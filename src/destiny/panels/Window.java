package destiny.panels;

import destiny.core.*;
import processing.core.PApplet;
import processing.video.Movie;

public class Window extends PApplet {

	public void setup() {
		
		// Instantiate your screens here and add them to the SceenManager
		// You can start with the ScreenManager.setScreen(screenName, screen);
		// Then add screens that you will use later with ScreenManager.addScreen(screenName, screen);
		// You can switch to these screens on events that you want to monitor using -
		// ScreenManager.setCurrentScreenByName(screenName);
		
	}

	public void draw() {
		ScreenManager.drawCurrentScreen(this);
	}
	
	public void movieEvent(Movie m) {
		m.read();
	}
	
	public void mousePressed() {
		EventHandler.notifyClickables(this);
	}
	
	public void mouseReleased() {
		EventHandler.notifyRelease(this);
	}
	
	public void mouseDragged() {
		EventHandler.notifyDraggables(this);
	}
	
}
