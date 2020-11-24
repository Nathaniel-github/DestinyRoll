package destiny.core;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class PButton implements ClickEvent {
	
	private PShape collider;
	private Runnable exec;
	private boolean visible;
	private boolean listenOnClick;
	private boolean isClicked = false;
	
	public PButton (PShape collider) {
		
		this.collider = collider; 
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		visible = false;
		listenOnClick = false;
		
	}
	
	public PButton (PShape collider, boolean onClick) {
		
		this.collider = collider; 
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		visible = false;
		listenOnClick = onClick;
		
	}
	
	public PButton (PShape collider, PImage texture) {
		
		this.collider = collider; 
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		collider.setTexture(texture);
		visible = true;
		listenOnClick = false;
		
	}
	
	public PButton (PShape collider, PImage texture, boolean onClick) {
		
		this.collider = collider; 
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		collider.setTexture(texture);
		visible = true;
		listenOnClick = onClick;
		
	}
	
	public PButton () {
		
		this.collider = new PShape();
		this.exec = new Runnable() {
			@Override
			public void run() {}
		};
		
		visible = false;
		listenOnClick = false;
		
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
			window.shape(collider);
		
	}
	
	public void addListener(Runnable code) {
		
		this.exec = code;
		EventHandler.addClickable(this);
		
	}
	
	public void listenOnClick(boolean onClick) {
		
		listenOnClick = onClick;
		
	}
	
	public void setTexture(PImage texture) {
		
		collider.setTexture(texture);
		visible = true;
		
	}
	
	public void setVisibility(boolean visibility) {
		
		this.visible = visibility;
		
	}
	
	public void removeTexture() {
		
		collider.noTexture();
		visible = false;
		
	}
	
	public void removeListener() {
		
		EventHandler.removeClickable(this);
		
	}

}