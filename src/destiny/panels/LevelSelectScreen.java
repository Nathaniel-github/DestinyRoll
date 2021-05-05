package destiny.panels;

import java.awt.Rectangle;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import processing.core.PApplet;

public class LevelSelectScreen implements Screen {
	
	private FadeImage background;
	private RippleCursor cursor;
	private PButton button, back;
	private FadeImage play, prev;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/levelSelectScreen/210322.jpg");
		play = new FadeImage("res/generalAssets/play.png");
		prev = new FadeImage("res/generalAssets/back.png");
		cursor = RippleCursor.createLowPerformanceCursor();
		button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, 200, 400, 200), false);
		back = new PButton(new Rectangle(0, Constants.SCREEN_HEIGHT-200, 400, Constants.SCREEN_HEIGHT), false);
		background.setCoords(0, 0);
		play.resize(400, 200);
		play.setCoords(Constants.SCREEN_WIDTH-500, 200);
		prev.resize(400, 200);
		prev.setCoords(0, Constants.SCREEN_HEIGHT-200);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	public void draw(PApplet window) {
		background.draw(window);
		play.draw(window);
		prev.draw(window);
		
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
				prev.setFadeSpeed(40);
				prev.setTint(255);
				prev.setTargetTint(0);
				prev.fadeWhite(true);
				background.addListener(new Runnable() {

					@Override
					public void run() {
						ScreenManager.setCurrentScreenByName("prep", window);
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
