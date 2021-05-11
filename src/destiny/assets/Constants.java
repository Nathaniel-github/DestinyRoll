package destiny.assets;

import java.awt.Toolkit;

/**
 * 
 * A class to hold some values that are constant throughout runtime
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class Constants {
	
	public static final int NUM_OF_CHARACTERS = 20;
	public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 65;
	public static final int TOTAL_LEVELS = 20;
	public static final int TOTAL_CHARACTERS = 20;
	
	
	private Constants() {}

	/**
	 * 
	 * Gives the string corresponding to the integer action
	 * 
	 * @param act The integer form of the action you are looking for
	 * @return The string form of the action for the integer given. Will return null if not an actual integer action
	 */
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
