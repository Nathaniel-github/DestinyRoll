package destiny.core;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class represents a button that can be added to a PApplet. It requires the 
 * EventHandler in order for its events to be triggered
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class PButton implements ClickEvent {

	private PImage texture;
	private PGif gifTexture;
	private Shape collider;
	private Runnable exec;
	private Runnable holdExec, holdReleaseExec;
	private int heldTimer = 120;
	private boolean visible;
	private boolean listenOnClick;
	private boolean isGif;
	private boolean isClicked = false;
	private boolean highlight = false;
	private boolean isListening = true;
	private boolean isHeld = false;
	
	/**
	 * 
	 * Creates a button with the specified area. Can also be set to trigger on click or on release
	 * 
	 * @param collider The bounding box that the button should be triggered on
	 * @param onClick Whether or not the button should trigger on click or on release
	 */
	public PButton (Shape collider, boolean onClick) {
		
		setupButton(collider, false, onClick);
		
	}
	
	/**
	 * 
	 * Constructs a button with the specified area and image. Can also be set to trigger on click or on release
	 * 
	 * @param collider The bounding box that the button should be triggered on
	 * @param texture The Image texture that should be drawn for the button
	 * @param onClick Whether or not the button should trigger on click or on release
	 */
	public PButton (Shape collider, PImage texture, boolean onClick) {
		
		this.texture = texture;
		
		setupButton(collider, true, onClick);
		
	}
	
	/**
	 * 
	 * Creates a button that can't be clicked on and is not visible
	 * 
	 */
	public PButton () {
		
		setupButton(new Polygon(), false, false);
		
	}
	
	private void setupButton(Shape collider, boolean vis, boolean onClick) {
		
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		visible = vis;
		listenOnClick = onClick;
		
		this.collider = collider;
		
	}

	@Override
	public boolean click(Event e) {
		
		if (!isListening)
			return false;
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (listenOnClick)
				exec.run();
			isClicked = true;
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean release(Event e) {
		
		if (!isListening)
			return false;
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (!listenOnClick && isClicked && !isHeld)
				exec.run();
			else if (isHeld) {
				isHeld = false;
				holdReleaseExec.run();
			}
			isClicked = false;
			return true;
		}
		isClicked = false;
		return false;
		
	}
	
	/**
	 * 
	 * Draws the button to the window if it is visible (if it has an image)
	 * 
	 * @param window The PApplet the button should be drawn to
	 * @post The PApplet will have the button drawn to it if the button is visible
	 */
	public void draw(PApplet window) {
		
		if (isClicked && !listenOnClick && holdExec != null) {
			
			heldTimer--;
			
		}
		
		if (heldTimer <= 0) {
			
			heldTimer = 120;
			isClicked = false;
			isHeld = true;
			if (holdExec != null)
				holdExec.run();
			
		}
		
		if (visible) {
			if (!isGif) {
				Rectangle bounds= collider.getBounds();
				window.image(texture, bounds.x, bounds.y, bounds.width, bounds.height);
			} else {
				gifTexture.draw(window);
			}
			if(highlight) {
				window.pushStyle();
				window.stroke(0);
				window.strokeWeight(5f);
				Rectangle bounds= collider.getBounds();
				window.line(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y);
				window.line(bounds.x, bounds.y, bounds.x, bounds.y + bounds.height);
				window.line(bounds.x + bounds.width, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
				window.line(bounds.x + bounds.width, bounds.y + bounds.height, bounds.x, bounds.y + bounds.height);
				window.popStyle();
			}
		}
	}
	
	/**
	 * 
	 * Sets a block of code to be run on the event the button is clicked
	 * 
	 * @param code The code that should be run in the event the button is clicked
	 */
	public void addListener(Runnable code) {
		
		this.exec = code;
		EventHandler.addClickable(this);
		
	}
	
	/**
	 * 
	 * Sets the button to either listen on click or on release
	 * 
	 * @param onClick If true the button will be triggered on click, if false it will be triggered on release
	 */
	public void listenOnClick(boolean onClick) {
		
		listenOnClick = onClick;
		
	}
	
	/**
	 * 
	 * Sets the image for the button
	 * 
	 * @param texture The Image that the button should be drawn with
	 */
	public void setTexture(PImage texture) {
		
		this.texture = texture;
		visible = true;
		isGif = false;
		
	}
	
	/**
	 * 
	 * Sets the texture of the button to a gif
	 * 
	 * @param texture The gif that should be the button's texture
	 */
	public void setGifTexture(PGif texture) {
		
		gifTexture = texture;
		visible = true;
		Rectangle bounds= collider.getBounds();
		texture.setCoords(bounds.x, bounds.y);
		texture.resize(bounds.width, bounds.height);
		isGif = true;
		
	}
	
	/**
	 * 
	 * Sets the highlight/border around the button
	 * 
	 * @param highlight Whether or not the highlight should be on
	 */
	public void setHightlight(boolean highlight) {
		
		this.highlight = highlight;
		
	}
	
	/**
	 * 
	 * Removes the image texture from the button
	 * 
	 */
	public void removeTexture() {
		
		texture = null;
		visible = false;
		gifTexture = null;
		isGif = false;
		
	}
	
	/**
	 * 
	 * Removes the runnable code from the button and doesn't allow the button to be triggered
	 * 
	 */
	public void removeListener() {
		
		exec = null;
		EventHandler.removeClickable(this);
		
	}

	/**
	 * 
	 * Toggles the highlight to the opposite of what it currently is
	 * 
	 */
	public void toggleHighlight() {
		highlight = !highlight;
	}
	
	/**
	 * 
	 * Returns whether or not the button is highlighted
	 * 
	 * @return Whether or not the button is highlighted
	 */
	public boolean isHighlighted() {
		return highlight;
	}
	
	/**
	 * 
	 * Disables the button from listening
	 * 
	 */
	public void disableListener() {
		isListening = false;
	}
	
	/**
	 * 
	 * Re-enables the button to perform its action on click
	 * 
	 */
	public void enableListener() {
		isListening = true;
	}
	
	/**
	 * 
	 * Adds a listener to run on the button being held
	 * 
	 * @param code The code to run on being held
	 */
	public void addHoldListener(Runnable code) {
		
		holdExec = code;
		EventHandler.addClickable(this);
		
	}
	
	/**
	 * 
	 * Adds a listener to run on the button hold being released
	 * 
	 * @param code The code that should be run whenever the button is held
	 */
	public void addHoldReleaseListener(Runnable code) {
		
		holdReleaseExec = code;
		EventHandler.addClickable(this);
		
	}
	
	/**
	 * 
	 * Removes the on hold listener
	 * 
	 */
	public void removeHoldListener() {
		
		holdExec = null;
		EventHandler.removeClickable(this);
		
	}
	
	/**
	 * 
	 * Removes the on hold release listener
	 * 
	 */
	public void removeHoldReleaseListener() {
		
		holdReleaseExec = null;
		EventHandler.removeClickable(this);
		
	}
	
	/**
	 * 
	 * Removes both the on hold and on hold release listeners
	 * 
	 */
	public void removeHoldListeners() {
		
		holdExec = null;
		holdReleaseExec = null;
		EventHandler.removeClickable(this);
		
	}
	
}