 package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.assets.Player;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.FadeVideo;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * LevelSelect screen is where the user select which level to play in.
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class LevelSelectScreen implements Screen {
	
	private FadeVideo background;
	private RippleCursor cursor;
	private PButton  back;
	private FadeImage prev;
	private PButton[] levelButtons;

	
	@Override
	public void setup(PApplet window) {
		background = new FadeVideo(window, "res/generalAssets/bg.gif");
		background.setFadeSpeed(50);
		background.loop();
		prev = new FadeImage("res/generalAssets/back.png");
		cursor = RippleCursor.createLowPerformanceCursor();
		try {
			back = new PButton(new Rectangle(Constants.scaleIntToWidth(50), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		prev.resize(Constants.scaleIntToWidth(150), Constants.scaleIntToWidth(150));
		prev.setCoords(Constants.scaleIntToWidth(50), Constants.SCREEN_HEIGHT-Constants.scaleIntToHeight(200));
		levelButtons = new PButton[Player.getLevelsUnlocked()];		
		for(int i = 0; i < Player.getLevelsUnlocked(); i++) {
			PButton b;
			int id = i;
			int temp = i + 1;
 			try {
				b = new PButton(new Rectangle(Constants.scaleIntToWidth(250+((i%20%5)*300)), Constants.scaleIntToHeight(100+((i%20/5)*200)), Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(170)),new PImage(ImageIO.read(new File("res/levelSelectScreen/numbers/"+temp+".png"))), false);
				b.addListener(new Runnable() {
					@Override
					public void run() {
						if(Player.getStamina()>=10) {
							background.setFadeSpeed(40);
							background.setTint(255);
							background.setTargetTint(0);
							background.fadeWhite(true);
							Player.setLevel(id+1);
							background.addListener(new Runnable() {
								@Override
								public void run() {
									ScreenManager.setCurrentScreenByName("prep", window);
								}
								
							});
						}else {
							JOptionPane.showMessageDialog(null, "Not enough stamina :////");

						}
					}
				});
				levelButtons[i] = b;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		back.draw(window);
		for(int i = 0; i < levelButtons.length; i++) {
			levelButtons[i].draw(window);
		}
		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}
		window.pushStyle();
		window.textSize(Constants.scaleIntToHeight(40));
		window.stroke(255,0,0);
		window.fill(255,0,0);
		window.strokeWeight(5f);
		window.text(String.valueOf("Stamina: " +Player.getStamina()), Constants.scaleIntToWidth(50), Constants.scaleIntToHeight(50));
		window.popStyle();
	}
	

	@Override
	public void dispose() {
		for(int i = 0; i < levelButtons.length; i++) {
			levelButtons[i].removeListener();
			levelButtons[i] = null;
		}
		background = null;
		cursor = null;
		back.removeListener();
		back = null;
		prev = null;
	}

}
