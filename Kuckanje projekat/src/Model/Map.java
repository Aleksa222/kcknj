package Model;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Map implements Serializable {
	private static final int size = 7;
	private Location[][] locations;
	private Player player;
	private Police police;
	
	
	public Map(Player player) {
		this.player = player;
		this.locations = new Location[size][size];
		this.police = new Police();
		
		initializeLocations();
	}
	
	public Location getLocation(int row, int col){
		return locations[row][col];
	}
	
	public Location[][] getLocations(){
		return locations;
	}
	
	public int getSize() {
		return size;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Police getPolice() {
		return police;
	}
	
	private void initializeLocations() {
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				locations[row][col] = new Location(row,col);
			}
		}
		
		assignPlayerToLocation();
		assignMoneyToLocations();
		assignMojaBracaToLocations();
		assignPoliceToLocation();
	}
	
	
	private void assignPlayerToLocation() {
		
		Location randomLocation = getRandomEmptyLocation(locations);
		player.setPlayerLocation(randomLocation, player);
	}
	
	private void assignMoneyToLocations() {
	    int moneyCount = 0;
	    while (moneyCount < 5) {
	    	Location location = getRandomEmptyLocation(locations);
			location.setMoney(new Money());
			moneyCount++;
	    }
	}
	
	private void assignPoliceToLocation() {
		Location randomLocation = getRandomEmptyLocation(locations);
		police.setPoliceLocation(randomLocation, new Police());
	}
	
	private void assignMojaBracaToLocations() {
		assignMojaBraca("Suvi");
        assignMojaBraca("Mica");
        assignMojaBraca("Radman");
        assignMojaBraca("Rokva");
    }
	
	private void assignMojaBraca(String type) {
        Location randomLocation = getRandomEmptyLocation(locations);
        
        if (randomLocation.checkAvailability()) {
        	MojaBraca mojBrat = MojaBracaFactory.create(type);
            randomLocation.setMojBrat(mojBrat);
        } else {
            assignMojaBraca(type);
        }
    }
	
	public Location getRandomEmptyLocation(Location[][] locations) {
        Random random = new Random();
        int maxAttempts = 20; 
        for (int i = 0; i < maxAttempts; i++) {
            int randomRow = random.nextInt(locations.length);
            int randomCol = random.nextInt(locations[0].length);
            Location randomLocation = locations[randomRow][randomCol];
            if (randomLocation.checkAvailability()) {
                return randomLocation;
            }
        }
        return null;
    }
	
	public byte[] serializeMap() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Map deserializeMap(byte[] mapStateBytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(mapStateBytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Map) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
