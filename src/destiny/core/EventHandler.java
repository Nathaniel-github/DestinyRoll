package destiny.core;

import java.util.Deque;
import java.util.LinkedList;

import processing.core.PApplet;

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
	
	public static void notifyClickables(PApplet window) {
		
		if (!window.mousePressed)
			throw new IllegalArgumentException("The PApplet provided has not been clicked");
		
		Event pack = makeEvent(window);
		
		for (ClickEvent e : clickables) {
			
			if (e.click(pack))
				break;
			
		}
		
	}
	
	public static void notifyDraggables(PApplet window) {
		
		Event pack = makeEvent(window);
		
		for (DragEvent e : draggables) {
			
			if (e.drag(pack))
				break;
			
		}
		
	}
	
	public static void notifyRelease(PApplet window) {
		
		if (window.mousePressed)
			throw new IllegalArgumentException("The PApplet provided has not had the mouse released");
		
		
		Event pack = makeEvent(window);
		
		for (ClickEvent e : clickables) {
			
			e.released(pack);
			
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
