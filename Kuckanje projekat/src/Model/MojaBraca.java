package Model;
import java.io.Serializable;
import java.util.Random;

public class MojaBraca implements Serializable{
	private int amount;
	
	public MojaBraca() {
		Random random = new Random();
		this.amount = random.nextInt(3) + 1;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int a) {
		this.amount = a;
	}
	
	public void interact(Player player) {
		
	};
}
