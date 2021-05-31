package destiny.assets;


import java.util.ArrayList;

import java.util.Iterator;

import destiny.core.FadeGif;
import destiny.core.PGif;
import processing.core.PApplet;

/**
 * 
 * A class that handles all the functionality of a ripple that will follow the user's
 * cursor around
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class RippleCursor {
	
	private PGif ripple;
	private ArrayList<FadeGif> trail;
	private long count = 0;
	private int highPerformanceBarrier, lowPerformanceBarrier;
	
	private RippleCursor(int highPerfBarrier, int lowPerfBarrier) {
		
		ripple = new PGif(0, 0, "res/generalAssets/ripple.gif");
		
		trail = new ArrayList<>();
		ripple.scale(0.35f);
		ripple.overrideDelay(2);
		ripple.stopLooping();
		
		this.highPerformanceBarrier = highPerfBarrier;
		this.lowPerformanceBarrier = lowPerfBarrier;
		
	}
	
	/**
	 * 
	 * Creates a RippleCursor object with a smaller amount of time between ripples
	 * 
	 * @return The RippleCursor object. It will have much more ripples than a high performance cursor
	 */
	public static RippleCursor createLowPerformanceCursor() {
		
		return new RippleCursor(4, 2);
		
	}
	
	/**
	 * 
	 * Creates a RippleCursor object with a larger amount of time between ripples
	 * 
	 * @return The RippleCursor object. It will have much less ripples than a low performance cursor
	 */
	public static RippleCursor createHighPerformanceCursor() {
		
		return new RippleCursor(13, 7);
		
	}
	
	/**
	 * 
	 * Draws the ripple cursor and its trail to the screen
	 * 
	 * @param window The PApplet that should have the cursor drawn to it
	 * @post The given PApplet will have the cursor drawn to it
	 */
	public void draw(PApplet window) {
		
		ripple.setCoords(window.mouseX - ripple.getWidth() / 2, window.mouseY - ripple.getHeight() / 2);
		
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
	
	/**
	 * 
	 * Clears the trail following the ripple cursor
	 * 
	 */
	public void clearTrail() {
		
		if (!trail.isEmpty())
			trail.clear();
		
	}

}
