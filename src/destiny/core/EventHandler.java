package destiny.core;

import java.util.Deque;
import java.util.LinkedList;

import processing.core.PApplet;

public class EventHandler {
	
	private static Deque<ClickEvent> clickables = new LinkedList<ClickEvent>();
	
	static void addClickable(ClickEvent clicker) {
		
		if (clickables.contains(clicker))
			return;
		clickables.addFirst(clicker);	
		
	}
	
	static void removeClickable(ClickEvent clicker) {
		
		clickables.remove(clicker);
		
	}
	
	public static void notifyClickables(PApplet window) {
		
		Event pack = makeEvent(window);
		
		for (ClickEvent e : clickables) {
			
			if (e.alert(pack))
				break;
			
		}
		
	}
	
	static void clearScreen() {
		
		clickables.clear();
		
	}
	
	private static Event makeEvent(PApplet window) {
		
		return new Event(window.mouseX, window.mouseY);
		
	}
	
}
