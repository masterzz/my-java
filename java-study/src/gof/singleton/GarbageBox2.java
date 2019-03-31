package gof.singleton;

/**
 * 单例模式,垃圾箱
 */
public class GarbageBox2 {
	
	//实例
	private static GarbageBox2 instance = new GarbageBox2(); 
	/**
	 * 构造私有
	 */
	private GarbageBox2(){
	}
	
	public static GarbageBox2 getInstance(){
		return instance ;
	}
}
