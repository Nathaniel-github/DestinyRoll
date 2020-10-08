package destiny.panels;

import destiny.graphicslib.FadeVideo;
import destiny.graphicslib.PGif;
import processing.core.PApplet;
import processing.video.Movie;

public class GraphicsPanel extends PApplet {
	
	FadeVideo myMovie;
	PGif test;

	public void setup() {
		myMovie = new FadeVideo(this, "res/titleScreen/Gurenge.mp4");
		myMovie.loop();
		test = new PGif(20, 20, "res/test.gif");
	}

	public void draw() {
		
		myMovie.draw(this);
		test.draw(this);
		
	}
	
	public void movieEvent(Movie m) {
		m.read();
	}

}
