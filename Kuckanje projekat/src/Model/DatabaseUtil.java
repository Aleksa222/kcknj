package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL = "jdbc:postgresql://localhost:5433/kuckanje";
    private static final String USER = "postgres";
    private static final String PASSWORD = "suvinho";
    
    public static void addScore(PlayerScores playerScores) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO playerscores(username, score, game_date, map_state) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, playerScores.getUsername());
                preparedStatement.setInt(2, playerScores.getScore());
                preparedStatement.setTimestamp(3, playerScores.getDate());
                preparedStatement.setBytes(4, playerScores.getMapStateBytes());

                preparedStatement.executeUpdate();
                System.out.println("Score added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database or executing the query:");
            e.printStackTrace();
        }
    }
    

    public static byte[] loadGameMap(String username) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT map_state FROM playerscores WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getBytes("map_state");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database or executing the query:");
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean doesUserExist(String username) {
        String query = "SELECT COUNT(*) FROM playerscores WHERE username = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static void updateScore(PlayerScores playerScores) {
    	try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE playerscores SET score = ?, game_date = ?, map_state = ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            	preparedStatement.setInt(1, playerScores.getScore());
            	preparedStatement.setTimestamp(2, playerScores.getDate());
            	preparedStatement.setBytes(3, playerScores.getMapStateBytes());
                preparedStatement.setString(4, playerScores.getUsername());

                preparedStatement.executeUpdate();
                System.out.println("Score and map updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database or executing the query:");
            e.printStackTrace();
        }
    }
    
    public static int getScoreByUsername(String username) {
    	int score = 0;
    	try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
    		String query = "SELECT score FROM playerscores WHERE username = ?";
    		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
    			preparedStatement.setString(1, username);
    			
    			try(ResultSet resultSet = preparedStatement.executeQuery()){
    				if(resultSet.next()) {
    					score = resultSet.getInt("score");
    				}
    			}
    		}
    	} catch(SQLException e) {
    		System.err.println("Error retrieving score for user");
    		e.printStackTrace();
    	}
    	return score;
    }

    
}
