package destiny.core;
import processing.core.PApplet;

/**
 * 
 * This is the abstract class all faders extend from, it gives the backbone functionality of tinting 
 * the window and ticking the fade along with other functionalities
 * 
 * @author Nathaniel
 * @version 12/5/2020
 *
 */
abstract class Fader {
	
	private float tint;
	private float targetTint;
	private float fadeSpeed;
	
	private boolean isFading = true;
	private boolean white = false;
	private Runnable listener = null;
	
	public Fader(float startTint, float targetTint, float fadeSpeed) {
		
		if (fadeSpeed < 0)
			throw new IllegalArgumentException("Fade speed must be greater than 0");
		
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		
	}
	
	public void draw(PApplet window) {
		
		if (!white)
			window.tint(tint);
		else
			window.tint(255, (int)tint);
		
		tick();
		
	}
	
	private void tick() {
		
		if (!isFading)
			return;
		
		if (tint == targetTint) {
			
			isFading = false;
			this.reachedTint();
			return;
			
		}
		
		if (tint < targetTint) {
			
			tint += fadeSpeed;
			
			if (tint > targetTint) {
				
				tint = targetTint;
				
			}
			
		} else {
			
			tint -= fadeSpeed;
			
			if (tint < targetTint) {
				
				tint = targetTint;
				
			}
			
		}
		
	}
	
	public void stopFade() {
		
		isFading = false;
		
	}
	
	public void setTint(float tint) {
		
		this.tint = tint;
		setTargetTint(tint);
		
	}
	
	public void setTargetTint(float target) {
		
		targetTint = target;
		isFading = true;
		
	}
	
	public boolean isFinished() {
		
		return targetTint == tint;
		
	}
	
	public int ticksTillTarget() {
		
		return (int) (Math.abs(targetTint - tint)/fadeSpeed);
		
	}
	
	public void setFadeSpeed(float speed) {
		
		fadeSpeed = speed;
		
	}
	
	public float getTint() {
		
		return tint;
		
	}
	
	public void addListener(Runnable listener) {
		
		this.listener = listener;
		
	}
	
	public void removeListener() {
		
		this.listener = null;
		
	}
	
	private void reachedTint() {
		
		if (listener != null)
			listener.run();
		
	}
	
	public void fadeWhite(boolean fade) {
		
		white = fade;
		
	}
	
}
