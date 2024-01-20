package Model;
import java.sql.Timestamp;

public class PlayerScores {
	private String username;
	private int score;
	private Timestamp date;
	private byte[] mapStateBytes;
	
	public PlayerScores() {
		
	}
	
	public PlayerScores(String username, int score, Timestamp date, Map map) {
		this.username = username;
		this.score = score;
		this.date = date;
		this.mapStateBytes = map.serializeMap();
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getScore() {
		return score;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public byte[] getMapStateBytes() {
        return mapStateBytes;
    }

    public void setMapStateBytes(byte[] mapStateBytes) {
        this.mapStateBytes = mapStateBytes;
    }
 
}
