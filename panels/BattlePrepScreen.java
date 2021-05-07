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
 * BattlePrepScreen is where the user can select which revolutionaries they want to use for battle
 * @author Jay Paek
 * @version 5/7/2021
 *
 */
public class BattlePrepScreen implements Screen {
	
	private FadeImage background;
	private RippleCursor cursor;
	private PButton button, back;
	private FadeImage play, prev;
	private boolean first, second, third, selectFirst, selectSecond, selectThird;
	private int[] revs;
	private PButton[] select;
	private PButton[] selection;
	private int page;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/battlePrepScreen/nathaniel.PNG");
		play = new FadeImage("res/generalAssets/play.png");
		prev = new FadeImage("res/generalAssets/back.png");
		cursor = RippleCursor.createLowPerformanceCursor();
		first = false;
		second = false;
		third = false;
		selectFirst = true;
		selectSecond = false;
		selectThird = false;
		page = 1;
		revs = new int[] {-1,-2,-3};
		button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, Constants.SCREEN_HEIGHT-300, Constants.SCREEN_WIDTH-100, Constants.SCREEN_HEIGHT-100), false);
		back = new PButton(new Rectangle(0, Constants.SCREEN_HEIGHT-200, 200, Constants.SCREEN_HEIGHT), false);
		background.setCoords(0, 0);
		play.resize(400, 200);
		play.setCoords(Constants.SCREEN_WIDTH-500, Constants.SCREEN_HEIGHT-300);
		prev.resize(150, 150);
		prev.setCoords(50, Constants.SCREEN_HEIGHT-200);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		select = new PButton[Constants.TOTAL_CHARACTERS];
		selection = new PButton[3]; 


		for(int i = 0; i < Constants.TOTAL_LEVELS; i++) {
			PButton b;
			int id = i+1;
			try {
				b = new PButton(new Rectangle(800+(i%20%5*200), 100+(i%20/5*200), 200, 200), new PImage(ImageIO.read(new File("res/generalAssets/obama.png"))), false);
				b.addListener(new Runnable() {
					@Override
					public void run() {
						if(selectFirst) {
							revs[0] = id;
							selectFirst = false;
							selectSecond = true;
							first = true;
						} else if(selectSecond) {
							revs[1] = id;
							selectSecond = false;
							selectThird = true;
							second = true;
						}
						else if(selectThird) {
							revs[2] = id;
							selectThird = false;
							third = true;
						}
						System.out.println(""+revs[0]+revs[1]+revs[2]);
					}
				});
				select[i] = b;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		for(int i = 0; i < 3; i++) {
			PButton b;
			try {
				b = new PButton(new Rectangle(100, 100+(200*i), 200, 200),new PImage(ImageIO.read(new File("res/generalAssets/obama.png"))), false);
				int sel = i;
				b.addListener(new Runnable() {
					@Override
					public void run() {
						if(sel == 0) {
							selectFirst = true;
							selectSecond = false;
							selectThird = false;
						} else if(sel == 1) {
							selectFirst = false;
							selectSecond = true;
							selectThird = false;
						}
						else if(sel == 2	) {
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
	}

	public void draw(PApplet window) {
		background.draw(window);
		
		prev.draw(window);
		for(int i = 20*(page-1); i < 20*page; i++) {
			select[i].draw(window);
		}
		for(int i =0; i < 3; i++) {
			selection[i].draw(window);
		}
		if(first&&second&&third)
			play.draw(window);
		
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
				play.setFadeSpeed(40);
				play.setTint(255);
				play.setTargetTint(0);
				play.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("main", window);
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
				play.setFadeSpeed(40);
				play.setTint(255);
				play.setTargetTint(0);
				play.fadeWhite(true);
				prev.setFadeSpeed(40);
				prev.setTint(255);
				prev.setTargetTint(0);
				prev.fadeWhite(true);
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
	public void dispose() {
		background = null;
		cursor = null;
		button.removeListener();
		button = null;
		prev = null;
	
	}

}
