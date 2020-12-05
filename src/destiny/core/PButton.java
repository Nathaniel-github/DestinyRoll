package destiny.core;

import java.awt.Polygon;
import java.awt.Shape;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

/**
 * 
 * This class represents a button that can be added to a PApplet. It requires the 
 * EventHandler in order for its events to be triggered
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class PButton implements ClickEvent {
	
	private PShape shape;
	private Shape collider;
	private Runnable exec;
	private boolean visible;
	private boolean listenOnClick;
	private boolean isClicked = false;
	
	public PButton (Shape collider, boolean onClick) {
		
		setupButton(new PShape(), collider, false, onClick);
		
	}
	
	public PButton (PShape shape, Shape collider, PImage texture, boolean onClick) {
		
		shape.setTexture(texture);
		
		setupButton(shape, collider, true, onClick);
		
	}
	
	public PButton () {
		
		setupButton(new PShape(), new Polygon(), false, false);
		
	}
	
	private void setupButton(PShape shape, Shape collider, boolean vis, boolean onClick) {
		
		this.shape = shape;
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
	public boolean released(Event e) {
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (!listenOnClick && isClicked)
				exec.run();
			isClicked = false;
			return true;
		}
		isClicked = false;
		return false;
		
	}
	
	public void draw(PApplet window) {
		
		if (visible)
			window.shape(shape);
		
	}
	
	public void addListener(Runnable code) {
		
		this.exec = code;
		EventHandler.addClickable(this);
		
	}
	
	public void listenOnClick(boolean onClick) {
		
		listenOnClick = onClick;
		
	}
	
	public void setTexture(PImage texture) {
		
		shape.setTexture(texture);
		visible = true;
		
	}
	
	public void setVisibility(boolean visibility) {
		
		this.visible = visibility;
		
	}
	
	public void removeTexture() {
		
		shape.noTexture();
		visible = false;
		
	}
	
	public void removeListener() {
		
		EventHandler.removeClickable(this);
		
	}

}