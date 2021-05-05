package destiny.panels;

import java.awt.Rectangle;

import destiny.assets.Constants;
import destiny.assets.RippleCursor;
import destiny.core.FadeImage;
import destiny.core.PButton;
import destiny.core.Screen;
import destiny.core.ScreenManager;
import processing.core.PApplet;

public class HomeScreen implements Screen {

	private FadeImage background;
	private RippleCursor cursor;
	private PButton button;
	private FadeImage play;
	
	@Override
	public void setup(PApplet window) {
		background = new FadeImage("res/homeScreen/unknown.png");
		play = new FadeImage("res/generalAssets/play.png");
		cursor = RippleCursor.createLowPerformanceCursor();
		button = new PButton(new Rectangle(Constants.SCREEN_WIDTH-500, 200, 400, 200), false);		
		background.setCoords(0, 0);
		play.resize(400, 200);
		play.setCoords(Constants.SCREEN_WIDTH-500, 200);
		background.resize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	@Override
	public void draw(PApplet window) {
			background.draw(window);
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
	}
		

	@Override
	public void dispose() {
		background = null;
		cursor = null;
		button.removeListener();
		button = null;
		play = null;
	}

}
