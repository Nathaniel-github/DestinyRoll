package destiny.assets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Timer;

import org.bson.Document;

import destiny.net.MongoHandler;

/**
 * 
 * A class that represents this user. It keeps track of general resources and data of this player
 * 
 * @author Nathaniel
 * @version 5/23/21
 *
 */
public class Player {

	private static int stamina;
	private static int currency;
	private static ArrayList<Integer> characters;
	private static double staminaPerMin = 1;
	private static String userName;
	private static int levelsUnlocked;
	private static int thisLevel;
	private static Timer staminaUpdate = new Timer(60000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			stamina += 1;
			if (stamina > 100)
				stamina = 100;
			else
				MongoHandler.updateStamina(userName, stamina, new Date(System.currentTimeMillis()));
			
			loadFromDocument(MongoHandler.getUserDoc(userName));
			
		}
		
	});
	
	
	private Player() {};
	
	/**
	 * 
	 * Loads the player data from the given document
	 * 
	 * @param d The document to load player data from
	 */
	@SuppressWarnings("unchecked")
	public static void loadFromDocument(Document d) {
		
		characters = d.get("characters", new ArrayList<Integer>().getClass());
		stamina = d.getInteger("stamina");
		stamina += (new Date(System.currentTimeMillis()).getTime() - d.getDate("last_update").getTime())/(int)(60000/staminaPerMin);
		if (stamina > 100)
			stamina = 100;
		currency = d.getInteger("currency");
		userName = d.getString("_id");
		staminaUpdate.start();
		levelsUnlocked = d.getInteger("levels_unlocked");
		
	}

	/**
	 * 
	 * Gets the stamina of this player
	 * 
	 * @return The stamina
	 */
	public static int getStamina() {
		return stamina;
	}
	/**
	 * 
	 * Gets the level this player has currently unlocked up till
	 * 
	 * @return level The level
	 */
	public static int getLevel() {
		return thisLevel;
	}
	/**
	 * 
	 * Sets the level to the given level
	 * 
	 * @param level The level to set to
	 */
	public static void setLevel(int level) {
		thisLevel = level;
	}
	
	/**
	 * 
	 * Decreases the stamina by the given amount
	 * 
	 * @param amt The amount to decrease the stamina by
	 */
	public static void decreaseStamina(int amt) {
		stamina -= amt;
		MongoHandler.updateStamina(userName, stamina, new Date(System.currentTimeMillis()));
	}

	/**
	 * 
	 * Gets the number of levels that have been unlocked by this player
	 * 
	 * @return The levelsUnlocked
	 */
	public static int getLevelsUnlocked() {
		return levelsUnlocked;
	}

	/**
	 * 
	 * Gets the currency the player currently has
	 * 
	 * @return The currency
	 */
	public static int getCurrency() {
		return currency;
	}
	
	/**
	 * 
	 * Uses the given amount of currency if possible, otherwise returns false and doesn't use anything
	 * 
	 * @param amt the amount of currency that should be used
	 * @return Whether or not the currency usage was successful
	 */
	public static boolean useCurrency(int amt) {
		if (currency < amt)
			return false;
		currency -= amt;
		MongoHandler.updateCurrency(userName, currency);
		return true;
	}
	
	/**
	 * 
	 * Adds the given amount of currency to the user
	 * 
	 * @param amt The amount of currency to be added
	 */
	public static void addCurrency(int amt) {
		currency += amt;
		MongoHandler.updateCurrency(userName, currency);
	}

	/**
	 * @return The characters
	 */
	public static ArrayList<Integer> getCharacters() {
		return characters;
	}
	
	/**
	 * 
	 * Unlocks the next level if there are any levels left to unlock
	 * 
	 */
	public static void passLevel() {
		
		if (levelsUnlocked < 20) {
			levelsUnlocked++;
			MongoHandler.progressLevel(userName);
		}
		
	}
	
	/**
	 * 
	 * Gets the username of this player
	 * 
	 * @return The username
	 */
	public static String getUserName() {
		return userName;
	}
}
