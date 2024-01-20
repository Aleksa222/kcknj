package Model;
public class Radman extends MojaBraca{
	
	public void interact(Player player) {
		player.setMoney(player.getMoney() + super.getAmount());
		System.out.println("Sreo si Radmana, uleteo ti je " + super.getAmount() + " kesa");
    }
}