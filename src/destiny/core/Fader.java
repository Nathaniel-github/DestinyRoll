package destiny.core;
import processing.core.PApplet;

abstract class Fader {
	
	private float tint;
	private float targetTint;
	private float fadeSpeed;
	
	private boolean isFading = true;
	
	public Fader(float startTint, float targetTint, float fadeSpeed) {
		
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		
	}
	
	public void draw(PApplet window) {
		
		window.tint(tint);
		
		tick();
		
	}
	
	private void tick() {
		
		if (!isFading)
			return;
		
		if (tint == targetTint) {
			
			isFading = false;
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
	
	public void setFadeSpeed(float speed) {
		
		fadeSpeed = speed;
		
	}
	
	public float getTint() {
		
		return tint;
		
	}
	
}
