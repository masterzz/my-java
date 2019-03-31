package gof.singleton;

/**
 * 单例模式,垃圾箱
 */
public class GarbageBox {
	
	//实例
	private static GarbageBox instance ; 
	private static Object lock = new Object();
	/**
	 * 构造私有
	 */
	private GarbageBox(){
	}
	
	public static GarbageBox getInstance(){
		if(instance == null){
			synchronized (lock) {
				if(instance == null ){
					instance = new GarbageBox();
				}
				return instance ;
			}
		}
		return instance ;
	}
}
