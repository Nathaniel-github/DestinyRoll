package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import destiny.assets.Constants;
import destiny.assets.Player;
import destiny.assets.RippleCursor;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Gacha Screen is a class that draws everything related to the banner and handles anything related to drawing characters
 * 
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class GachaScreen implements Screen {

	private FadeImage background;
	private RippleCursor cursor;
	private PButton button, back;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/gachaScreen/sparkle.jpg");
		cursor = RippleCursor.createLowPerformanceCursor();
		try {
			button = new PButton(new Rectangle(Constants.SCREEN_WIDTH/2 - Constants.scaleIntToWidth(200), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(400), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/gachaScreen/roll.png"))), false);
			back = new PButton(new Rectangle(Constants.scaleIntToWidth(50), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		button.addListener(new Runnable() {
			@Override
			public void run() {
				if(Player.getCurrency()<50) {
					JOptionPane.showMessageDialog(null, "Not enough money :////");

				}else {
					background.setFadeSpeed(40);
					background.setTint(255);
					background.setTargetTint(0);
					background.fadeWhite(true);
					Player.useCurrency(50);
					background.addListener(new Runnable() {
	
						@Override
						public void run() {
							ScreenManager.setCurrentScreenByName("gachaResult", window);
						}
	
					});
				}
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
						ScreenManager.setCurrentScreenByName("main", window);
					}

				});
			}
		});
	}

	@Override
	public void draw(PApplet window) {
		background.draw(window);
		button.draw(window);
		back.draw(window);
		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}
		window.pushStyle();
		window.textSize(Constants.scaleIntToHeight(40));
		window.strokeWeight(5f);
		window.text(String.valueOf("Gems: " +Player.getCurrency()),  Constants.scaleIntToWidth(50), Constants.scaleIntToHeight(50));
		window.popStyle();
	}

	@Override
	public void dispose() {
		background = null;
		cursor = null;
		button.removeListener();
		button = null;

	}

}
