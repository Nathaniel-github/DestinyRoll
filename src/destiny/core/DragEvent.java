package destiny.core;

/**
 * 
 * This class is for detecting dragging on the window
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
interface DragEvent extends ClickEvent {

	/**
	 * Called on the event of a mouse being dragged anywhere on the screen. Information about the drag will be passed as an Event
	 * 
	 * @param e Information about the click that took place
	 * @return Whether or not the mouse was dragged over this object
	 */
	public boolean drag(Event e);
	
}
