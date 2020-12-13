package destiny.assets;

/**
 * 
 * A class to hold some values that are constant throughout runtime
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class Constants {
	
	public static final int NUM_OF_CHARACTERS = 20;
	
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
