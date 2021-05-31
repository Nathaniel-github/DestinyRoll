package destiny.core;

import java.util.HashMap;

import processing.core.PApplet;

/**
 * 
 * This class manages all the screens in the game and decides which one should be 
 * drawn and when
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class ScreenManager {
	
	private static String currentScreen;
	private static HashMap<String, Screen> allScreens = new HashMap<>();
	private static boolean disposeFlag = false;
	private static String nextScreen;
	
	private ScreenManager() {}
	
	/**
	 * 
	 * Add a Screen to the ScreenManager
	 * 
	 * @param key The key that the screen should be stored with
	 * @param s The screen that should be placed with that key
	 */
	public static void addScreen(String key, Screen s) {
		
		allScreens.put(key, s);
		
	}
	/**
	 * 
	 * Set the screen to the given screen given a screen object and key
	 * 
	 * @param key The desired screen's name
	 * @param s The Screen object
	 * @param window The window that the screen should be drawn on
	 */
	public static void setScreen(String key, Screen s, PApplet window) {
		
		addScreen(key, s);
		
		EventHandler.clearScreen();
		if (currentScreen != null)
			allScreens.get(currentScreen).dispose();
		currentScreen = key;
		allScreens.get(currentScreen).setup(window);
		
	}
	
	/**
	 * 
	 * Gets the current screen that is being drawn
	 * 
	 * @return The screen that is currently being drawn
	 */
	public static Screen getCurrentScreen() {
		
		return allScreens.get(currentScreen);
		
	}
	
	/**
	 * 
	 * Draws the screen that is selected
	 * 
	 * @post Has the screen drawn to the PApplet
	 * @param window The PApplet that the screen should be drawn to
	 */
	public static void drawCurrentScreen(PApplet window) {
		
		window.clear();
		window.background(255, 255, 255);
		if (currentScreen == null)
			return;
		allScreens.get(currentScreen).draw(window);
		
		if (disposeFlag) {
			disposeScreen(window);
			disposeFlag = false;
		}
	}

	private static void disposeScreen(PApplet window) {
		
		EventHandler.clearScreen();
		Runtime.getRuntime().gc();
		if (currentScreen != null)
			allScreens.get(currentScreen).dispose();
		currentScreen = nextScreen;
		allScreens.get(currentScreen).setup(window);
		nextScreen = null;
		
	}
	
	/**
	 * 
	 * Set the screen with the given screen name
	 * 
	 * @param name The name of the screen
	 * @param window The window that the screen should be drawn to
	 */
	public static void setCurrentScreenByName(String name, PApplet window) {
		
		if (!allScreens.containsKey(name))
			throw new NullPointerException("That screen does not exist");
		else {

			disposeFlag = true;
			nextScreen = name;
			
		}
	}
	
}