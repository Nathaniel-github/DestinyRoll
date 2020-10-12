package destiny.core;

class Event {
	
	private final int mouseX, mouseY;
	
	public Event(int mX, int mY) {
		
		this.mouseX = mX;
		this.mouseY = mY;
		
	}
	
	public int getMouseX() {
		
		return mouseX;
		
	}
	
	public int getMouseY() {
		
		return mouseY;
		
	}
	
}
