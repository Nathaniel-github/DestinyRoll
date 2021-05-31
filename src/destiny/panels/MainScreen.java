package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import destiny.core.SoundPlayer;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * MainScreen is the screen where the user can select which game modes to play
 * or determine which aspect of the game they want to explore
 * 
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class MainScreen implements Screen {

	private FadeGif background;
	private RippleCursor cursor;
	private PButton button, back, gacha, gallery;
	@Override
	public void setup(PApplet window) {
		background = new FadeGif("res/generalAssets/bg.gif");
		background.setFadeSpeed(50);
		cursor = RippleCursor.createLowPerformanceCursor();
		try {
			button = new PButton(new Rectangle(Constants.SCREEN_WIDTH - Constants.scaleIntToWidth(500), Constants.scaleIntToHeight(100), Constants.scaleIntToWidth(400), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/play.png"))), false);
			back = new PButton(new Rectangle(Constants.scaleIntToWidth(50), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
			gacha = new PButton(new Rectangle(Constants.SCREEN_WIDTH - Constants.scaleIntToWidth(500), Constants.scaleIntToHeight(400), Constants.scaleIntToWidth(400), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/mainScreen/gacha.png"))), false);
			gallery = new PButton(new Rectangle(Constants.SCREEN_WIDTH - Constants.scaleIntToWidth(500), Constants.scaleIntToHeight(700), Constants.scaleIntToWidth(400), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/mainScreen/gallery.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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
		gacha.addListener(new Runnable() {
			@Override
			public void run() {
				background.setFadeSpeed(40);
				background.setTint(255);
				background.setTargetTint(0);
				background.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("gacha", window);
					}

				});
			}
		});
		gallery.addListener(new Runnable() {
			@Override
			public void run() {
				background.setFadeSpeed(40);
				background.setTint(255);
				background.setTargetTint(0);
				background.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("gallery", window);
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
		gacha.draw(window);
		gallery.draw(window);
		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}
		
	}

	@Override
	public void dispose() {
		background = null;
		cursor = null;
		button.removeListener();
		button = null;
		gacha = null;

	}

}
