package destiny.assets;

import java.util.Arrays;

import org.bson.Document;

import destiny.core.PGif;
import destiny.net.MongoHandler;
import processing.core.PApplet;

/**
 * 
 * A class that represents a revolutionary. It holds their stats, image, and other info
 * 
 * @author Nathaniel
 * @version 5/13/2021
 *
 */
public class Character {
	
    private int health, attack, defense, mp, gauge;
    private int totalHealth, totalMp;
    protected PGif sprite, spriteStatic, spriteAttack, spriteFlinch, spriteMove;
    protected PGif[] sprites;
    private Runnable trigger;
    private boolean isBlocking, isHighlighted;  
    private int[] stats = {health, attack, defense, mp, gauge, totalHealth, totalMp};
    private int[] baseStats;
    /**
     * 
     * Creates the revolutionary given their id number
     * 
     * @param id The id number of the revolutionary
     */
    public Character(int id) {
        
        Document statDoc = MongoHandler.getStatDoc(id);
        
        health = statDoc.getInteger("health");
        attack = statDoc.getInteger("attack");
        defense = statDoc.getInteger("defense");
        mp = statDoc.getInteger("mp");
        totalMp = mp;
        totalHealth = health;
        gauge = statDoc.getInteger("gauge");
        
        baseStats = Arrays.copyOf(stats, stats.length);
        
        isBlocking = false;
        spriteStatic = new PGif(0, 0, Constants.getCharacterPath(id, "static"));
        spriteAttack = new PGif(0, 0, Constants.getCharacterPath(id, "attack"));
        spriteFlinch = new PGif(0, 0, Constants.getCharacterPath(id, "flinch"));
        spriteMove = new PGif(0, 0, Constants.getCharacterPath(id, "move"));
        sprite = spriteStatic;
        sprites = new PGif[]{spriteAttack, spriteFlinch, spriteMove, spriteStatic};
        
    }
	
    /**
     * 
     * Draws the revolutionary to the screen
     * 
     * @param window PApplet will have the revolutionary drawn to it
     * @post Stat box will be drawn to the window
     */
	public void draw(PApplet window) {
		sprite.draw(window);
		window.pushStyle();
		if (isHighlighted) {
			window.noFill();
			window.stroke(255,0,0);
			window.strokeWeight(5f);
			window.rect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		}
		window.stroke(0);
		window.fill(255, 0, 0);
		window.rect(spriteStatic.getX(), spriteStatic.getY()+spriteStatic.getHeight(), spriteStatic.getWidth(), 25);
		window.fill(0, 255, 0);
		window.rect(spriteStatic.getX(), spriteStatic.getY()+spriteStatic.getHeight(),  (int)((double)health*spriteStatic.getWidth()/totalHealth), 25);
		window.fill(0, 0, 0);
		window.rect(spriteStatic.getX(), spriteStatic.getY()+spriteStatic.getHeight()+25, spriteStatic.getWidth(), 25);
		window.fill(0, 0, 255);
		window.rect(spriteStatic.getX(), spriteStatic.getY()+spriteStatic.getHeight()+25,  (int)((double)mp*spriteStatic.getWidth()/totalMp), 25);
		
		
		for(int i = 0; i < gauge; i++) {
			window.pushStyle();
			window.fill(255,165,0);
			window.ellipse(spriteStatic.getX()+Constants.scaleIntToWidth(i*spriteStatic.getWidth()/5), spriteStatic.getY()-spriteStatic.getWidth()/5, spriteStatic.getWidth()/5, spriteStatic.getWidth()/5);
			window.popStyle();
		}
		window.popStyle();
	}
	
	/**
	 * 
	 * Moves the revolutionary to the given coords
	 * 
	 * @param x The x coordinate of the top left
	 * @param y The y coordinate of the top left
	 */
	public void setCoords(int x, int y) {
		
		for (int i = 0; i < sprites.length; i ++) {
			
			sprites[i].setCoords(x, y);
			
		}
		
	}
	 
	/**
	 * 
	 * Changes the revolutionary's gif top a dif action
	 * 
	 * @param action The action that the gif should change to
	 */
	public void changeAction(String action) {
		
		sprite = sprites[Constants.getIntForAction(action)];
		
	}
	
	/**
	 * 
	 * Takes damage based on enemy attack and multiplier
	 * 
	 * @param attack The attack of the enemy
	 * @param multi The multiplier of the attack that the enemy is using
	 */
	public void takeDamage(int attack, double multi) {
		if(attack > 0) {
			if(isBlocking) {
				health -= (int)(multi*attack*(140.0/(100+defense*4)));
			}
			else{
				health -= (int)(multi*attack*(140.0/(100+defense*2)));
			}
			
			final PGif temp = sprite;
			
			sprite = spriteFlinch;
			sprite.playOnce();
			sprite.addListener(new Runnable() {
	
				@Override
				public void run() {
					
					sprite.startLooping();
					sprite.restart();
					sprite.removeListener();
					sprite = temp;
					
				}
				
			});
		}
		else {
			health += attack;
		}
		isBlocking = false;
	}
	
	/**
	 * 
	 * Plays the action gif once and then returns to the previous gif
	 * 
	 * @param action The action gif you want to have play once
	 */
	public void playActionOnce(String action) {
		
		final PGif temp = sprite;
		
		sprite = sprites[Constants.getIntForAction(action)];
		sprite.playOnce();
		
		sprite.addListener(new Runnable() {

			@Override
			public void run() {
				
				sprite.startLooping();
				sprite.restart();
				sprite.removeListener();
				sprite = temp;
				
				if (trigger != null) {
					trigger.run();
					trigger = null;
				}
				
			}
			
		});
		
	}
	
	/**
	 * 
	 * Plays the action gif once and then returns to the previous gif after running the listener
	 * 
	 * @param action The action gif you want to have play once 
	 * @param code The code that you want to have run after the gif is done playing once
	 */
	public void playActionOnce(String action, Runnable code) {
		
		final PGif temp = sprite;
		
		sprite = sprites[Constants.getIntForAction(action)];
		sprite.playOnce();
		
		sprite.addListener(new Runnable() {

			@Override
			public void run() {
				
				sprite.startLooping();
				sprite.restart();
				sprite.removeListener();
				sprite = temp;
				code.run();
				
				if (trigger != null) {
					trigger.run();
					trigger = null;
				}
				
			}
			
		});
		
	}
	
	/**
	 * 
	 * Adds a trigger that will run after a gif is played once
	 * 
	 * @param code The code that should run after the play once
	 */
	public void addTrigger(Runnable code) {
		
		trigger = code;
		
	}
	
	/**
	 * 
	 * Removes the trigger that would run after a playOnce
	 * 
	 */
	public void removeTrigger() {
		
		trigger = null;
		
	}
	
	/**
	 * 
	 * Buffs all the stats of this character by a multiplier
	 * 
	 * @param mult The multiplier to buff by
	 */
	public void buff(double mult) {
		
		for (int i = 0; i < stats.length; i ++) {
			
			stats[i] = (int)(stats[i]*mult);
			
		}
		
	}
	
	/**
	 * 
	 * Sets whether or not the character should be outlined
	 * 
	 * @param light Whether or not the character should be highlighted
	 */
	public void setHighlight(boolean light) {
		
		isHighlighted = light;
		
	}
	
	/**
	 * 
	 * Adds an amount to the ultimate gauge. This can not go over 5, if it does it will be capped at 5
	 * 
	 * @param amt The amount of gauge that should be added to the character
	 */
	public void addGauge(int amt) {
		
		gauge += amt;
		if (gauge > 5)
			gauge = 5;
		
	}
	
	/**
	 * 
	 * Uses the ultimate if the character has enough gauge. Otherwise it will return false and not use any gauge
	 * 
	 * @return Whether or not the ultimate was used
	 */
	public boolean useUltimate() {
		
		if (gauge < 5)
			return false;
		
		gauge -= 5;
		
		return true;
		
	}
	
	/**
	 * 
	 * Uses the given mana amount if possible, otherwise it will not use any and return false
	 * 
	 * @param amt The amount of mana that should be used
	 * @return Whether or not the use of mana was successful
	 */
	public boolean useMana(int amt) {
		
		if (mp < amt)
			return false;
		
		mp -= amt;
		
		return true;
		
	}
	
	/**
	 * 
	 * Scales the character based on the given width
	 * 
	 * @param width The width that should be scaled to meet
	 */
	public void scaleByWidth(int width) {
		
		for (PGif i : sprites)
			i.scaleByWidth(width);
		
	}
	
	/**
	 * 
	 * Scales the character based on the given height
	 * 
	 * @param height The height that should be scaled to meet
	 */
	public void scaleByHeight(int height) {
		
		for (PGif i : sprites)
			i.scaleByHeight(height);
		
	}
	
	/**
	 * 
	 * Removes all buffs from this character
	 * 
	 */
	public void removeBuffs() {
		
		stats = Arrays.copyOf(baseStats, baseStats.length);
		
	}

	/**
	 * 
	 * Gets the health of this character
	 * 
	 * @return The health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * 
	 * Sets the health of this character
	 * 
	 * @param health The health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * 
	 * Gets the attack of this character
	 * 
	 * @return The attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * 
	 * Sets the attack of this character
	 * 
	 * @param attack The attack to set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * 
	 * Gets the defense of this character
	 * 
	 * @return The defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * 
	 * Sets the defense of this character
	 * 
	 * @param defense The defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * 
	 * Gets the mp of this character
	 * 
	 * @return The mp
	 */
	public int getMp() {
		return mp;
	}

	/**
	 * 
	 * Sets the mp of this character
	 * 
	 * @param mp The mp to set
	 */
	public void setMp(int mp) {
		this.mp = mp;
	}

	/**
	 * 
	 * Gets the current gauge of this character
	 * 
	 * @return The gauge
	 */
	public int getGauge() {
		return gauge;
	}

	/**
	 * 
	 * Sets the current gauge of this character
	 * 
	 * @param gauge The gauge to set
	 */
	public void setGauge(int gauge) {
		this.gauge = gauge;
	}
	
	/**
	 * 
	 * Determines whether or not this character is dead
	 * 
	 * @return true if the hp is less than 0 otherwise false
	 */
	public boolean isDead() {
		return health <= 0;
	}
	
	/**
	 * 
	 * Sets whether or not this character is blocking
	 * 
	 * @param isBlock Whether or not the character is blocking
	 */
	public void setBlock(boolean isBlock) {
		this.isBlocking = isBlock;
	}
}
