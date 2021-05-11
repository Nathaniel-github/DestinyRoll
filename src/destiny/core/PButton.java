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
	private Shape collider;
	private Runnable exec;
	private boolean visible;
	private boolean listenOnClick;
	private boolean isClicked = false;
	
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
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (!listenOnClick && isClicked)
				exec.run();
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
		
		if (visible) {
			Rectangle bounds= collider.getBounds();
			window.image(texture, bounds.x, bounds.y, bounds.width, bounds.height);
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
		
	}
	
	/**
	 * 
	 * Removes the image texture from the button
	 * 
	 */
	public void removeTexture() {
		
		texture = null;
		visible = false;
		
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

}