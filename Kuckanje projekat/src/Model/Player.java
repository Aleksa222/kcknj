package Model;

import java.io.Serializable;

public class Player implements Serializable{
	private String name;
	private int money;
	private int sic;
	private Location playerLocation;
	
	public Player(String name) {
		this.name = name;
		this.money = 0;
		this.sic = 0;
	};
	
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getSic() {
		return sic;
	}
	
	public Location getPlayerLocation() {
		return playerLocation;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void setSic(int sic) {
		this.sic = sic;
	}
	
	public void setPlayerLocation(Location location, Player player) {
		this.playerLocation = location;
		location.setPlayer(player);
	}
	
	public void collectMoney() {
		this.money += 1;
	}
	
	
}
