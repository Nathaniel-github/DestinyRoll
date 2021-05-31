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
	
	/**
	 * 
	 * Returns the numerical value corresponding to each action
	 * 
	 * @param action The action you want the numerical value for
	 * @return The numerical value for the given action
	 */
	public static int getIntForAction(String action) {
		
		switch(action) {
		
		case "attack":
			return 0;
		case "flinch":
			return 1;
		case "move":
			return 2;
		case "static":
			return 3;
		default:
			return -1;
			
		}
		
	}
	
	/**
	 * 
	 * Returns the path to the gif file given the id number and action
	 * 
	 * @param id The id number of the revolutionary 
	 * @param action The action of the gif you desire
	 * @return The path to the gif file
	 */
	public static String getCharacterPath(int id, String action) {
		
		return "res/characters/"+id+"/asset"+id+"-"+action+".gif";
		
	}
	
	/**
	 * 
	 * Returns the full path to the enemy sprite
	 * 
	 * @param enemy The name of the enemy
	 * @param action The action of enemy you want
	 * @return The full path to the enemy action gif
	 */
	public static String getEnemyPath(String enemy, String action) {
		
		return "res/enemies/" + enemy+"-" +action+ ".gif";
		
	}
	
	/**
	 * 
	 * Returns the full path to the sound file
	 * 
	 * @pre The sound file is in the generalAssets folder
	 * @param fileName The name of the sound file
	 * @return The full path to the given sound file
	 */
	public static String getSoundPath(String fileName) {
		
		return "res/generalAssets/" + fileName;
		
	}
	
	/**
	 * 
	 * Scales a number to the width of the screen
	 * 
	 * @param num The number being scaled
	 * @return The scaled version of the number
	 */
	public static int scaleIntToWidth(double num) {
		
		return (int)(num/1920*SCREEN_WIDTH);
		
	}
	
	/**
	 * 
	 * Scales a number to the height of the screen
	 * 
	 * @param num The number being scaled
	 * @return The scaled version of the number
	 */
	public static int scaleIntToHeight(double num) {
		
		return (int)(num/1015*SCREEN_HEIGHT);
		
	}
	
}
