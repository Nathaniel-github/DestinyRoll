package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * MainScreen is the screen where the user can select which game modes to play or determine which aspect of the game they want to explore
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class MainScreen implements Screen {
	
	private FadeImage background;
	private RippleCursor cursor;
	private PButton button, back;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/mainScreen/big.jpg");
		cursor = RippleCursor.createLowPerformanceCursor();
		button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, 200, 400, 200), false);
		back = new PButton(new Rectangle(0, Constants.SCREEN_HEIGHT-200, 200, Constants.SCREEN_HEIGHT), false);
		try {
			button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, 200, 400, 200), new PImage(ImageIO.read(new File("res/generalAssets/play.png"))), false);
			back = new PButton(new Rectangle(50, Constants.SCREEN_HEIGHT-250, 200, 200), new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	public void draw(PApplet window) {
		background.draw(window);
		button.draw(window);
		back.draw(window);
		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}
		button.addListener(new Runnable() {
			@Override
			public void run() {
				background.setFadeSpeed(40);
				background.setTint(255);
				background.setTargetTint(0);
				background.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("level", window);
					}
					
				});
			}
		});
		back.addListener(new Runnable() {
			@Override
			public void run() {
				background.setFadeSpeed(40);
				background.setTint(255);
				background.setTargetTint(0);
				background.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("home", window);
					}
					
				});
			}
		});
}
	

@Override
public void dispose() {
	background = null;
	cursor = null;
	button.removeListener();
	button = null;

}

}
