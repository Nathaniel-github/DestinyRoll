package destiny.panels;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import destiny.assets.Constants;
import destiny.assets.Character;
import destiny.assets.Player;
import destiny.assets.RippleCursor;
import destiny.assets.StatsBox;
import destiny.core.FadeGif;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.PGif;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import destiny.net.MongoHandler;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * BattlePrepScreen is where the user can select which revolutionaries they want
 * to use for battle
 * 
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class BattlePrepScreen implements Screen {

	private FadeGif background;
	private RippleCursor cursor;
	private PButton button, back;
	private boolean first, second, third, selectFirst, selectSecond, selectThird;
	public static int[] revsSelect;
	private PButton[] select;
	private PButton[] selection;
	private Character[] revs;
	private ArrayList<Integer> unlocked;
	private boolean isHolding;
	private StatsBox stats;
	@Override
	public void setup(PApplet window) {
		background = new FadeGif("res/generalAssets/bg.gif");
		background.setFadeSpeed(50);
		cursor = RippleCursor.createLowPerformanceCursor();
		first = false;
		second = false;
		third = false;
		selectFirst = true;
		selectSecond = false;
		selectThird = false;
		unlocked = Player.getCharacters();
		revsSelect = new int[] { 0, 0, 0 };
		isHolding = false;
		revs = new Character[3];
		try {
			button = new PButton(
					new Rectangle(Constants.SCREEN_WIDTH - Constants.scaleIntToWidth(250),
							Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(125), Constants.scaleIntToWidth(200),
							Constants.scaleIntToWidth(100)),
					new PImage(ImageIO.read(new File("res/generalAssets/play.png"))), false);
			back = new PButton(
					new Rectangle(Constants.scaleIntToWidth(50),
							Constants.SCREEN_HEIGHT - Constants.scaleIntToHeight(250), Constants.scaleIntToWidth(200),
							Constants.scaleIntToHeight(200)),
					new PImage(ImageIO.read(new File("res/generalAssets/back.png"))), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background.setCoords(0, 0);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		select = new PButton[Constants.TOTAL_CHARACTERS];
		selection = new PButton[3];
		for (int i = 0; i < 3; i++) {
			PButton b;
			try {
				b = new PButton(
						new Rectangle(Constants.scaleIntToWidth(100), Constants.scaleIntToHeight(100 + (200 * i)),
								Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200)),
						new PImage(ImageIO.read(new File("res/generalAssets/obama.png"))), false);
				int sel = i;
				b.addListener(new Runnable() {
					@Override
					public void run() {

						if (sel == 0) {
							selectFirst = true;
							selectSecond = false;
							selectThird = false;
						} else if (sel == 1) {
							selectFirst = false;
							selectSecond = true;
							selectThird = false;
						} else if (sel == 2) {
							selectFirst = false;
							selectSecond = false;
							selectThird = true;
						}
					}
				});
				selection[i] = b;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < unlocked.size(); i++) {
			PButton b;
			PGif c;
			int id = unlocked.get(i);
			b = new PButton(
					new Rectangle(Constants.scaleIntToWidth(800 + (i % 20 % 5 * 200)),
							Constants.scaleIntToHeight(100 + (i % 20 / 5 * 200)), Constants.scaleIntToWidth(200),
							Constants.scaleIntToWidth(200)), false);
			c = new PGif(Constants.scaleIntToWidth(800+(i%20%5*200)), Constants.scaleIntToHeight(100+(i%20/5*200)), Constants.getCharacterPath(id, "static"));
			c.resize(Constants.scaleIntToWidth(200), Constants.scaleIntToWidth(200));

			b.setGifTexture(c);
			
			b.addListener(new Runnable() {

				@Override
				public void run() {
					if(revsSelect[0]!=id&&revsSelect[1]!=id&&revsSelect[2]!=id) {
						if (selectFirst) {
							revsSelect[0] = id;
							selection[0].setGifTexture(new PGif(Constants.scaleIntToWidth(120),Constants.scaleIntToHeight(120),Constants.getCharacterPath(id, "static")));
							selectFirst = false;
							selectSecond = true;
							first = true;
						} else if (selectSecond) {
							revsSelect[1] = id;
							selection[1].setGifTexture(new PGif(Constants.scaleIntToWidth(120),Constants.scaleIntToHeight(320),Constants.getCharacterPath(id, "static")));
							selectSecond = false;
							selectThird = true;
							second = true;
						} else if (selectThird) {
							revsSelect[2] = id;
							selection[2].setGifTexture(new PGif(Constants.scaleIntToWidth(120),Constants.scaleIntToHeight(520),Constants.getCharacterPath(id, "static")));
							selectThird = false;
							third = true;
						}
					}
					
				}
			});
			b.addHoldListener(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stats = new StatsBox(MongoHandler.getStatDoc(id),Constants.scaleIntToWidth(200), Constants.scaleIntToHeight(200),Constants.scaleIntToWidth(200),Constants.scaleIntToWidth(600));
				}
				
			});
			b.addHoldReleaseListener(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stats = null;
				}
				
			});
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
						ScreenManager.setCurrentScreenByName("level", window);
					}

				});
			}
		});
	}

	@Override
	public void draw(PApplet window) {
		background.draw(window);
		for(int i = 0; i < 3 ; i++) {
			if(revs[i]!=null) {
				revs[i].draw(window);
			}
		}
		back.draw(window);
		for (int i = 0; i < unlocked.size(); i++) {
			select[i].draw(window);
		}
		for(PButton p : selection) {
			p.setHightlight(false);
		}
		if(stats != null) {
			stats.draw(window);
		}
		if(selectFirst) {
			selection[0].setHightlight(true);
		}else if(selectSecond) {
			selection[1].setHightlight(true);
		}else if(selectThird) {
			selection[2].setHightlight(true);
		}
		for (int i = 0; i < 3; i++) {
			selection[i].draw(window);
		}
		if (first && second && third) {
			if(revsSelect[1]!=revsSelect[2]&&revsSelect[2]!=revsSelect[0]&&revsSelect[1]!=revsSelect[0]) {
				button.addListener(new Runnable() {
					@Override
					public void run() {
						background.setFadeSpeed(40);
						background.setTint(255);
						background.setTargetTint(0);
						background.fadeWhite(true);
						Player.decreaseStamina(10);
						background.addListener(new Runnable() {
							@Override
							public void run() {
								ScreenManager.setCurrentScreenByName("battle", window);
							}

						});
					}
				});
				button.draw(window);
			}
		}

		if (window.mousePressed) {
			cursor.draw(window);
		} else {
			cursor.clearTrail();
		}

	}

	@Override
	public void dispose() {
		for (int i = 0; i < unlocked.size(); i++) {

			select[i].removeListener();
			select[i] = null;

		}
		for (int i = 0; i < selection.length; i++) {

			selection[i].removeListener();
			selection[i] = null;

		}

		background = null;
		cursor = null;
		button.removeListener();
		button = null;
	}

}
