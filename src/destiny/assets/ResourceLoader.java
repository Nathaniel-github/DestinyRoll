package destiny.assets;

/**
 * 
 * A class to load all resources statically and hold them until used
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
import java.util.HashMap;

import destiny.core.PGif;

public class ResourceLoader {

	private static HashMap<String, PGif[]> sprites = new HashMap<>();

	private ResourceLoader() {
	}

	private static void loadResource(String i) {

		if (sprites.containsKey(i))
			return;
		
		PGif[] actions = new PGif[4];

		for (int k = 0; k < 4; k++) {
			actions[k] = new PGif(0, 0, "res/characters/" + i + "/asset" + i + "-" + Constants.getActionForInt(k) + ".gif");
		}

		sprites.put(i, actions);

	}
	
	public static PGif[] getSpriteData(String name) {
		
		loadResource(name);
		
		return sprites.get(name);
		
	}

}
