package destiny.core;
import processing.core.PApplet;
import processing.core.PImage;

public class FadeImage extends Fader {
	
	public PImage myImage;
	private int x, y;
	
	public FadeImage(PApplet window, String pathname) {
		
		super(0, 255, 0.2f);
		myImage = window.loadImage(pathname);
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		myImage = window.loadImage(pathname);
		x = xCord;
		y = yCord;
		myImage.resize(width, height);
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		myImage = window.loadImage(pathname);
		
	}
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		window.image(myImage, x, y);
		
		window.popStyle();
		
	}
	
	public void setCoords(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}

}
