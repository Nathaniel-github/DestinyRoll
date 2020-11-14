package destiny.net;

public class ServerPacket {

	public double myX, myY, oX, oY, pX, pY;
	
	public ServerPacket(double x, double y, double ox, double oy, double px, double py) {
		
		this.myX = x;
		this.myY = y;
		this.oX = ox;
		this.oY = oy;
		this.pX = px;
		this.pY = py;
		
	}
	
	public ServerPacket() {
		
	}
	
	public String toString() {
		return myX + ", " + myY + ", " + oX + ", " + oY + ", " + pX + ", " + pY;
	}
	
}
