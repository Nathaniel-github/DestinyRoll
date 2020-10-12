package destiny.core;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class PButton implements ClickEvent {
	
	private PShape collider;
	private Runnable exec;
	private boolean hasTexture;
	
	public PButton (PShape collider, Runnable code) {
		
		this.collider = collider; 
		this.exec = code;
		
		hasTexture = false;
		
	}
	
	public PButton (PShape collider, Runnable code, PImage texture) {
		
		this.collider = collider; 
		this.exec = code;
		
		collider.setTexture(texture);
		hasTexture = true;
		
	}
	
	public PButton () {
		
		this.collider = new PShape();
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		hasTexture = false;
		
	}

	@Override
	public boolean alert(Event e) {
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			new Thread(exec).start();
			return true;
		}
		return false;
	}
	
	public void draw(PApplet window) {
		
		if (hasTexture)
			window.shape(collider);
		
	}
	
	public void addListener() {
		
		EventHandler.addClickable(this);
		
	}
	
	public void setTexture(PImage texture) {
		
		collider.setTexture(texture);
		hasTexture = true;
		
	}
	
	public void removeTexture() {
		
		collider.noTexture();
		hasTexture = false;
		
	}
	
	public void turnOffListening() {
		
		EventHandler.removeClickable(this);
		
	}
	
	public void turnOnListening() {
		
		EventHandler.addClickable(this);
		
	}
	
	

}