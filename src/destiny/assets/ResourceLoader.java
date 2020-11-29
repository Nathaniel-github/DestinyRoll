package destiny.assets;

import java.util.HashMap;

import destiny.core.PGif;

public class ResourceLoader {
	
	private static HashMap<String, PGif[]> sprites = new HashMap<>();
	
	private ResourceLoader() {}
	
	public static void loadResources() {
		
		for (int i = 1; i <= Constants.NUM_OF_CHARACTERS; i ++) {
			
			PGif[] actions = new PGif[4];
			
			for (int k = 0; k < 4; k ++) {
				actions[k] = new PGif(0, 0, "res/characters/" + i + "/asset" + i + "-" + Constants.getActionForInt(k) + ".gif");
			}
			
			sprites.put(Integer.toString(i), actions);
			
		}
		
	}
	
}
