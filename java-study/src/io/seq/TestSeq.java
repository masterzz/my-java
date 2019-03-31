package io.seq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSeq {
	/**
	 * 
	 */
	@Test
	public void testClass(){
		Jing8 jing8 = new Jing8();
		Class clazz = jing8.getClass();
		//
		System.out.println(clazz == Dog.class);
		//
		System.out.println(jing8 instanceof Dog);
	}
	
	class Dog{
		
	}
	class Jing8 extends Dog{
		
	}
	
	/**
	 * 测试序列
	 */
	@Test
	public void testSerialize() throws Exception{
		Integer i = 100 ;
		FileOutputStream fos = new FileOutputStream("d:/tmp.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(i);
		oos.close();
		fos.close();
		
	}
	/**
	 * 测试反序列
	 */
	@Test
	public void testDeSerialize() throws Exception{
		Integer i = 100 ;
		FileInputStream fis = new FileInputStream("d:/tmp.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Integer ii = (Integer)ois.readObject();
		System.out.println(ii);
		ois.close();
		fis.close();
	}
}
