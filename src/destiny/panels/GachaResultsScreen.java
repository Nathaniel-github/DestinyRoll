package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import destiny.assets.Constants;
import destiny.assets.Player;
import destiny.assets.RippleCursor;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.FadeVideo;
import destiny.assets.Character;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import destiny.core.SoundPlayer;
import destiny.net.MongoHandler;
import processing.core.PApplet;
import processing.core.PImage;
import org.bson.Document;


/**
 * 
 * GachaResultsScreen is the panel that handles drawing everything that shows once you draw a character.
 * It is also in charge of handling all events that can take place on this screen
 * 
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class GachaResultsScreen implements Screen {

	private FadeGif background;
	private FadeVideo animation;
	private RippleCursor cursor;
	private PButton back;
	private Character result;
	private Document doc;
	private boolean rekt;
	private SoundPlayer buildUp, finish;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeGif("res/generalAssets/bg.gif");
		background.setFadeSpeed(50);
		buildUp = new SoundPlayer(Constants.getSoundPath("zelda.wav"));
		finish = new SoundPlayer(Constants.getSoundPath("zelda2.wav"));
		animation = new FadeVideo(window, "res/gachaScreen/roll.mp4");
		animation.fadeWhite(true);
		animation.setFadeSpeed(50);
		animation.play();
		rekt = false;
		animation.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
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
		doc = MongoHandler.rollRandomCharacter(Player.getUserName());
		if(doc != null) {
			result = new Character( doc.getInteger("_id"));
			
			result.setCoords(Constants.scaleIntToWidth(800), Constants.scaleIntToHeight(400));

		}else {
			rekt = true;
		}
	}

	@Override
	public void draw(PApplet window) {
		if (animation.isPlaying()) {
			if (!buildUp.isPlaying())
				buildUp.play();
			animation.draw(window);
		} else {
			if (buildUp.isPlaying())
				buildUp.stop();
			if (!finish.isPlaying())
				finish.play();
			background.draw(window);
			back.draw(window);
			if(result!=null)
				result.draw(window);
			if (window.mousePressed) {
				cursor.draw(window);
			} else {
				cursor.clearTrail();
			}
			if(rekt) {
				window.pushStyle();
				window.textSize(50);
				window.stroke(255,0,0);
				window.fill(255,0,0);
				window.text("get rekt", Constants.scaleIntToWidth(400), Constants.scaleIntToHeight(500));
				window.popStyle();
			}
		}
	}

	@Override
	public void dispose() {
		background = null;
		result = null;
		cursor = null;	
		buildUp = null;
		finish = null;
	}

}
