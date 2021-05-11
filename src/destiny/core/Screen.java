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
	 * 
	 * Initializes all objects and variables that would be used in the Screen
	 * 
	 * @param window The PApplet that represents the window the Screen should be on
	 */
	public void setup(PApplet window);
	
	/**
	 * 
	 * Draws the Screen to the given window
	 * 
	 * @param window The PApplet that the Screen is drawn to
	 * @post The PApplet will have the Screen drawn to it
	 */
	public void draw(PApplet window);
	
	/**
	 * 
	 * Set all objects to null to conserve RAM
	 * 
	 */
	public void dispose();

}
