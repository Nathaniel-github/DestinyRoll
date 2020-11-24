package destiny.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import processing.core.PApplet;
import processing.core.PImage;

public class PGif {
	
	private ImageFrame[] frameData;
	private PImage[] imageFrames;
	private int frameCount;
	private long lastTimeStamp;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean firstDraw = true;
	private float scale;
	private boolean looping = true;
	private boolean tempPlaying = false;
	private double delay;
	private boolean overrideDelay = false;
	
	public PGif(int x, int y, String pathName) {
		
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
		scale = 1;
		
	}
	
	public void draw(PApplet window) {
		
		advanceFrame();
		
		window.pushMatrix();

		double midOfImageX = (x + imageFrames[frameCount].width/2.0);
		double midOfImageY = (y + imageFrames[frameCount].width/2.0);
		window.translate((float)(midOfImageX), (float)(midOfImageY));
		
		this.resize((int)(width * scale), (int)(height * scale));
		window.image(imageFrames[frameCount], (float)(x - midOfImageX), (float)(y - midOfImageY));
		
		window.popMatrix();
		
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
	
	public void setCoordinates(int xCord, int yCord) {
		
		x = xCord;
		y = yCord;
		
	}
	
	public void translate(int xShift, int yShift) {
		
		x += xShift;
		y += yShift;
		
	}
	
	public int getWidth() {
		
		return imageFrames[frameCount].width;
		
	}
	
	public int getHeight() {
		
		return imageFrames[frameCount].height;
		
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
		
	}
	
	public void setScale(float s) {
		
		scale = s;
		
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
	
	public void defaultDelay() {
		
		overrideDelay = false;
		
	}
	
	// This method was mainly just taken from an example piece of code in Stack Overflow
	private ImageFrame[] readGIF(String pathName) throws IOException {
	    ArrayList<ImageFrame> frames = new ArrayList<ImageFrame>();
	    
	    ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
		
		try {
			reader.setInput(ImageIO.createImageInputStream(new File(pathName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    int width = -1;
	    int height = -1;

	    IIOMetadata metadata = reader.getStreamMetadata();
	    if (metadata != null) {
	        IIOMetadataNode globalRoot = (IIOMetadataNode) metadata.getAsTree(metadata.getNativeMetadataFormatName());

	        NodeList globalScreenDescriptor = globalRoot.getElementsByTagName("LogicalScreenDescriptor");

	        if (globalScreenDescriptor != null && globalScreenDescriptor.getLength() > 0) {
	            IIOMetadataNode screenDescriptor = (IIOMetadataNode) globalScreenDescriptor.item(0);

	            if (screenDescriptor != null) {
	                width = Integer.parseInt(screenDescriptor.getAttribute("logicalScreenWidth"));
	                height = Integer.parseInt(screenDescriptor.getAttribute("logicalScreenHeight"));
	            }
	        }
	    }

	    BufferedImage master = null;
	    Graphics2D masterGraphics = null;

	    for (int frameIndex = 0;; frameIndex++) {
	        BufferedImage image;
	        try {
	            image = reader.read(frameIndex);
	        } catch (IndexOutOfBoundsException io) {
	            break;
	        }

	        if (width == -1 || height == -1) {
	            width = image.getWidth();
	            height = image.getHeight();
	        }

	        IIOMetadataNode root = (IIOMetadataNode) reader.getImageMetadata(frameIndex).getAsTree("javax_imageio_gif_image_1.0");
	        IIOMetadataNode gce = (IIOMetadataNode) root.getElementsByTagName("GraphicControlExtension").item(0);
	        int delay = Integer.valueOf(gce.getAttribute("delayTime"));
	        String disposal = gce.getAttribute("disposalMethod");

	        int x = 0;
	        int y = 0;

	        if (master == null) {
	            master = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	            masterGraphics = master.createGraphics();
	            masterGraphics.setBackground(new Color(0, 0, 0, 0));
	        } else {
	            NodeList children = root.getChildNodes();
	            for (int nodeIndex = 0; nodeIndex < children.getLength(); nodeIndex++) {
	                Node nodeItem = children.item(nodeIndex);
	                if (nodeItem.getNodeName().equals("ImageDescriptor")) {
	                    NamedNodeMap map = nodeItem.getAttributes();
	                    x = Integer.valueOf(map.getNamedItem("imageLeftPosition").getNodeValue());
	                    y = Integer.valueOf(map.getNamedItem("imageTopPosition").getNodeValue());
	                }
	            }
	        }
	        masterGraphics.drawImage(image, x, y, null);

	        BufferedImage copy = new BufferedImage(master.getColorModel(), master.copyData(null), master.isAlphaPremultiplied(), null);
	        frames.add(new ImageFrame(copy, delay, disposal));

	        if (disposal.equals("restoreToPrevious")) {
	            BufferedImage from = null;
	            for (int i = frameIndex - 1; i >= 0; i--) {
	                if (!frames.get(i).getDisposal().equals("restoreToPrevious") || frameIndex == 0) {
	                    from = frames.get(i).getImage();
	                    break;
	                }
	            }

	            master = new BufferedImage(from.getColorModel(), from.copyData(null), from.isAlphaPremultiplied(), null);
	            masterGraphics = master.createGraphics();
	            masterGraphics.setBackground(new Color(0, 0, 0, 0));
	        } else if (disposal.equals("restoreToBackgroundColor")) {
	            masterGraphics.clearRect(x, y, image.getWidth(), image.getHeight());
	        }
	    }
	    reader.dispose();

	    return frames.toArray(new ImageFrame[frames.size()]);
	}

	private class ImageFrame {
	    private final int delay;
	    private final BufferedImage image;
	    private final String disposal;

	    public ImageFrame(BufferedImage image, int delay, String disposal) {
	        this.image = image;
	        this.delay = delay;
	        this.disposal = disposal;
	    }

	    public BufferedImage getImage() {
	        return image;
	    }

	    public int getDelay() {
	        return delay;
	    }

	    public String getDisposal() {
	        return disposal;
	    }
	}
	
}
