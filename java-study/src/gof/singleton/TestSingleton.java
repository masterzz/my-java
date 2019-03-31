package gof.singleton;

import org.junit.Test;

/**
 * 测试单例模式
 */
public class TestSingleton {
	
	@Test
	public void testSingleton(){
		GarbageBox box = GarbageBox.getInstance();
		System.out.println(box.toString());
		System.out.println(GarbageBox.getInstance());
		System.out.println(GarbageBox.getInstance());
		System.out.println(GarbageBox.getInstance());
	}
	
	/**
	 * 
	 */
	public static void main(String[] args){
		new Thread(){
			public void run() {
				GarbageBox box = GarbageBox.getInstance();
				System.out.println(box);
			}
		}.start();
		
		
		new Thread(){
			public void run() {
				GarbageBox box = GarbageBox.getInstance();
				System.out.println(box);
			}
		}.start();
		
	}
}
