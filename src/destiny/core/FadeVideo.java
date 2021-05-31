package destiny.core;
import processing.core.PApplet;
import processing.video.Movie;

/**
 * 
 * This class uses the Movie class from processing and adds fading capability to it
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class FadeVideo extends Fader {
	
	private Movie video;
	
	private int x, y, w = -1, h = -1;
	private float targetVol, volChange;
	private boolean isChanging;
	
	/**
	 * 
	 * Creates a fading video given a path to a video file
	 * 
	 * @param window The window that the movie should be drawn to
	 * @param pathname The path to the video file
	 */
	public FadeVideo(PApplet window, String pathname) {
		
		super(0, 255, 0.5f);
		video = new Movie(window, pathname);
		
	}
	
	/**
	 * 
	 * Creates a fading video with the given parameters
	 * 
	 * @param window The window to be drawn to
	 * @param pathname The path to the video
	 * @param startTint The starting tint of the video
	 * @param targetTint The tint that the video should fade to
	 * @param fadeSpeed The amount the tint should change by each frame
	 * @param xCord The x coordinate of the top left
	 * @param yCord The y coordinate of the top left
	 * @param width The width the video should be
	 * @param height The height the video should be
	 */
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		video = new Movie(window, pathname);
		w = width;
		h = height;
		
	}
	
	/**
	 * 
	 * Creates a fading video with the given parameters
	 * 
	 * @param window The window to be drawn to
	 * @param pathname The path to the video
	 * @param startTint The starting tint of the video
	 * @param targetTint The tint that the video should fade to
	 * @param fadeSpeed The amount the tint should change by each frame
	 * @param xCord The x coordinate of the top left
	 * @param yCord The y coordinate of the top left
	 */
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		video = new Movie(window, pathname);
		
	}
	
	@Override
	/**
	 * 
	 * Draws the fading video to the screen
	 * 
	 * @param window The PApplet the video should be drawn to
	 * @post The given PApplet will have the video drawn to it and will be tinted
	 * 
	 */
	public void draw(PApplet window) {
		
		super.draw(window);
		
		if (w < 0)
			window.image(video, x, y);
		else
			window.image(video, x, y, w, h);
		
		if (isChanging) {
			
			this.scrollVolume(volChange);
			
			if (this.isFinished()) {
				
				isChanging = false;
				this.video.volume(targetVol);
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * Sets the coordinates of this video
	 * 
	 * @param x The x coordinate of the top left of the video
	 * @param y The y coordinate of the top left of the video
	 */
	public void setCoords(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * 
	 * Resizes this video to the given dimensions
	 * 
	 * @param w The width of the video
	 * @param h The height of the video
	 */
	public void resize(int w, int h) {
		
		this.w = w;
		this.h = h;
		
	}
	
	/**
	 * 
	 * Changes the volume by the specified amount
	 * 
	 * @param scroll The amount of volume you want to change by
	 */
	public void scrollVolume(float scroll) {
		
		
		float vol = (float)video.playbin.getVolume() + scroll;
		video.volume(vol);
		
	}
	
	/**
	 * 
	 * Plays the video once
	 * 
	 */
	public void play() {
		
		video.play();
		
	}
	
	/**
	 * 
	 * The volume you want to set the video to
	 * 
	 * @param vol The volume that should be set for the video
	 */
	public void setVolume(float vol) {
		
		video.volume(vol);
		
	}
	
	/**
	 * 
	 * Scales video given the width
	 * 
	 * @param width The width that you want to scale to
	 */
	public void scaleByWidth(int width) {
		
		double scale = (double)video.width/width;
		this.scale(scale);
		
	}
	
	/**
	 * 
	 * Scales the video based on the given factor
	 * 
	 * @param scale The factor you want to scale by
	 */
	public void scale(double scale) {
		
		w  = (int)(w  *scale);
		h = (int)(h * scale);
		
	}
	
	/**
	 * 
	 * Fades the volume of the video to the given volume
	 * 
	 * @param vol The volume that should be faded towards
	 */
	public void fadeVolumeTo(float vol) {
		
		isChanging = true;
		volChange = (float)(vol-video.playbin.getVolume())/this.ticksTillTarget();
		targetVol = vol;
		
	}
	
	/**
	 * 
	 * The second mark the video is on
	 * 
	 * @return The second mark of the video
	 */
	public float getSecondMark() {
		
		return video.time();
		
	}
	
	/**
	 * 
	 * Determines whether or not the video is currently playing
	 * 
	 * @return Whether or not the video is currently playing
	 */
	public boolean isPlaying() {
		
		return video.isPlaying();
		
	}
	
	/**
	 * 
	 * Loops the video
	 * 
	 */
	public void loop() {
		
		video.loop();
		
	}
	
	/**
	 * 
	 * Mutes the video
	 * 
	 */
	public void mute() {
		
		video.volume(0);
		
	}
	
	/**
	 * 
	 * Stops the video
	 * 
	 */
	public void stop() {
		
		video.stop();
		
	}
	
}
