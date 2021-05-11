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
 * HomeScreen is the screen right after the opening screen
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class HomeScreen implements Screen {

	private FadeImage background;
	private RippleCursor cursor;
	private PButton button;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/homeScreen/210322.jpg");
		cursor = RippleCursor.createLowPerformanceCursor();
		try {
			button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, 200, 400, 200),new PImage(ImageIO.read(new File("res/generalAssets/play.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	@Override
	public void draw(PApplet window) {
			background.draw(window);
			button.draw(window);
			
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
							ScreenManager.setCurrentScreenByName("main", window);
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
