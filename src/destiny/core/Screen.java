package destiny.core;

import processing.core.PApplet;

/**
 * 
 * This is the interface that must be implemented by all Screens that are added to the 
 * ScreenManager
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public interface Screen {

	/**
	 * initialize all objects and variables that would be used in the Screen
	 * @param window
	 */
	public void setup(PApplet window);
	
	/**
	 * draw the Screen
	 * @param window
	 */
	public void draw(PApplet window);
	
	/**
	 * set all objects to null to conserve RAM
	 * @param window
	 */
	public void dispose();

}
