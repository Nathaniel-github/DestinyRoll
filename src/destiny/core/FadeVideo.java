package destiny.core;
import processing.core.PApplet;
import processing.video.Movie;

public class FadeVideo extends Fader {
	
	public Movie video;
	
	private int x, y, w, h;
	
	public FadeVideo(PApplet window, String pathname) {
		
		super(0, 255, 0.2f);
		video = new Movie(window, pathname);
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord, int width, int height) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		video = new Movie(window, pathname);
		w = width;
		h = height;
		
	}
	
	public FadeVideo(PApplet window, String pathname, float startTint, float targetTint, float fadeSpeed, int xCord, int yCord) {
		
		super(startTint, targetTint, fadeSpeed);
		x = xCord;
		y = yCord;
		video = new Movie(window, pathname);
		
	}
	
	@Override
	public void draw(PApplet window) {
		
		window.pushStyle();
		
		super.draw(window);
		
		window.image(video, x, y, w, h);
		
		window.popStyle();
		
	}
	
	public void setCoords(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void resize(int w, int h) {
		
		this.w = w;
		this.h = h;
		
	}
	
	public void loop() {
		
		video.loop();
		
	}
	
	public void play() {
		
		video.play();
		
	}
	
	public void pause() {
		
		video.pause();
		
	}
	
	public void resume() {
		
		video.play();
		
	}
	
	public void stop() {
		
		video.stop();
		
	}
	
	public void setVolume(float vol) {
		
		video.volume(vol);
		
	}
	
	public void scrollVolume(float scroll) {
		
		
		float vol = (float)video.playbin.getVolume() + scroll;
		video.volume(vol);
		
	}
	
	public void mute() {
		
		video.volume(0);
		
	}
	
	public boolean isPlaying() {
		
		return video.isPlaying();
		
	}
	
}
