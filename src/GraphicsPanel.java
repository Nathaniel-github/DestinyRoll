import processing.core.PApplet;
import processing.video.Movie;

public class GraphicsPanel extends PApplet {
	
	FadeVideo myMovie;

	public void setup() {
		myMovie = new FadeVideo(this, "res/titleScreen/Gurenge.mp4");
		myMovie.loop();
	}

	public void draw() {
		
		myMovie.draw(this);
		
	}
	
	public void movieEvent(Movie m) {
		m.read();
	}

}
