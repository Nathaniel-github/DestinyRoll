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
	
	public void draw(PApplet window) {
		
		window.image(imageFrames[frameCount], (float)(x), (float)(y));
		
		advanceFrame();
		
	}
	
	private void advanceFrame() {
		
		if (isFinished())
			return;
		
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
	
	public PImage getCurrentImage() {
		
		return imageFrames[frameCount];
		
	}
	
	public void setCoords(int xCord, int yCord) {
		
		x = xCord;
		y = yCord;
		
	}
	
	public void translate(int xShift, int yShift) {
		
		x += xShift;
		y += yShift;
		
	}
	
	public int getWidth() {
		
		return width;
		
	}
	
	public int getHeight() {
		
		return height;
		
	}
	
	public boolean isFinished() {
		
		return  (!looping && frameCount == 0) && !tempPlaying;
		
	}
	
	public void resize(int w, int h) {
		
		if (w == width && h == height)
			return;
		
		for (int i = 0;i < imageFrames.length; i ++) {
			
			imageFrames[i].resize(w, h);
			
		}
		
		width = imageFrames[0].width;
		height = imageFrames[0].height;
		
	}
	
	public void scale(double s) {
		
		this.resize((int)(width * s), (int)(height * s));
		
	}
	
	public void stopLooping() {
		
		looping  = false;
		tempPlaying = true;
		
	}
	
	public void startLooping() {
		
		looping = true;
		tempPlaying = false;
		lastTimeStamp = System.nanoTime();
		
	}
	
	public void playOnce() {
		
		restart();
		
		tempPlaying = true;
		looping = false;
		
	}
	
	public void restart() {
		
		frameCount = 0;
		
	}
	
	public void overrideDelay(double delay) {
		
		overrideDelay = true;
		this.delay = delay;
		
	}
	
	public void scaleByWidth(int width) {
		
		double scale = (double)width/this.width;
		
		this.scale(scale);
		
	}
	
	public void defaultDelay() {
		
		overrideDelay = false;
		
	}
	
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
