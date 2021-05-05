package destiny.net;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

/**
 * 
 * This class handles all incoming and outgoing packets between client and server
 * 
 * @author Nathaniel
 * @version 12/5/2020
 */
public class PacketHandler {
	
	private static Client client = new Client();
	private static final String IPAddress = "34.94.121.72";
	private static final int port = 64545;
	
	static {
		
		Kryo k = client.getKryo();
		k.register(ClientPacket.class);
		k.register(ServerPacket.class);
		
		client.start();
		try {
			client.connect(5000, IPAddress, port, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client.addListener(new MyListener());

	}
	
	public static void sendPacket(ClientPacket packet) {
		
		client.sendTCP(packet);
		
	}
	
	static void deliverPacket(ServerPacket pack) {
		// TODO
	}
	
}
