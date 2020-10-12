package destiny.core;
import processing.core.PApplet;

public class FadeImage extends Fader {
	
	public FadeImage(PApplet window, String pathname) {
		
		super(0, 255, 0.2f, 0, 0, 0 ,0, true);
		myImage = window.loadImage(pathname);
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, width, height, false);
		myImage = window.loadImage(pathname);
		
	}
	
	public FadeImage(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, 0 ,0, true);
		myImage = window.loadImage(pathname);
		
	}

}
