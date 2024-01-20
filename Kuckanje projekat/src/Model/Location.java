package Model;

import java.io.Serializable;

public class Location implements Serializable{
	private int row;
	private int col;
	private Player player;
	private Money money;
	private MojaBraca mojBrat;
	private Police police;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
		this.player = null;
		this.money = null;
		this.police = null;
	}
	
	public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Money getMoney() {
    	return money;
    }
    
    public void setMoney(Money money) {
    	this.money = money;
    }
    
    
    public MojaBraca getMojBrat() {
    	return mojBrat;
    }
    
    public void setMojBrat(MojaBraca b) {
    	this.mojBrat = b;
    }
    
    public Police getPolice() {
    	return police;
    }
    
    public void setPolice(Police p) {
    	this.police = p;
    }
    
    public boolean checkAvailability() {
		if(this.getPlayer() == null && this.getMoney() == null && this.getMojBrat() == null && this.getPolice() == null) {
			return true;
		} return false;
	}
    
    public boolean checkAvailabiltyForPolice() {
    	if(this.getMoney() == null && this.getMojBrat() == null && this.getPolice() == null) {
			return true;
		} return false;
    }
}
