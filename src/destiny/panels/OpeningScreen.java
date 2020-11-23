package destiny.panels;

import destiny.core.FadeVideo;
import destiny.core.Screen;
import processing.core.PApplet;
import processing.video.Movie;

public class OpeningScreen implements Screen {

	private Movie corp;
	private FadeVideo background;
	
	public OpeningScreen(PApplet window) {
		
		corp = new Movie(window, "res/titleScreen/CorpLogo.mp4");
		background = new FadeVideo(window, "res/titleScreen/BackgroundMovie.mp4");
		
	}
	
	@Override
	public void setup() {
		
		corp.play();
		background.setCoords(0, 0);
		background.setDimensions(1280, 720);
		
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
		
	}
	
}
