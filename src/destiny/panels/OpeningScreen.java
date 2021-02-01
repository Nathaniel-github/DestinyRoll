package destiny.panels;

import destiny.core.*;

import java.awt.Rectangle;

import destiny.assets.*;
import processing.core.PApplet;
import processing.video.Movie;

/**
 * 
 * This class represents the opening/startup screen for the game
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class OpeningScreen implements Screen {

	private Movie corp;
	private FadeVideo background;
	private RippleCursor cursor, cursor2;
	private PButton button;
	private FadeGif touchScreen;
	private boolean clicked = false;
	
	public OpeningScreen() {}
	
	@Override
	public void setup(PApplet window) {
		corp = new Movie(window, "res/titleScreen/CorpLogoTEMP.mp4");
		background = new FadeVideo(window, "res/titleScreen/BackgroundMovie.mp4");
		cursor = RippleCursor.createLowPerformanceCursor();
		cursor2 = RippleCursor.createLowPerformanceCursor();
		button = new PButton(new Rectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), false);
		touchScreen = new FadeGif("res/generalAssets/ClickToStart.gif", 0, 255, 0.01f);
		
		touchScreen.scaleByWidth(500);
		touchScreen.setCoordinates(Constants.SCREEN_WIDTH/2 - 250, Constants.SCREEN_HEIGHT - 100 - touchScreen.getHeight()/2);
		corp.volume(0);
		corp.play();
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	@Override
	public void draw(PApplet window) {
		
		if (corp.isPlaying()) {
			window.image(corp, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
			
			if (window.mousePressed) {
				cursor.draw(window);
			} else {
				cursor.clearTrail();
			}
		} else {
			if (!background.isPlaying()) {
				background.loop();
				corp.stop();
				button.addListener(new Runnable() {
					@Override
					public void run() {
						background.setFadeSpeed(40);
						background.setTint(255);
						background.setTargetTint(0);
						background.fadeWhite(true);
						clicked = true;
						background.addListener(new Runnable() {

							@Override
							public void run() {
								
								System.out.println("Yes");
								ScreenManager.setCurrentScreenByName("home", window);
								
							}
							
						});
						button.removeListener();
					}
				});
			}
			background.draw(window);
			
			if (background.getSecondMark() > 5 && !clicked) {
				touchScreen.draw(window);
			}
			
			if (window.mousePressed) {
				cursor2.draw(window);
			} else {
				cursor2.clearTrail();
			}
		}
		
	}

	@Override
	public void dispose() {
		corp = null;
		background.stop();
		background = null;
		cursor = null;
		cursor2 = null;
		button.removeListener();
		button = null;
		touchScreen = null;
	}
	
}
