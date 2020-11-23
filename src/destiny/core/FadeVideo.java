package destiny.core;
import processing.core.PApplet;
import processing.video.Movie;

public class FadeVideo extends Fader {
	
	public FadeVideo(PApplet window, String pathname) {
		
		super(0, 255, 0.2f, 0, 0, 0 ,0, true);
		myImage = new Movie(window, pathname);
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, width, height, false);
		myImage = new Movie(window, pathname);
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed, xCord, yCord, 0 ,0, true);
		myImage = new Movie(window, pathname);
		
	}
	
	public void loop() {
		
		((Movie) myImage).loop();
		
	}
	
	public void play() {
		
		((Movie) myImage).play();
		
	}
	
	public void pause() {
		
		((Movie) myImage).pause();
		
	}
	
	public void resume() {
		
		((Movie) myImage).play();
		
	}
	
	public void stop() {
		
		((Movie) myImage).stop();
		
	}
	
	public void setVolume(float vol) {
		
		((Movie) myImage).volume(vol);
		
	}
	
	public void scrollVolume(float scroll) {
		
		
		float vol = (float) ((Movie) myImage).playbin.getVolume() + scroll;
		((Movie) myImage).volume(vol);
		
	}
	
	public void mute() {
		
		((Movie) myImage).volume(0);
		
	}
	
	public boolean isPlaying() {
		
		return ((Movie)myImage).isPlaying();
		
	}
	
}
