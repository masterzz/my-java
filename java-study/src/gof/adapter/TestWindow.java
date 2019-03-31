package gof.adapter;

public class TestWindow {
	public static void main(String[] args) {
		Window w = new Window();
		w.setWindowListener(new WindowAdapter(){
			public void onMax() {
				System.out.println("hello");
			}
		});
	}
}

//预实现
class WindowAdapter implements WindowListener{
	public void onMin() {
	}

	public void onMax() {
	}

	public void onClose() {
	}

	public void onResize() {
	}
}
