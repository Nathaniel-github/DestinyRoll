package destiny.core;

import java.util.HashMap;

import processing.core.PApplet;

public class ScreenManager {
	
	private static String currentScreen;
	private static HashMap<String, Screen> allScreens = new HashMap<>();
	
	public static void addScreen(String key, Screen s) {
		
		allScreens.put(key, s);
		
	}
	
	public static void setScreen(String key, Screen s) {
		
		addScreen(key, s);
		setCurrentScreenByName(key);
		
	}
	
	public static void drawCurrentScreen(PApplet window) {
		
		window.clear();
		if (currentScreen == null)
			return;
		allScreens.get(currentScreen).draw(window);;
		
	}
	
	public static void setCurrentScreenByName(String name) {
		
		if (!allScreens.containsKey(name))
			throw new NullPointerException("That screen does not exist");
		else {
			currentScreen = name;
			EventHandler.clearScreen();
			allScreens.get(currentScreen).setup();
		}
	}
	
}