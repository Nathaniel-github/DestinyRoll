package destiny.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.Timer;

/**
 * 
 * A class that plays wav files because that is all java supports
 * 
 * @author Nathaniel
 * @version 5/23/21
 *
 */
public class SoundPlayer {
	
	private AudioInputStream myobj;
	private Clip clip;
	private String fileName;
	private FloatControl volume;
	private final float muteVolume;
	private int initDelay = 0;
	private Timer restartSound = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (!clip.isRunning()) {
				
				clip.setMicrosecondPosition(0);
				
			}
			
		}
		
	});
	
	/**
	 * 
	 * Creates a SoundPlayer from the given file
	 * 
	 * @param FileName The filename
	 */
	public SoundPlayer(String FileName) {	
		fileName = FileName;
		
		try {
			myobj = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clip.open(myobj);
			volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		muteVolume = volume.getMinimum();
		
	}
	
	/**
	 * 
	 * Plays the sound
	 * 
	 */
	public void play() {
		if (!clip.isRunning()) {
			new Thread(new Runnable() {
	
				@Override
				public void run() {
	
					try {
						Thread.sleep(initDelay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					clip.start();
					
				}
				
			}).start();;
		}
	}
	
	/**
	 * 
	 * Loops the sound
	 * 
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * 
	 * Changes the volume by the given amount
	 * 
	 * @param f The amount of volume you want to change by
	 */
	public void changeVolume(float f) {
		volume.setValue(f);
	}
	
	/**
	 * 
	 * Sets the volume to the given number
	 * 
	 * @param f The volume that the sound should be played at
	 */
	public void setVolume(float f) {
		volume.setValue(f + muteVolume);
	}
	
	/**
	 * 
	 * Stops playing the sound
	 * 
	 */
	public void stop() {
		clip.stop();
	}
	
	/**
	 * 
	 * Closes the clip
	 * 
	 */
	public void close() {
		clip.close();
	}
	
	/**
	 * 
	 * Sets an initial delay for the sound to start playing
	 * 
	 * @param x The amount of initial delay that should be set for the sound
	 */
	public void setInitialDelay(int x) {
		initDelay = x;
	}
	
	/**
	 * 
	 * Restarts the sound playing
	 * 
	 */
	public void restart() {
		
		restartSound.start();
		
	}
	
	/**
	 * 
	 * Determines whether or not the clip is playing
	 * 
	 * @return Whether or not the sound is playing
	 */
	public boolean isPlaying() {
		
		return clip.isActive();
		
	}
	
	/**
	 * 
	 * Force restarts the sound
	 * 
	 */
	public void forceRestart() {
		
		clip.setMicrosecondPosition(0);
		
	}
	
}