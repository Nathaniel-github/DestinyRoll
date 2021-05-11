package destiny.core;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import destiny.gif.GifDecoder;
import destiny.gif.GifDecoder.GifImage;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class uses the OpenImaging GifDecoder to make a gif that is drawable to a PApplet
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class PGif {
	
	private ImageFrame[] frameData;
	private String pathName;
	private PImage[] imageFrames;
	private int frameCount;
	private long lastTimeStamp;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean firstDraw = true;
	private boolean looping = true;
	private boolean tempPlaying = false;
	private double delay;
	private boolean overrideDelay = false;
	private Runnable exec;
	private boolean listenerFired = false;
	
	/**
	 * 
	 * Creates a gif at the given from the given path
	 * 
	 * @param x The x coordinate of the top left of the gif
	 * @param y The y coordinate of the top left of the gif
	 * @param pathName The path to the gif
	 */
	public PGif(int x, int y, String pathName) {
		
		this.pathName = pathName;
		
		try {
			frameData = readGIF(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageFrames = new PImage[frameData.length];
		
		for (int i = 0; i < frameData.length; i ++) {
			
			imageFrames[i] = new PImage(frameData[i].getImage());
			
		}
		
		this.x = x;
		this.y = y;
		
		width = imageFrames[0].width;
		height = imageFrames[0].height;
		
		frameCount = 0;
		
	}
	
	private PGif(String pathName, int frameCount, long lastTimeStamp, int x, int y, int width, int height, boolean firstDraw, boolean looping, boolean tempPlaying, double delay, boolean overrideDelay) {
		
		try {
			frameData = readGIF(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageFrames = new PImage[frameData.length];
		
		for (int i = 0; i < frameData.length; i ++) {
			
			imageFrames[i] = new PImage(frameData[i].getImage());
			
		}
		
		this.resize(width, height);
		
		this.frameCount = frameCount;
		this.lastTimeStamp = lastTimeStamp;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.firstDraw = firstDraw;
		this.looping = looping;
		this.tempPlaying = tempPlaying;
		this.delay = delay;
		this.overrideDelay = overrideDelay;
		
	}
	
	/**
	 * 
	 * Draws the gif to the given window
	 * 
	 * @param window The PApplet that the gif should be drawn to
	 * @post The PApplet will have a gif drawn to it
	 */
	public void draw(PApplet window) {
		
		window.image(imageFrames[frameCount], (float)(x), (float)(y));
		
		advanceFrame();
		
	}
	
	private void advanceFrame() {
		
		if (isFinished()) {
			if (exec != null && !listenerFired) {
				listenerFired = true;
				exec.run();
			}
			return;
		} else if (listenerFired) {
			listenerFired = false;
		}
		
		if (!firstDraw) {
			
			int lastFrame = frameCount;
			frameCount += (((System.nanoTime() - lastTimeStamp) / 10000000) / (overrideDelay?delay:frameData[frameCount].getDelay()));
			if (lastFrame != frameCount)
				lastTimeStamp = System.nanoTime();
			
		} else {
			
			firstDraw = false;
			lastTimeStamp = System.nanoTime();
			
		}
		
		if (frameCount >= frameData.length || frameCount < 0) {
			
			frameCount = 0;
			tempPlaying = false;
			
		}
		
	}
	
	/**
	 * 
	 * Gives the current image frame the gif is on
	 * 
	 * @return A PImage of the current frame 
	 */
	public PImage getCurrentImage() {
		
		return imageFrames[frameCount];
		
	}
	
	/**
	 * 
	 * Sets the coordinates of the gif
	 * 
	 * @param xCord The x coordinate of the top left of the gif
	 * @param yCord The y coordinate of the top left of the gif
	 */
	public void setCoords(int xCord, int yCord) {
		
		x = xCord;
		y = yCord;
		
	}
	
	/**
	 * 
	 * Adds a listener for code to be run in the event that the gif stops playing
	 * 
	 * @param listener The code that should be run in the event the gif stops playing
	 */
	public void addListener(Runnable listener) {
		
		exec = listener;
		listenerFired = false;
		
	}
	
	/**
	 * 
	 * Removes the code that should be run in the even the gif stops playing
	 * 
	 */
	public void removeListener() {
		exec = null;
	}
	
	/**
	 * 
	 * Shifts the top left corner of the gif by the specified amount
	 * 
	 * @param xShift
	 * @param yShift
	 */
	public void translate(int xShift, int yShift) {
		
		x += xShift;
		y += yShift;
		
	}
	
	/**
	 * 
	 * Gets the width of the gif
	 * 
	 * @return The width of the gif
	 */
	public int getWidth() {
		
		return width;
		
	}
	
	/**
	 * 
	 * Gets the height of the gif 
	 * 
	 * @return The height of the gif
	 */
	public int getHeight() {
		
		return height;
		
	}
	
	/**
	 * 
	 * Calculates whether or not the gif is done playing
	 * 
	 * @return Whether or not the gif is done playing
	 */
	public boolean isFinished() {
		
		return  (!looping && frameCount == 0) && !tempPlaying;
		
	}
	
	/**
	 * 
	 * Resizes the gif with the given specifications
	 * 
	 * @param w The new width of the gif
	 * @param h The new height of the gif
	 */
	public void resize(int w, int h) {
		
		if (w == width && h == height)
			return;
		
		for (int i = 0;i < imageFrames.length; i ++) {
			
			imageFrames[i].resize(w, h);
			
		}
		
		width = imageFrames[0].width;
		height = imageFrames[0].height;
		
	}
	
	/**
	 * 
	 * Scales the gif by the given factor
	 * 
	 * @param s The factor to be scaled by
	 */
	public void scale(double s) {
		
		this.resize((int)(width * s), (int)(height * s));
		
	}
	
	/**
	 * 
	 * Stops looping the gif
	 * 
	 */
	public void stopLooping() {
		
		looping  = false;
		tempPlaying = true;
		
	}
	
	/**
	 * 
	 * Starts looping the gif again
	 * 
	 */
	public void startLooping() {
		
		looping = true;
		tempPlaying = false;
		lastTimeStamp = System.nanoTime();
		
	}
	
	/**
	 * 
	 * Plays the gif once
	 * 
	 */
	public void playOnce() {
		
		restart();
		
		tempPlaying = true;
		looping = false;
		
	}
	
	/**
	 * 
	 * Restarts the gif to frame 0
	 * 
	 */
	public void restart() {
		
		frameCount = 0;
		
	}
	
	/**
	 * 
	 * Overrides the default delay between gif frames
	 * 
	 * @param delay The new desired gif between frames
	 */
	public void overrideDelay(double delay) {
		
		overrideDelay = true;
		this.delay = delay;
		
	}
	
	/**
	 * 
	 * Scales the gif perfectly given a new desired width
	 * 
	 * @param width Desired new width
	 */
	public void scaleByWidth(int width) {
		
		double scale = (double)width/this.width;
		
		this.scale(scale);
		
	}
	
	/**
	 * 
	 * Returns the gif to playing with its default delays between frames
	 * 
	 */
	public void defaultDelay() {
		
		overrideDelay = false;
		
	}
	
	/**
	 * 
	 * Makes a deep copy of itself and returns it
	 * 
	 * @return A deep copy of this object
	 */
	public PGif copy() {
		
		return new PGif(pathName, frameCount, lastTimeStamp, x, y, width, height, firstDraw, looping, tempPlaying, delay, overrideDelay);
		
	}
	
	// This method was mainly just taken from an example piece of code in Stack Overflow
	private ImageFrame[] readGIF(String pathName) throws IOException {
	    ArrayList<ImageFrame> frames = new ArrayList<ImageFrame>();
	    
	    GifImage gif = GifDecoder.read(new FileInputStream(pathName));
	    
	    for (int i = 0; i < gif.getFrameCount(); i ++) {
	    	
	    	frames.add(new ImageFrame(gif.getFrame(i), gif.getDelay(i)));
	    	
	    }

	    return frames.toArray(new ImageFrame[frames.size()]);
	}

	private class ImageFrame {
	    private final int delay;
	    private final BufferedImage image;

	    public ImageFrame(BufferedImage image, int delay) {
	        this.image = image;
	        this.delay = delay;
	    }

	    public BufferedImage getImage() {
	        return image;
	    }

	    public int getDelay() {
	        return delay;
	    }
	    
	}
	
}
