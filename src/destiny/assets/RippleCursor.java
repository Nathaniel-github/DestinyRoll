package destiny.assets;

/**
 * 
 * A class that handles all the functionality of a ripple that will follow the user's
 * cursor around
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
import java.util.ArrayList;
import java.util.Iterator;

import destiny.core.FadeGif;
import destiny.core.PGif;
import processing.core.PApplet;

public class RippleCursor {
	
	private PGif ripple;
	private ArrayList<FadeGif> trail;
	private long count = 0;
	private int highPerformanceBarrier, lowPerformanceBarrier;
	
	private RippleCursor(int highPerfBarrier, int lowPerfBarrier) {
		
		ripple = new PGif(100, 100, "res/generalAssets/ripple.gif");
		
		trail = new ArrayList<>();
		ripple.scale(0.35f);
		ripple.overrideDelay(2);
		ripple.stopLooping();
		
		this.highPerformanceBarrier = highPerfBarrier;
		this.lowPerformanceBarrier = lowPerfBarrier;
		
	}
	
	public static RippleCursor createLowPerformanceCursor() {
		
		return new RippleCursor(5, 2);
		
	}
	
	public static RippleCursor createHighPerformanceCursor() {
		
		return new RippleCursor(13, 7);
		
	}
	
	public void draw(PApplet window) {
		
		ripple.setCoordinates(window.mouseX - ripple.getWidth() / 2, window.mouseY - ripple.getHeight() / 2);
		
		if (count % ((trail.size() > 9 || (Math.abs(window.pmouseX - window.mouseX) < 5 && Math.abs(window.pmouseY - window.mouseY) < 5) ) ? highPerformanceBarrier : lowPerformanceBarrier) == 0) {
			FadeGif temp = new FadeGif(ripple, 255, 0, (int)(60/(highPerformanceBarrier)));
			temp.fadeWhite(true);
			trail.add(temp);
		}
		count++;
		
		for (Iterator<FadeGif> iterator = trail.iterator(); iterator.hasNext();) {
			FadeGif i = iterator.next();
			if (!i.isFinished())
				i.draw(window);
			else
				iterator.remove();
		}
		
	}
	
	public void clearTrail() {
		
		if (trail.size() > 0)
			trail.clear();
		
	}

}
