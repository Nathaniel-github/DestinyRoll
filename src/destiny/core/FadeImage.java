package destiny.core;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

public class FadeImage extends Fader {
	
	public PImage myImage;
	private int x, y;
	
	public FadeImage(String pathname) {
		
		super(0, 255, 0.2f);
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

}
