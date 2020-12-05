package destiny.net;

/**
 * 
 * This class holds all the data that the client sends to the server
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class ClientPacket {
	
	public ClientPacket(String stuff) {
		
	}
	
	// This no args constructor is a MUST. It is required in order to deserialize the 
	// packet properly
	public ClientPacket() {}
	
	@Override
	public String toString() {
		return null;
		// TODO
	}
	
}
