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

	public void setup(PApplet window);
	public void draw(PApplet window);
	public void dispose();

}
