package gof.adapter;

/**
 * 窗口类 
 */
public class Window {
	
	private int size;
	private int location;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public void setWindowListener(WindowListener l){
		l.onMax();
	}
}
