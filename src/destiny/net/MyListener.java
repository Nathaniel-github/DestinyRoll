package destiny.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

class MyListener extends Listener {
	
	@Override
	public void received (Connection connection, Object object) {
		
		if (object instanceof ServerPacket) {
			PacketHandler.deliverPacket((ServerPacket)object);
		}
		
	}
	
	@Override
    public void disconnected(Connection c){
        System.out.println("Disconnected from server!");
    }
	
}
