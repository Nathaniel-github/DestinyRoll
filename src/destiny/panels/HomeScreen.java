package destiny.panels;

import destiny.core.Screen;
import processing.core.PApplet;

public class HomeScreen implements Screen {

	@Override
	public void setup(PApplet window) {
		
		

	}

	@Override
	public void draw(PApplet window) {
		
		window.fill(0);
		window.stroke(255);
		window.rect(0, 0, 200, 200);

	}

	@Override
	public void dispose() {
		
		

	}

}
