package destiny.net;

public class ClientPacket {

	public int px, py;
	public String keyCode;
	
	public ClientPacket(String code, int px, int py) {
		
		keyCode = code;
		this.px = px;
		this.py = py;
		
	}
	
	public ClientPacket() {
		
	}
	
	public String toString() {
		return keyCode + ", " + px + ", " + py;	
	}
	
}
