package destiny.core;

import processing.core.PApplet;

/**
 * 
 * This class uses the existing PGif class and adds fading functionality to it
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class FadeGif extends Fader {
	
	public PGif myGif;
	
	public FadeGif(String pathname) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(xCord, yCord, pathname);
		myGif.resize(width, height);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(xCord, yCord, pathname);
		
	}
	
	public FadeGif(PGif gif) {
		
		super(0, 255, 0.2f);
		myGif = gif.copy();
		
	}
	
	public FadeGif(PGif gif, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = gif.copy();
		
	}
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		myGif.draw(window);
		
		window.popStyle();
		
	}

}
