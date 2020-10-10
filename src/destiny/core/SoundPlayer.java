package destiny.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.Timer;

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
	
	public SoundPlayer(String FileName) {	
		fileName = FileName;
		
		try {
			myobj = AudioSystem.getAudioInputStream(new File(fileName));
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
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void changeVolume(float f) {
		volume.setValue(f);
	}
	
	public void setVolume(float f) {
		volume.setValue(f + muteVolume);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void close() {
		clip.close();
	}
	
	public void setInitialDelay(int x) {
		initDelay = x;
	}
	
	public void restart() {
		
		restartSound.start();
		
	}
	
	public void forceRestart() {
		
		clip.setMicrosecondPosition(0);
		
	}
	
}