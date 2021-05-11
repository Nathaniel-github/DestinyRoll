package destiny.core;

public class ScreenFader extends Fader {

	/**
	 * 
	 * Creates a screen fader that doesnt tint at all
	 * 
	 */
	public ScreenFader() {
		
		this(0, 0, 0.5f);
		
	}
	
	/**
	 * 
	 * Creates a Screen fader with the given specifications about tint
	 * 
	 * @param startTint The desired starting tint of the screen
	 * @param targetTint The tint that should be reached by this fader
	 * @param fadeSpeed The speed at which the screen fades at. This is the amount the tint is changed by each frame
	 */
	public ScreenFader(float startTint, float targetTint, float fadeSpeed) {
		
		super(startTint, targetTint, fadeSpeed);
		
		
	}
	
	/**
	 * 
	 * Sets the screen to fade to black and starts the fade
	 * 
	 */
	public void fadeToBlack() {
		
		this.setTargetTint(255);
		
	}
	
	/**
	 * 
	 * Sets the screen to fade to white and starts the fade
	 * 
	 */
	public void fadeToWhite() {
		
		this.fadeWhite(true);
		this.setTargetTint(255);
		
	}
	
	/**
	 * 
	 * Fades the screen into becoming clear
	 * 
	 */
	public void fadeIn() {
		
		this.setTargetTint(0);
		
	}

}
