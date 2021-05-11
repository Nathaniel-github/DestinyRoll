package destiny.core;


import java.util.Deque;
import java.util.LinkedList;

import processing.core.PApplet;
/**
 * This class handles all events that are triggered in the PApplet and filters them
 * to any listeners on those events
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class EventHandler {
	
	private static Deque<ClickEvent> clickables = new LinkedList<ClickEvent>();
	private static Deque<DragEvent> draggables = new LinkedList<DragEvent>();
	
	@SuppressWarnings("rawtypes")
	private static Deque[] allComps = {clickables, draggables};
	
	static void addClickable(ClickEvent clicker) {
		
		if (clickables.contains(clicker))
			return;
		clickables.addFirst(clicker);
		
	}
	
	static void removeClickable(ClickEvent clicker) {
		
		clickables.remove(clicker);
		
	}
	
	static void addDraggable(DragEvent drag) {
		
		if (draggables.contains(drag))
			return;
		draggables.addFirst(drag);	
		
	}
	
	static void removeDraggable(DragEvent drag) {
		
		draggables.remove(drag);
		
	}
	
	/**
	 *
	 * This call should be made whenever there is a click that has occurred on the screen
	 * 
	 * @pre The PApplet must have been clicked
	 * @param window The screen that was clicked on
	 */
	public static void notifyClickables(PApplet window) {
		
		if (!window.mousePressed)
			throw new IllegalArgumentException("The PApplet provided has not been clicked");
		
		Event pack = makeEvent(window);
		
		for (ClickEvent e : clickables) {
			
			if (e.click(pack))
				break;
			
		}
		
	}
	
	/**
	 * 
	 * This call should be made whenever the mouse has been dragged on the screen
	 * 
	 * @pre The PApplet must have had the mouse dragged on it
	 * @param window The screen that was dragged across
	 */
	public static void notifyDraggables(PApplet window) {
		
		Event pack = makeEvent(window);
		
		for (DragEvent e : draggables) {
			
			if (e.drag(pack))
				break;
			
		}
		
	}
	
	/**
	 * 
	 * This call should be made whenever the mouse has been released
	 * 
	 * @param window The screen that had the mouse released
	 */
	public static void notifyRelease(PApplet window) {
		
		if (window.mousePressed)
			throw new IllegalArgumentException("The PApplet provided has not had the mouse released");
		
		
		Event pack = makeEvent(window);
		
		for (ClickEvent e : clickables) {
			
			e.release(pack);
			
		}
		
	}
	
	static void clearScreen() {
		
		for (int i = 0; i < allComps.length; i ++)
			allComps[i].clear();
		
	}
	
	private static Event makeEvent(PApplet window) {
		
		return new Event(window.mouseX, window.mouseY);
		
	}
	
}
