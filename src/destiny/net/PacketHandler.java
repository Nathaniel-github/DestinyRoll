package destiny.net;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class PacketHandler {
	
	private static ServerPacket buffer;
	private static Client client = new Client();
	private static final String IPAddress = "34.94.121.72";
	private static final int port = 64545;
	private static final MyListener myListener;
	
	static {
		
		buffer = null;
		
		Kryo k = client.getKryo();
		k.register(ClientPacket.class);
		k.register(ServerPacket.class);
		
		client.start();
		try {
			client.connect(5000, IPAddress, port, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		myListener = new MyListener();
		
		client.addListener(myListener);

	}
	
	public static void sendPacket(ClientPacket packet) {
		
		client.sendUDP(packet);
		
	}
	
	static void addPacket(ServerPacket pack) {
		buffer = pack;
	}
	
	public static ServerPacket readBuffer() {
		
		if (buffer != null) {
			return buffer;
		}
		return null;
		
	}
	
}
