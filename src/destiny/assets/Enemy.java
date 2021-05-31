package destiny.assets;

import destiny.core.PGif;

/**
 * 
 * A class that represents an Enemy
 * 
 * @author Nathaniel
 * @version 5/23/21
 *
 */
public class Enemy extends Character {

	String name;
	/**
	 * 
	 * Creates an enemy from the given parameters
	 * 
	 * @param id The id of the character that the enemy should take stats from
	 * @param name The name of the character
	 */
	public Enemy(int id, String name) {
		
		super(id);
		this.name = name;
        spriteStatic = new PGif(0, 0, Constants.getEnemyPath(name,"static"));
        spriteAttack = new PGif(0, 0, Constants.getEnemyPath(name,"attack"));
        spriteFlinch = new PGif(0, 0, Constants.getEnemyPath(name,"flinch"));
        spriteMove = new PGif(0, 0, Constants.getEnemyPath(name,"move"));
		
        sprite = spriteStatic;
        
        
        sprites = new PGif[]{spriteAttack, spriteFlinch, spriteMove, spriteStatic};
        
        for (PGif i : sprites) {
        	i.flipHorizontal();
        	if(name.equals("kumar")) {
        		i.resize(Constants.scaleIntToWidth(450), Constants.scaleIntToHeight(500));
        		this.buff(1.5);
        	}else if(name.equals("nathaniel")){
        		i.scaleByWidth(Constants.scaleIntToWidth(300));
        		i.setCoords(Constants.scaleIntToWidth(i.getX()), Constants.scaleIntToWidth(200));
        	}else {
        		i.scaleByWidth(Constants.scaleIntToWidth(300));
        		i.setCoords(Constants.scaleIntToWidth(i.getX()), Constants.scaleIntToWidth(200));
        	}
        }
        
	}
	/**
	 * Reset the Coordinates to the coordinates it should be
	 */
	public void resetSprites() {
		for (PGif i : sprites) {
        	if(name.equals("kumar")) {
        		i.resize(Constants.scaleIntToWidth(300), Constants.scaleIntToWidth(490));
        		i.setCoords(i.getX(), i.getY() + Constants.scaleIntToHeight(50));
        		this.buff(1.5);
        	}else if(name.equals("nathaniel")){
        		i.resize(Constants.scaleIntToWidth(300), Constants.scaleIntToWidth(400));
        		i.setCoords(i.getX(), Constants.scaleIntToHeight(230));
        	}else {
        		i.scaleByHeight(Constants.scaleIntToWidth(400));
        		i.setCoords(i.getX(), Constants.scaleIntToHeight(200));
        	}
        }
	}
}
