package destiny.panels;

import destiny.core.*;
import processing.core.PApplet;
import processing.video.Movie;

public class OpeningScreen implements Screen {

	private Movie corp;
	private FadeVideo background;
	private PGif test;
	
	public OpeningScreen(PApplet window) {
		
		corp = new Movie(window, "res/titleScreen/CorpLogo.mp4");
		background = new FadeVideo(window, "res/titleScreen/BackgroundMovie.mp4");
		test = new PGif(100, 100, "res/test.gif");
		
	}
	
	@Override
	public void setup() {
		
		corp.play();
		background.setCoords(0, 0);
		background.setDimensions(1280, 720);
		test.stopLooping();
		
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
		test.draw(window);
		
	}
	
}
