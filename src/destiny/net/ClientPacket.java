//package destiny.net;
//
///**
// * 
// * This class holds all the data that the client sends to the server
// * 
// * @author Nathaniel
// * @version 12/5/2020
// */
//public class ClientPacket {
//	
//	private String data;
//	
//	/**
//	 * 
//	 * Creates a packet with the given data
//	 * 
//	 * @param stuff The data the packet should store
//	 */
//	public ClientPacket(String stuff) {
//		data = stuff;
//	}
//	
//	// This no args constructor is a MUST. It is required in order to deserialize the 
//	// packet properly
//	/**
//	 * 
//	 * Creates a packet with no data
//	 * 
//	 */
//	public ClientPacket() {}
//	
//	/**
//	 * 
//	 * Gets the data the packet is currently storing
//	 * 
//	 * @return The data being stored
//	 */
//	public String getData() {
//		return data;
//	}
//	
//	@Override
//	public String toString() {
//		return null;
//		// TODO
//	}
//	
//}
