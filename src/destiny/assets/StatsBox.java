package destiny.assets;

import org.bson.Document;

import processing.core.PApplet;

/**
 * 
 * A class that represents a statistics box for a character. It can be drawn to a PApplet
 * 
 * @author Nathaniel
 * @version 5/23/21
 *
 */
public class StatsBox {
	
    private final int totalHealth, totalMp, attack, defense;
    private final String rarity;
    private String[] stats;
    private String[] names;
    private int width, height, x, y;

    /**
     * 
     * Creates a stat box with the given parameters
     * 
     * @param character The document of the character the stat box is for
     * @param x The x coordinate of the top left of the box
     * @param y The y coordinate of the top left of the box
     * @param width The width of the box
     * @param height The height of the box
     */
	public StatsBox(Document character, int x, int y, int width, int height) {
		
		this.totalHealth = character.getInteger("health");
		this.totalMp = character.getInteger("mp");
		this.attack = character.getInteger("attack");
		this.defense = character.getInteger("defense");
		this.width = width;
		this.height = height;
		this.rarity = character.getString("rarity");
		this.x = x;
		this.y = y;
		stats = new String[]{String.valueOf(totalHealth), String.valueOf(totalMp), String.valueOf(attack), String.valueOf(defense), rarity};
		names = new String[]{"Total Health: ", "Total MP: ", "Attack: ", "Defense: ", "Rarity: "};
		
	}
	
	/**
	 * 
	 * Draws the stat box to the window
	 * 
	 * @param window The window that the stat box should be drawn to
	 * @post The given PApplet will have the stat box drawn to it
	 */
	public void draw(PApplet window) {
		
		window.pushStyle();
		window.textSize((width+height)/20);
		for (int i = 0; i < stats.length; i ++)
			window.text(names[i] + stats[i], x + 50, y + height/6*i);
		
		window.popStyle();
		
	}
	
}
