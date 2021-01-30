package destiny.core;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class uses the existing PGif class and adds fading functionality to it
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class FadeGif extends Fader {
	
	private PGif myGif;
	
	public FadeGif(String pathname) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	public FadeGif(String pathname, int x, int y) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(x, y, pathname);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	public FadeGif(String pathname, int x, int y, int width, int height) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(x, y, pathname);
		myGif.resize(width, height);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(xCord, yCord, pathname);
		myGif.resize(width, height);
		
	}
	
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(xCord, yCord, pathname);
		
	}
	
	public FadeGif(PGif gif) {
		
		super(0, 255, 0.2f);
		myGif = gif.copy();
		
	}
	
	public FadeGif(PGif gif, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = gif.copy();
		
	}
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		myGif.draw(window);
		
		window.popStyle();
		
	}
	
	public PImage getCurrentImage() {
		
		return myGif.getCurrentImage();
		
	}
	
	public void setCoordinates(int xCord, int yCord) {
		
		myGif.setCoordinates(xCord, yCord);
		
	}
	
	public void translate(int xShift, int yShift) {
		
		myGif.translate(xShift, yShift);
		
	}
	
	public int getWidth() {
		
		return myGif.getWidth();
		
	}
	
	public int getHeight() {
		
		return myGif.getHeight();
		
	}
	
	public boolean isFinished() {
		
		return  myGif.isFinished();
		
	}
	
	public void resize(int w, int h) {
		
		myGif.resize(w, h);
		
	}
	
	public void scaleByWidth(int width) {
		
		myGif.scaleByWidth(width);
		
	}
	
	public void scale(float s) {
		
		myGif.scale(s);
		
	}
	
	public void stopLooping() {
		
		myGif.stopLooping();
		
	}
	
	public void startLooping() {
		
		myGif.startLooping();
		
	}
	
	public void playOnce() {
		
		myGif.playOnce();
		
	}
	
	public void restart() {
		
		myGif.restart();
		
	}
	
	public void overrideDelay(double delay) {
		
		myGif.overrideDelay(delay);
		
	}
	
	public void defaultDelay() {
		
		myGif.defaultDelay();
		
	}
	
	public PGif copy() {
		
		return myGif.copy();
		
	}

}
