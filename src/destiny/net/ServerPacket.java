package destiny.net;

/**
 * 
 * This class holds all data that the server sends to the client
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class ServerPacket {
	
	private String data;
	
	public ServerPacket(String stuff) {
		data = stuff;
	}
	// This no args constructor is a MUST. It is required in order to deserialize the packet properly
	public ServerPacket() {}
	
	public String getData() {
		return data;
	}
	
	public String toString() {
		return null;
		//TODO
	}
	
}
