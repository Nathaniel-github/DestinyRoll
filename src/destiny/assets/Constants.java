package destiny.assets;

public class Constants {
	
	public static final int NUM_OF_CHARACTERS = 10;
	
	private Constants() {}

	public static String getActionForInt(int act) {
		
		switch(act) {
		
		case 0:
			return "attack";
		case 1:
			return "flinch";
		case 2:
			return "move";
		case 3:
			return "static";
		default:
			return null;
			
		}
		
	}
	
}
