package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import destiny.assets.Constants;
import destiny.assets.Player;
import destiny.assets.RippleCursor;
import destiny.assets.StatsBox;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.FadeVideo;
import destiny.core.PButton;
import destiny.core.PGif;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import destiny.net.MongoHandler;
import processing.core.PApplet;
import processing.core.PImage;
import destiny.assets.Character;

/**
 * GalleryScreen is the panel that draws and handles all events related to the character gallery
 * 
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class GalleryScreen implements Screen {
	
	private FadeVideo background;
	private RippleCursor cursor;
	private PButton back;
	private PButton[] select;
	private Character rev;
	private PGif[] revs;
	private ArrayList<Integer> unlocked;
	private StatsBox stats;
	@Override
	public void setup(PApplet window) {
		background = new FadeVideo(window, "res/levelSelectScreen/levels.mp4");
		background.setFadeSpeed(50);
		background.loop();

		cursor = RippleCursor.createLowPerformanceCursor();
		unlocked = Player.getCharacters();

		rev = null;
		try {
			back = new PButton(new Rectangle(Constants.scaleIntToWidth(50), Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(200), Constants.scaleIntToHeight(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		select = new PButton[unlocked.size()];
		revs = new PGif[unlocked.size()];
		for(int i = 0; i < unlocked.size(); i++) {
			PButton b;
			PGif c;
			int id = unlocked.get(i);
			b = new PButton(new Rectangle(Constants.scaleIntToWidth(800+(i%20%5*200)), Constants.scaleIntToHeight(100+(i%20/5*200)), Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200)), false);
			c = new PGif(Constants.scaleIntToWidth(800+(i%20%5*200)), Constants.scaleIntToHeight(100+(i%20/5*200)), Constants.getCharacterPath(id, "static"));
			c.resize(Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200));
			b.addListener(new Runnable() {
				@Override
				public void run() {
					rev = new Character(id);
					rev.setCoords(50, 50);
					stats = new StatsBox(MongoHandler.getStatDoc(id),Constants.scaleIntToWidth(200), Constants.scaleIntToHeight(440),Constants.scaleIntToWidth(200),Constants.scaleIntToWidth(600));

				}
			});
			b.setGifTexture(c);
			select[i] = b;

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
		for(int i = 0; i < unlocked.size(); i++) {
			select[i].draw(window);
		}
		if(rev!=null) {
			rev.draw(window);
		}
		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}	
		if(stats!=null) {
			stats.draw(window);
		}
	}
	

	@Override
	public void dispose() {
		for (int i = 0; i < select.length; i ++) {		
			select[i].removeListener();	
		}
		select = null;	
		background = null;
		cursor = null;

	}

}
