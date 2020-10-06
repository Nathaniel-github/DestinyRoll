package destiny.graphicslib;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Fader {
	
	protected PImage myImage;
	
	protected float tint;
	protected float targetTint;
	protected float fadeSpeed;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected boolean fullScreen;
	private boolean isFading = true;
	
	public void draw(PApplet window) {
		
		window.pushStyle();
		window.tint((float)tint);
		int width = fullScreen ? window.width : this.width;
		int height = fullScreen ? window.height : this.height;
		window.image(myImage, x, y, width, height);
		window.popStyle();
		
		tick();
		
	}
	
	private void tick() {
		
		if (!isFading)
			return;
		
		if (tint == targetTint) {
			
			isFading = false;
			return;
			
		}
		
		if (tint < targetTint) {
			
			tint += fadeSpeed;
			
			if (tint > targetTint) {
				
				tint = targetTint;
				
			}
			
		} else {
			
			tint -= fadeSpeed;
			
			if (tint < targetTint) {
				
				tint = targetTint;
				
			}
			
		}
		
	}
	
	public void stopFade() {
		
		isFading = false;
		
	}
	
	public void setTargetFade(float target) {
		
		targetTint = target;
		isFading = true;
		
	}
	
	public void setFadeSpeed(float speed) {
		
		fadeSpeed = speed;
		
	}
	
	public void setCoords(int xCord, int yCord) {
		
		x = xCord;
		y = yCord;
		
	}
	
	public void translate(int xShift, int yShift) {
		
		x += xShift;
		y += yShift;
		
	}
	
	public void setDimensions(int width, int height) {
		
		this.width = width;
		this.height = height;
		fullScreen = false;
		
	}
	
	public void fullScreen() {
		
		fullScreen = true;
		
	}

	
}
