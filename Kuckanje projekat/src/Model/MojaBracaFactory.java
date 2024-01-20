package Model;

public class MojaBracaFactory {
	public MojaBracaFactory() {
		
	}
	
	public static MojaBraca create(String s) {
		if(s == "Rokva") {
			return new Rokva();
		} else if(s == "Radman") {
			return new Radman();
		} else if(s == "Mica") {
			return new Mica();
		} else if (s == "Suvi") {
			return new Suvi();
		} else {
			return null;
		}
	}
}
