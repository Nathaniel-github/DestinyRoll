package destiny.core;

/**
 * This class is for detecting clicking on the window
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
interface ClickEvent {
	
	/**
	 * Called on the event of a click anywhere on the screen. Information about the click will be passed as an Event
	 * 
	 * @param e Information about the click that took place
	 * @return Whether or not the object was actually being clicked on or if it was a click that took place outside the clickable
	 */
	public boolean click(Event e);
	
	/**
	 * Called on the event of a mouse being released anywhere on the screen. Information about the release will be passed as an Event
	 * 
	 * @param e Information about the click that took place
	 * @return Whether or not the cursor was over the object on release and was clicking on the object to begin with
	 */
	public boolean release(Event e);

}
