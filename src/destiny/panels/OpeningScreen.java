package destiny.panels;

import destiny.core.*;
import destiny.assets.*;
import processing.core.PApplet;
import processing.video.Movie;

public class OpeningScreen implements Screen {

	private Movie corp;
	private FadeVideo background;
	private RippleCursor cursor;
	
	public OpeningScreen(PApplet window) {
		
		corp = new Movie(window, "res/titleScreen/CorpLogo.mp4");
		background = new FadeVideo(window, "res/titleScreen/BackgroundMovie.mp4");
		cursor = new RippleCursor();
		
		corp.play();
		background.setCoords(0, 0);
		background.setDimensions(1280, 720);
		
	}
	
	@Override
	public void setup() {
		

		
	}

	@Override
	public void draw(PApplet window) {
		
		if (corp.isPlaying())
			window.image(corp, 0, 0, 1280, 720);
		else {
			if (!background.isPlaying())
				background.loop();
			background.draw(window);
		}
		
		if (window.mousePressed)
			cursor.draw(window);
		
	}
	
}
