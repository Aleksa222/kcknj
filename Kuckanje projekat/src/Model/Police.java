package Model;
import java.io.Serializable;
import java.util.Random;

public class Police implements Serializable{
	private Location policeLocation;
	
	public Police() {

	}
	
	public Location getPoliceLocation() {
		return policeLocation;
	}
	
	public void setPoliceLocation(Location location, Police police) {
		this.policeLocation = location;
		location.setPolice(police);
	}
	
	public Location getRandomEmptyLocationForPolice(Location[][] locations) {
        Random random = new Random();
        int maxAttempts = 20; 
        for (int i = 0; i < maxAttempts; i++) {
            int randomRow = random.nextInt(locations.length);
            int randomCol = random.nextInt(locations[0].length);
            Location randomLocation = locations[randomRow][randomCol];
            if (randomLocation.checkAvailabiltyForPolice()) {
                return randomLocation;
            }
        }
        return null;
    }
	
}
