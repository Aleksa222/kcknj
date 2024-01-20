package Model;
public class Mica extends MojaBraca{
	
	public void interact(Player player) {
		player.setMoney(player.getMoney() - super.getAmount());
		System.out.println("Sreo si Micu, falilo joj je " + super.getAmount() + " za pljuge.");
    }
}