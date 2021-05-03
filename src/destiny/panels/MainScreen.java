package destiny.panels;

import destiny.core.FadeImage;
import destiny.core.Screen;
import processing.core.PApplet;

public class MainScreen implements Screen {
	
	private FadeImage background;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/homeScreen/unknown.png");
		background.setCoords(0, 0);

	}

	@Override
	public void draw(PApplet window) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
