package gof.factory;

import org.junit.Test;

/**
 * 工厂设计模式
 */
public class TestFactory {
	
	@Test
	public void test1(){
		TVSet tv = TVSetFactory.productTV();
		System.out.println(tv.getBrand());
	}
}
