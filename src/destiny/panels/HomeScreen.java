package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.FadeVideo;
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

	private FadeVideo background;
	private RippleCursor cursor;
	private PButton button;
	private PButton setting;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeVideo(window, "res/generalAssets/impact.mp4");
		background.setFadeSpeed(50);
		cursor = RippleCursor.createLowPerformanceCursor();
		try {
			button = new PButton(new Rectangle(Constants.SCREEN_WIDTH - Constants.scaleIntToWidth(500), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(300), Constants.scaleIntToWidth(400), Constants.scaleIntToWidth(200)),new PImage(ImageIO.read(new File("res/generalAssets/play.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			setting = new PButton(
					new Rectangle(Constants.scaleIntToWidth(50),
							Constants.scaleIntToHeight(50), Constants.scaleIntToWidth(100),
							Constants.scaleIntToHeight(100)),
					new PImage(ImageIO.read(new File("res/generalAssets/setting.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setting.addListener(new Runnable() {

			@Override
			public void run() {
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Select the song you would like to listen to:");
				panel.add(label);
				String[] options = new String[] { "Again", "Air", "COLORS", "Grand Escape", "JJK", "One Last Kiss", "My Truth", "Sparkle" };
				int choice = JOptionPane.showOptionDialog(null, panel, "Music Selection", JOptionPane.NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (choice == 0) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("again.mp3"));

				} else if (choice == 1) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("air.mp3"));

				} else if (choice == 2) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("colors.mp3"));

				} else if (choice == 3) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("grandEscape.mp3"));

				} else if (choice == 4) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("JJK.mp3"));

				} else if (choice == 5) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("oneLastKiss.mp3"));

				} else if (choice == 6) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("rondo.mp3"));

				} else if (choice == 7) {

					Window.sound.close();
					Window.loopSound(Constants.getSoundPath("sparkle.mp3"));

				}
				
			}
			
		});
		
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		background.loop();
	}

	@Override
	public void draw(PApplet window) {
			background.draw(window);
			button.draw(window);
			window.pushStyle();
			window.stroke(255, 255, 255);
			window.textSize(Constants.scaleIntToWidth(70));
			window.text("Welcome To Destiny Roll!", Constants.SCREEN_WIDTH/2 - Constants.scaleIntToWidth(400), Constants.SCREEN_HEIGHT/3);
			window.textSize(Constants.scaleIntToWidth(30));
			window.text("By: Nathaniel Thomas and Jay Paek", Constants.SCREEN_WIDTH/2 - Constants.scaleIntToWidth(210), Constants.SCREEN_HEIGHT/3 + Constants.scaleIntToHeight(100));
			window.popStyle();
			if (window.mousePressed) {
				cursor.draw(window);
			} else {
				cursor.clearTrail();
			}
			setting.draw(window);
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
