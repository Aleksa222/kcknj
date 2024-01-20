package Model;
public class Suvi extends MojaBraca{
	
	public void interact(Player player) {
		player.setSic(player.getSic() - super.getAmount());
		System.out.println("Suvi nije dao pare za kurblu, izbacio si mu " + super.getAmount() + " sica.");
    }
}
