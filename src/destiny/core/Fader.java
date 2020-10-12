package destiny.core;
import processing.core.PApplet;
import processing.core.PImage;

abstract class Fader {
	
	protected PImage myImage;
	
	private float tint;
	private float targetTint;
	private float fadeSpeed;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean fullScreen;
	private boolean isFading = true;
	
	public Fader(float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height, boolean fullScreen) {
		
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		x = xCord;
		y = yCord;
		this.width = width;
		this.height = height;
		this.fullScreen = fullScreen;
		
	}
	
	public void draw(PApplet window) {
		
		window.pushStyle();
		window.tint((float)tint);
		int width = fullScreen ? window.width : this.width;
		int height = fullScreen ? window.height : this.height;
		window.image(myImage, x, y, width, height);
		window.popStyle();
		
		tick();
		
	}
	
	protected void tick() {
		
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
	
	public void setTint(float tint) {
		
		this.tint = tint;
		setTargetTint(tint);
		
	}
	
	public void setTargetTint(float target) {
		
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

	public float getTint() {
		
		return tint;
		
	}
	
	public int getWidth() {
		
		return width;
		
	}
	
	public int getHeight() {
		
		return height;
		
	}
	
	public boolean isFullScreen() {
		
		return fullScreen;
		
	}
	
	public int getX() {
		
		return x;
		
	}
	
	public int getY() {
			
		return y;
		
	}
	
}
