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
	
	public static void addScreen(String key, Screen s) {
		
		allScreens.put(key, s);
		
	}
	
	public static void setScreen(String key, Screen s, PApplet window) {
		
		addScreen(key, s);
		setCurrentScreenByName(key, window);
		
	}
	
	public static void drawCurrentScreen(PApplet window) {
		
		window.clear();
		window.background(255, 255, 255);
		if (currentScreen == null)
			return;
		allScreens.get(currentScreen).draw(window);;
		
	}
	
	public static void setCurrentScreenByName(String name, PApplet window) {
		
		if (!allScreens.containsKey(name))
			throw new NullPointerException("That screen does not exist");
		else {
			EventHandler.clearScreen();
			if (currentScreen != null)
				allScreens.get(currentScreen).dispose();
			currentScreen = name;
			allScreens.get(currentScreen).setup(window);
		}
	}
	
}