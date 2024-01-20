package Model;
public class Rokva extends MojaBraca{

	public void interact(Player player) {
		player.setSic(player.getSic() + super.getAmount());
		System.out.println("Sreo si Rokvu, izbacio ti je " + super.getAmount() + " sica.");
    }
}
