package destiny.core;
import processing.core.PApplet;

/**
 * 
 * This is the class all faders extend from, it gives the backbone functionality of tinting 
 * the window and ticking the fade along with other functionalities
 * 
 * @author Nathaniel
 * @version 12/5/2020
 *
 */
class Fader {
	
	private float tint;
	private float targetTint;
	private float fadeSpeed;
	
	private boolean isFading = true;
	private boolean white = false;
	private Runnable listener = null;
	
	/**
	 * 
	 * Creates a Fader with the specified values for tint and speed of the fade
	 * 
	 * @param startTint The tint that the fade will start from
	 * @param targetTint The tint that the Fader should attempt to reach with its fade
	 * @param fadeSpeed The speed that the Fader should fade at
	 */
	protected Fader(float startTint, float targetTint, float fadeSpeed) {
		
		if (fadeSpeed < 0)
			throw new IllegalArgumentException("Fade speed must be greater than 0");
		
		tint = startTint;
		this.targetTint = targetTint;
		this.fadeSpeed = fadeSpeed;
		
	}
	
	/**
	 * 
	 * Applies the tint to the window
	 * 
	 * @param window The PApplet that the tint will be applied to
	 * @post The given PApplet will have a tint applied to it
	 */
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
	
	/**
	 * 
	 * Stops the fader from changing the tint applied
	 * 
	 */
	public void stopFade() {
		
		isFading = false;
		
	}
	
	/**
	 * 
	 * Sets the tint of the window
	 * 
	 * @param tint The tint that should be applied to the window
	 */
	public void setTint(float tint) {
		
		this.tint = tint;
		setTargetTint(tint);
		
	}
	
	/**
	 * 
	 * Sets a target tint for the Fader to reach. The Fader will automatically start to tint towards this value
	 * 
	 * @param target The target tint that you wish to reach
	 */
	public void setTargetTint(float target) {
		
		targetTint = target;
		isFading = true;
		
	}
	
	/**
	 * 
	 * Determines if the fader has reached its target tint
	 * 
	 * @return Whether or not the target tint is equal to the current tint of the Fader
	 */
	public boolean isFinished() {
		
		return targetTint == tint;
		
	}
	
	/**
	 * 
	 * Calculates and returns the number of ticks till the Fader reaches its target tint
	 * 
	 * @return The number of ticks till the target tint is reached
	 */
	public int ticksTillTarget() {
		
		return (int) (Math.abs(targetTint - tint)/fadeSpeed);
		
	}
	
	/**
	 * 
	 * Sets the speed that the fade occurs at
	 * 
	 * @param speed The speed that you want the Fader to fade at
	 */
	public void setFadeSpeed(float speed) {
		
		fadeSpeed = speed;
		
	}
	
	/**
	 * 
	 * Determines the current tint of the Fader
	 * 
	 * @return The current tint of the Fader
	 */
	public float getTint() {
		
		return tint;
		
	}
	
	/**
	 * 
	 * Adds a runnable block of code that will run on the event that the target tint has been reached
	 * 
	 * @param listener The code that should be run in the event the target tint is reached
	 */
	public void addListener(Runnable listener) {
		
		this.listener = listener;
		
	}
	
	/**
	 * 
	 * Removes the code that should be run on reaching the target tint and removes the trigger on the event the target fade is reacehd
	 * 
	 */
	public void removeListener() {
		
		this.listener = null;
		
	}
	
	private void reachedTint() {
		
		if (listener != null)
			listener.run();
		
	}
	
	/**
	 * 
	 * Sets whether or not the window should fade to white
	 * 
	 * @param fade Whether or not the window should fade to white. True means white, false means black
	 */
	public void fadeWhite(boolean fade) {
		
		white = fade;
		
	}
	
}
