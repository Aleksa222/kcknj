package View;
import java.util.Scanner;

import Model.*;

public class ConsoleView {
	private Map gameMap;
	private Scanner scanner;
	
	public ConsoleView(Map m) {
		this.gameMap = m;
		this.scanner = new Scanner(System.in);
	}
	
	public void displayMap() {
		System.out.println("Map: ");
		for(int row = 0; row < gameMap.getSize(); row++) {
			for(int col = 0; col < gameMap.getSize(); col++) {
				Location currentLocation = gameMap.getLocations()[row][col];
				if(currentLocation.getPlayer() != null) {
					System.out.print("P ");
				} else if(currentLocation.getMoney() != null){
					System.out.print("$ ");
				} else if(currentLocation.getMojBrat() != null){
					System.out.print("B ");
				} else if(currentLocation.getPolice() != null){
					System.out.print("! ");
				}
				else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
	
	public void displayOptions() {
		int currentRow = gameMap.getPlayer().getPlayerLocation().getRow();
		int currentCol = gameMap.getPlayer().getPlayerLocation().getCol();
		
		System.out.println("Options:");
		if(currentCol > 0) {
			System.out.println("4. Move left");
		}
		if (currentCol < gameMap.getSize() - 1) {
            System.out.println("6. Move Right");
        }
        if (currentRow > 0) {
            System.out.println("8. Move Up");
        }
        if (currentRow < gameMap.getSize() - 1) {
            System.out.println("2. Move Down");
        }
        System.out.println("0. Quit");

	}
	
	public int getUserChoice() {
		System.out.print("Enter your choice: ");
		return scanner.nextInt();
	}
	
	public void displayMessage(String s) {
		System.out.println(s);
	}
}
