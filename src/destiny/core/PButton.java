package destiny.core;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class PButton implements ClickEvent {
	
	private PShape collider;
	private Runnable exec;
	private boolean hasTexture;
	private boolean listenOnClick;
	private boolean isClicked = false;
	
	public PButton (PShape collider, Runnable code) {
		
		this.collider = collider; 
		this.exec = code;
		
		hasTexture = false;
		listenOnClick = false;
		
	}
	
	public PButton (PShape collider, Runnable code, boolean onClick) {
		
		this.collider = collider; 
		this.exec = code;
		
		hasTexture = false;
		listenOnClick = onClick;
		
	}
	
	public PButton (PShape collider, Runnable code, PImage texture) {
		
		this.collider = collider; 
		this.exec = code;
		
		collider.setTexture(texture);
		hasTexture = true;
		listenOnClick = false;
		
	}
	
	public PButton (PShape collider, Runnable code, PImage texture, boolean onClick) {
		
		this.collider = collider; 
		this.exec = code;
		
		collider.setTexture(texture);
		hasTexture = true;
		listenOnClick = onClick;
		
	}
	
	public PButton () {
		
		this.collider = new PShape();
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		hasTexture = false;
		listenOnClick = false;
		
	}

	@Override
	public boolean click(Event e) {
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (listenOnClick)
				new Thread(exec).start();
			isClicked = true;
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean released(Event e) {
		
		if (collider.contains(e.getMouseX(), e.getMouseY())) {
			if (!listenOnClick && isClicked)
				new Thread(exec).start();
			isClicked = false;
			return true;
		}
		isClicked = false;
		return false;
		
	}
	
	public void draw(PApplet window) {
		
		if (hasTexture)
			window.shape(collider);
		
	}
	
	public void addListener() {
		
		EventHandler.addClickable(this);
		
	}
	
	public void setOnClick(boolean onClick) {
		
		listenOnClick = onClick;
		
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