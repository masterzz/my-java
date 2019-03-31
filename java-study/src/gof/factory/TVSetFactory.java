package gof.factory;

/**
 * 工厂设计模式 
 */
public class TVSetFactory {
	
	/**
	 * 静态工厂方法
	 */
	public static TVSet productTV(){
		TVSet tv = new TVSet();
		tv.setBrand("三星");
		tv.setName("xxx");
		tv.setPrice(500.f);
		return tv ;
	}
}
