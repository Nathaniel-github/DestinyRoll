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
	
	/**
	 * 
	 * Create a FadeGif by retrieving a GIF from a path placed at (0,0) with no starting tint and a target tint of pure black
	 * 
	 * @param pathname The location of the gif file
	 */
	public FadeGif(String pathname) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	/**
	 * Create a FadeGif by retrieving a GIF from a path and places it at the desired top left corner
	 * 
	 * @param pathname The location of the gif file
	 * @param x The x coordinate of the top left corner of where the gif should be placed
	 * @param y The y coordinate of the top left corner of where the gif should be placed
	 */
	public FadeGif(String pathname, int x, int y) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(x, y, pathname);
		
	}
	
	/**
	 * 
	 * Create a FadeGif by retrieving a GIF from a path and fade speed and opacities placed at (0,0)
	 * 
	 * @param pathname The location of the gif file
	 * @param startTint The starting tint of the FadeGif
	 * @param targetTint The target tint of the FadeGif
	 * @param fadeSpeed The speed at which the gif should fade at. This is the value the tint is changed by every frame
	 */
	public FadeGif(String pathname, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(0, 0, pathname);
		
	}
	
	/**
	 * 
	 * Create a FadeGif by retrieving a GIF from a path and desired dimension of the GIF 
	 * 
	 * @param pathname The location of the gif file
	 * @param x The x coordinate of the top left corner of where the gif should be placed
	 * @param y The y coordinate of the top left corner of where the gif should be placed
	 * @param width The desired width of the gif
	 * @param height The desired height of the gif
	 */
	public FadeGif(String pathname, int x, int y, int width, int height) {
		
		super(0, 255, 0.2f);
		myGif = new PGif(x, y, pathname);
		myGif.resize(width, height);
		
	}
	
	/**
	 * 
	 * Create a FadeGif by retrieving a GIF from a path and desired dimension of the GIF and fade speed and opacities 
	 * 
	 * @param pathname The location of the gif file
	 * @param x The x coordinate of the top left corner of where the gif should be placed
	 * @param y The y coordinate of the top left corner of where the gif should be placed
	 * @param width The desired width of the gif
	 * @param height The desired height of the gif
	 * @param startTint The starting tint of the FadeGif
	 * @param targetTint The target tint of the FadeGif
	 * @param fadeSpeed The speed at which the gif should fade at. This is the value the tint is changed by every frame
	 */
	public FadeGif(String pathname, int x, int y, int width, int height, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(x, y, pathname);
		myGif.resize(width, height);
		
	}
	
	/**
	 * 
	 * Create a FadeGif by retrieving a GIF from a path and fade speed and opacities and top left corner coordinates
	 * 
	 * @param pathname The location of the gif file
	 * @param x The x coordinate of the top left corner of where the gif should be placed
	 * @param y The y coordinate of the top left corner of where the gif should be placed
	 * @param startTint The starting tint of the FadeGif
	 * @param targetTint The target tint of the FadeGif
	 * @param fadeSpeed The speed at which the gif should fade at. This is the value the tint is changed by every frame
	 */
	public FadeGif(String pathname, int x, int y, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = new PGif(x, y, pathname);
		
	}
	
	/**
	 * 
	 * Create a FadeGif from PGif object
	 * 
	 * @param gif The gif that should be copied from
	 */
	public FadeGif(PGif gif) {
		
		super(0, 255, 0.2f);
		myGif = gif.copy();
		
	}
	
	/**
	 * 
	 * Creates a FadeGif with the given specifications starting from an already existing gif
	 * 
	 * @param gif The gif that should be copied from
	 * @param startTint The starting tint of the FadeGif
	 * @param targetTint The target tint of the FadeGif
	 * @param fadeSpeed The speed at which the gif should fade at. This is the value the tint is changed by every frame
	 */
	public FadeGif(PGif gif, float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		myGif = gif.copy();
		
	}
	
	@Override
	/**
	 * 
	 * Draws the gif to the given screen with the applied fade
	 * @param window The PApplet that the gif should be drawn to
	 * @post The given PApplet will have a tint applied to it and the gif drawn to it
	 * 
	 */
	public void draw(PApplet window) {
		
		super.draw(window);
		
		myGif.draw(window);
		
	}
	
	/**
	 * 
	 * Gets the current image frame of the gif
	 * 
	 * @return The current image from of the gif
	 */
	public PImage getCurrentImage() {
		
		return myGif.getCurrentImage();
		
	}
	
	/**
	 * Sets top left coordinate of the gif 
	 * @param xCord The x coordinate of the top left
	 * @param yCord The y coordinate of the top left
	 */
	public void setCoords(int xCord, int yCord) {
		
		myGif.setCoords(xCord, yCord);
		
	}
	
	/**
	 * Move the gif by given x and y 
	 * @param xShift The shift along the x axis. Positive is to the right
	 * @param yShift The shift along the y axis. Positive is down
	 */
	public void translate(int xShift, int yShift) {
		
		myGif.translate(xShift, yShift);
		
	}
	
	/**
	 * 
	 * Gets the width of the gif
	 * 
	 * @return The width of the gif
	 */
	public int getWidth() {
		
		return myGif.getWidth();
		
	}
	
	/**
	 * 
	 * Gets the height of the gif
	 * 
	 * @return The height of the gif
	 */
	public int getHeight() {
		
		return myGif.getHeight();
		
	}
	
	/**
	 * 
	 * Determines whether or not the gif is done playing
	 * 
	 * @return Whether or not the gif is done playing
	 */
	public boolean gifIsFinished() {
		
		return  myGif.isFinished();
		
	}
	
	/**
	 * 
	 * Resizes the gif to the desired shape
	 * 
	 * @param w The desired width
	 * @param h The desired height
	 */
	public void resize(int w, int h) {
		
		myGif.resize(w, h);
		
	}
	
	/**
	 * 
	 * Scales the gif perfectly given a desired new width
	 * 
	 * @param width The desired new width
	 */
	public void scaleByWidth(int width) {
		
		myGif.scaleByWidth(width);
		
	}
	
	/**
	 * 
	 * Scales the gif by a given factor
	 * 
	 * @param s The factor the gif should be scaled by
	 */
	public void scale(float s) {
		
		myGif.scale(s);
		
	}
	
	/**
	 * 
	 * Stops looping the gif
	 * 
	 */
	public void stopLooping() {
		
		myGif.stopLooping();
		
	}
	/**
	 * 
	 * Starts looping the gif
	 * 
	 */
	public void startLooping() {
		
		myGif.startLooping();
		
	}
	
	/**
	 * 
	 * Sets the gif to only play once
	 * 
	 */
	public void playOnce() {
		
		myGif.playOnce();
		
	}
	
	/**
	 * 
	 * Restarts the gif and plays it again
	 * 
	 */
	public void restart() {
		
		myGif.restart();
		
	}
	
	/**
	 * 
	 * Overrides the default delay between the gif frames
	 * 
	 * @param delay The delay you want between gif frames
	 */
	
	public void overrideDelay(double delay) {
		
		myGif.overrideDelay(delay);
		
	}
	
	/**
	 * 
	 * Returns the gif delays back to default
	 * 
	 */
	public void defaultDelay() {
		
		myGif.defaultDelay();
		
	}
	
	/**
	 * 
	 * Makes and returns a copy of the PGif used
	 * 
	 * @return A copy of the gif used in the PGif
	 */
	public PGif copy() {
		
		return myGif.copy();
		
	}

}
