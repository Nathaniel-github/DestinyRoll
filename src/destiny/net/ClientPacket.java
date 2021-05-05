package destiny.net;

/**
 * 
 * This class holds all the data that the client sends to the server
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class ClientPacket {
	
	private String data;
	
	public ClientPacket(String stuff) {
		data = stuff;
	}
	
	// This no args constructor is a MUST. It is required in order to deserialize the 
	// packet properly
	public ClientPacket() {}
	
	public String getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return null;
		// TODO
	}
	
}
