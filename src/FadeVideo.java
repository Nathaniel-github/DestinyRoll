import processing.core.PApplet;
import processing.video.Movie;

public class FadeVideo extends Fader {
	
	public FadeVideo(PApplet window, String pathname) {
		
		myImage = new Movie(window, pathname);
		tint = 0;
		this.targetTint = 255;
		this.fadeSpeed = 0.2f;
		x = 0;
		y = 0;
		fullScreen = true;
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		myImage = new Movie(window, pathname);
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		x = xCord;
		y = yCord;
		this.width = width;
		this.height = height;
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		myImage = new Movie(window, pathname);
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		x = xCord;
		y = yCord;
		fullScreen = true;
		
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
	
}
