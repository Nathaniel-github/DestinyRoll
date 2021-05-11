package destiny.core;

/**
 * 
 * This class is for all events
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
class Event {
	
	private final int mouseX, mouseY;
	
	/**
	 * Creates an Event object storing the x and y coordinates of the mouse
	 * 
	 * @param mX The x coordinate of the mouse
	 * @param mY The y coordinate of the mouse
	 */
	public Event(int mX, int mY) {
		
		this.mouseX = mX;
		this.mouseY = mY;
		
	}
	
	/**
	 * 
	 * Gets the x coordinate of the mouse that was stored when creating this event
	 * 
	 * @return The x coordinate of the mouse
	 */
	public int getMouseX() {
		
		return mouseX;
		
	}
	
	/**
	 * 
	 * Gets the y coordinate of the mouse that was stored when creating this event
	 * 
	 * @return The x coordinate of the mouse
	 */
	public int getMouseY() {
		
		return mouseY;
		
	}
	
}
