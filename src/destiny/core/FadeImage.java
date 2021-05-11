package destiny.core;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class uses the PImage class from processing and adds fading capability to it
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class FadeImage extends Fader {
	
	private PImage myImage;
	private int x, y;
	
	/**
	 * 
	 * Creates a fade image with the given image
	 * 
	 * @param pathname The image that should be used
	 */
	public FadeImage(String pathname) {
		
		super(0, 255, 0.9f);
		try {
			myImage = new PImage(ImageIO.read(new File(pathname)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Creates a fading image with the specified parameters
	 * 
	 * @param pathname The image path
	 * @param startTint The starting tint of the fade
	 * @param targetTint The target tint of the fade
	 * @param fadeSpeed The speed that the image should fade at
	 * @param xCord The x coordinate of the top left of the image
	 * @param yCord The y coordinate of the top left of the image
	 * @param width The width that the image should be drawn with
	 * @param height The height that the image should be drawn with
	 */
	public FadeImage(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		try {
			myImage = new PImage(ImageIO.read(new File(pathname)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = xCord;
		y = yCord;
		myImage.resize(width, height);
		
	}
	
	/**
	 * 
	 * Creates a fading image with the specified parameters
	 * 
	 * @param pathname The image path
	 * @param startTint The starting tint of the fade
	 * @param targetTint The target tint of the fade
	 * @param fadeSpeed The speed that the image should fade at
	 * @param xCord The x coordinate of the top left of the image
	 * @param yCord The y coordinate of the top left of the image
	 */
	public FadeImage(String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		try {
			myImage = new PImage(ImageIO.read(new File(pathname)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Draws the fading image to the given PApplet
	 * 
	 * @post The PApplet will have a fading image drawn to it
	 * 
	 */
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		window.image(myImage, x, y);
		
		window.popStyle();
		
	}
	
	/**
	 * 
	 * Sets the coordinates of the fading image
	 * 
	 * @param x The x coordinate of the top left of the image
	 * @param y The y coordinate of the top left of the image
	 */
	public void setCoords(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * 
	 * Resizes the fading image
	 * 
	 * @param width The width the image should be drawn with
	 * @param height The height the image should be drawn with
	 */
	public void resize(int width, int height) {
		myImage.resize(width, height);
	}

}
