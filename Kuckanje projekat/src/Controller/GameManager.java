package Controller;
import java.sql.Timestamp;
import java.util.Random;
import View.*;
import Model.*;

public class GameManager {
	private static GameManager instance;
	private Map gameMap;
	private ConsoleView consoleView;
	private int score;
	
	public GameManager(Map m, ConsoleView cV) {
		this.gameMap = m;
		this.consoleView = cV;
	}
	
	public static GameManager getInstance(Map m, ConsoleView cV) {
		if(instance == null) {
			instance = new GameManager(m, cV);
		}
		return instance;
	}
	
	public void play() {
		consoleView.displayMap();
		consoleView.displayMessage("START YOU JOURNEY!");
		
		boolean existingUser = DatabaseUtil.doesUserExist(gameMap.getPlayer().getName());
		score = existingUser ? DatabaseUtil.getScoreByUsername(gameMap.getPlayer().getName()) : 0;
		while(true) {
			consoleView.displayOptions();
			int choice = consoleView.getUserChoice();
			if (choice == 0) {
				if (DatabaseUtil.doesUserExist(gameMap.getPlayer().getName())) {
	                updateUserScore(gameMap.getPlayer().getName());
	            } else {
	                addUserScore(gameMap.getPlayer().getName());
	            }
				consoleView.displayMessage("Quitting the game. Thanks for playing!");
	            System.exit(0);
	        }
			movePolice();
			handleChoice(choice);
			consoleView.displayMap();
			consoleView.displayMessage(" ");
			consoleView.displayMessage("Your balance: " + gameMap.getPlayer().getMoney());
			consoleView.displayMessage("Amount of sic: " + gameMap.getPlayer().getSic());
		}
	}
	
	private void handleChoice(int choice) {
		int currentRow = gameMap.getPlayer().getPlayerLocation().getRow();
		int currentCol = gameMap.getPlayer().getPlayerLocation().getCol();
		
		switch(choice) {
		case 4:
            if (currentCol > 0) {
                movePlayer(currentRow, currentCol - 1);
                score++;
            }
            break;
        case 6:
            if (currentCol < gameMap.getSize() - 1) {
                movePlayer(currentRow, currentCol + 1);
                score++;
            }
            break;
        case 8:
            if (currentRow > 0) {
                movePlayer(currentRow - 1, currentCol);
                score++;
            }
            break;
        case 2:
            if (currentRow < gameMap.getSize() - 1) {
                movePlayer(currentRow + 1, currentCol);
                score++;
            }
            break;
		default:
			consoleView.displayMessage("Invalid choice. Please try again. ");
		}
	}
	
	private void movePlayer(int newRow, int newCol) {
		gameMap.getPlayer().getPlayerLocation().setPlayer(null);
		Location newPlayerLoc = gameMap.getLocation(newRow, newCol);
		gameMap.getPlayer().setPlayerLocation(newPlayerLoc, gameMap.getPlayer());
		
		if(newPlayerLoc.getMoney() != null) {
			gameMap.getPlayer().collectMoney();
			newPlayerLoc.setMoney(null);
			consoleView.displayMessage("You found money! Your balance is now: " + gameMap.getPlayer().getMoney());
		}
		
		MojaBraca mojBrat = newPlayerLoc.getMojBrat();
		if(mojBrat != null) {
			mojBrat.interact(gameMap.getPlayer());
		}
		
		if(newPlayerLoc.getPolice() != null) {
			consoleView.displayMessage("You encountered the police. Game over!");
			System.exit(0);
		}
		
	}
	
	private void movePolice() {
		int currentRow = gameMap.getPolice().getPoliceLocation().getRow();
		int currentCol = gameMap.getPolice().getPoliceLocation().getCol();
		Police p = gameMap.getPolice();
		Random random = new Random();
		int r = random.nextInt(4) + 1;
		switch(r) {
		case 4:
            if (currentCol > 0) {
                int newCol = currentCol - 1;
                Location newPoliceLoc = gameMap.getLocation(currentRow, newCol);
                if(newPoliceLoc.checkAvailabiltyForPolice()) {
                	gameMap.getPolice().getPoliceLocation().setPolice(null);
                	gameMap.getPolice().setPoliceLocation(newPoliceLoc, p);
                }
            }
            break;
        case 6:
            if (currentCol < gameMap.getSize() - 1) {
            	int newCol = currentCol + 1;
                Location newPoliceLoc = gameMap.getLocation(currentRow, newCol);
                if(newPoliceLoc.checkAvailabiltyForPolice()) {
                	gameMap.getPolice().getPoliceLocation().setPolice(null);
                	gameMap.getPolice().setPoliceLocation(newPoliceLoc, p);
                }
            }
            break;
        case 8:
            if (currentRow > 0) {
            	int newRow = currentRow - 1;
                Location newPoliceLoc = gameMap.getLocation(newRow, currentCol);
                if(newPoliceLoc.checkAvailabiltyForPolice()) {
                	gameMap.getPolice().getPoliceLocation().setPolice(null);
                	gameMap.getPolice().setPoliceLocation(newPoliceLoc, p);
                }
            }
            break;
        case 2:
            if (currentRow < gameMap.getSize() - 1) {
            	int newRow = currentRow + 1;
                Location newPoliceLoc = gameMap.getLocation(newRow, currentCol);
                if(newPoliceLoc.checkAvailabiltyForPolice()) {
                	gameMap.getPolice().getPoliceLocation().setPolice(null);
                	gameMap.getPolice().setPoliceLocation(newPoliceLoc, p);
                }
            }
            break;
		}
		
	}
	
	private void updateUserScore(String playerName) {
		DatabaseUtil.updateScore(new PlayerScores(playerName, score, new Timestamp(System.currentTimeMillis()), gameMap ));
	}
	
	private void addUserScore(String playerName) {
		DatabaseUtil.addScore(new PlayerScores(playerName, score, new Timestamp(System.currentTimeMillis()), gameMap));
	}
}
