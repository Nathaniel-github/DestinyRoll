package destiny.assets;

import destiny.core.PGif;
import processing.core.PApplet;

public class RippleCursor {
	
	private PGif test;
	
	public RippleCursor() {
		
		test = new PGif(100, 100, "res/generalAssets/test.gif");
		
		test.setScale(0.35f);
		test.overrideDelay(1.6);
		
	}
	
	public void draw(PApplet window) {
		
		test.setCoordinates(window.mouseX - test.getWidth() / 2, window.mouseY - test.getHeight() / 2);
		test.draw(window);
		
	}

}
