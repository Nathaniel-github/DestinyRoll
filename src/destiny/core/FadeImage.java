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
	
	public FadeImage(String pathname) {
		
		super(0, 255, 0.9f);
		try {
			myImage = new PImage(ImageIO.read(new File(pathname)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
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
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		window.image(myImage, x, y);
		
		window.popStyle();
		
	}
	
	public void setCoords(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void resize(int width, int height) {
		myImage.resize(width, height);
	}

}
