
import java.util.Scanner;

import Controller.GameManager;
import Model.*;
import View.*;

public class LimanSimulator {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your username: ");
			String name = scanner.nextLine();
			byte[] serializedMapData = DatabaseUtil.loadGameMap(name);
            Map gameMap = null;

            if (serializedMapData != null) {
                gameMap = Map.deserializeMap(serializedMapData);
            } else {
                Player player = new Player(name);
                gameMap = new Map(player);
            }
					
			ConsoleView consoleView = new ConsoleView(gameMap);
			GameManager gameManager = GameManager.getInstance(gameMap, consoleView);
			
			gameManager.play();
		}
	}

}
