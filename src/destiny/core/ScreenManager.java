package destiny.core;

import java.util.HashMap;

import processing.core.PApplet;

public class ScreenManager {
	
	private static String currentScreen;
	private static HashMap<String, Screen> allScreens = new HashMap<>();
	
	public void addScreen(String key, Screen s) {
		
		allScreens.put(key, s);
		
	}
	
	public void drawCurrentScreen(PApplet window) {
		
		if (currentScreen == null)
			return;
		allScreens.get(currentScreen).draw(window);;
		
	}
	
	public void setCurrentScreenByName(String name) {
		
		if (!allScreens.containsKey(name))
			throw new NullPointerException("That screen does not exist");
		else {
			currentScreen = name;
			EventHandler.clearScreen();
		}
	}
	
}