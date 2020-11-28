package destiny.panels;

import destiny.core.*;

import java.awt.Rectangle;

import destiny.assets.*;
import processing.core.PApplet;
import processing.video.Movie;

public class OpeningScreen implements Screen {

	private Movie corp;
	private FadeVideo background;
	private RippleCursor cursor, cursor2;
	private PButton button;
	
	public OpeningScreen(PApplet window) {
		
		corp = new Movie(window, "res/titleScreen/CorpLogo.mp4");
		background = new FadeVideo(window, "res/titleScreen/BackgroundMovie.mp4");
		cursor = RippleCursor.createHighPerformanceCursor();
		cursor2 = RippleCursor.createLowPerformanceCursor();
		button = new PButton(new Rectangle(0, 0, 1280, 720), false);
		
		corp.volume(0);
		corp.play();
		background.setCoords(0, 0);
		background.resize(1280, 720);
		
	}
	
	@Override
	public void setup() {
		
		button.addListener(new Runnable() {
			@Override
			public void run() {
				System.out.println("It works?");
			}
		});
		
	}

	@Override
	public void draw(PApplet window) {
		
		if (corp.isPlaying()) {
			window.image(corp, 0, 0, 1280, 720);
			
			if (window.mousePressed) {
				cursor.draw(window);
			} else {
				cursor.clearTrail();
			}
		} else {
			if (!background.video.isPlaying()) {
				background.video.loop();
				corp.stop();
			}
			background.draw(window);
			
			if (window.mousePressed) {
				cursor2.draw(window);
			} else {
				cursor2.clearTrail();
			}
		}
		
	}
	
}
