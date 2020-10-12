package destiny.panels;

import destiny.core.*;
import processing.core.PApplet;
import processing.video.Movie;

public class Window extends PApplet {
	
	FadeVideo myMovie;
	PGif test;

	public void setup() {
		myMovie = new FadeVideo(this, "res/titleScreen/Gurenge.mp4");
		myMovie.loop();
		test = new PGif(100, 100, "res/test.gif");
		test.stopLooping();
		test.playOnce();
	}

	public void draw() {
		this.clear();
		myMovie.draw(this);
		test.draw(this);
	}
	
	public void movieEvent(Movie m) {
		m.read();
	}
	
	public void mousePressed() {
		EventHandler.notifyClickables(this);
	}
	
}
