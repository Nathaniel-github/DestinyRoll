package destiny.graphicslib;
import processing.core.PApplet;

public class FadeImage extends Fader {
	
	public FadeImage(PApplet window, String pathname) {
		
		myImage = window.loadImage(pathname);
		tint = 0;
		this.targetTint = 255;
		this.fadeSpeed = 0.2f;
		x = 0;
		y = 0;
		fullScreen = true;
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		myImage = window.loadImage(pathname);
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		x = xCord;
		y = yCord;
		this.width = width;
		this.height = height;
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		myImage = window.loadImage(pathname);
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		x = xCord;
		y = yCord;
		fullScreen = true;
		
	}

}
