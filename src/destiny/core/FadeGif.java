package destiny.core;

import processing.core.PApplet;

public class FadeGif extends Fader {
	
	private PGif myGif;
	
	public FadeGif(String pathname) {
		
		super(0, 255, 0.2f, 0, 0, 0 ,0, true);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, width, height, false);
		myGif = new PGif(xCord, yCord, pathname);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, 0 ,0, true);
		myGif = new PGif(xCord, yCord, pathname);
		
	}
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		window.tint((float)getTint());
		int width = isFullScreen() ? window.width : getWidth();
		int height = isFullScreen() ? window.height : getWidth();
		myGif.resize(width, height);
		myGif.draw(window);
		window.popStyle();
		
		tick();
		
	}

}
