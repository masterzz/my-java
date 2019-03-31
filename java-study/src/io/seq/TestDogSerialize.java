package io.seq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestDogSerialize {

	/**
	 * 串行化dog对象
	 */
	@Test
	public void testSer() throws Exception{
		FileOutputStream fos = new FileOutputStream("d:/dog.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Dog dog = new Dog("大黄", 3);
		oos.writeObject(dog);
		oos.close();
		fos.close();
	}
	
	/**
	 * 串行化dog对象
	 */
	@Test
	public void testDeSer() throws Exception{
		FileInputStream fis = new FileInputStream("d:/dog.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Dog d = (Dog)ois.readObject();
		System.out.println(d.getName());
		System.out.println(d.getAge());
		System.out.println(d.getColor());
		ois.close();
		fis.close();
	}
	

	/**
	 * 串行化dog对象
	 * 实现深度复制
	 */
	@Test
	public void testDogSer2() throws Exception{
		//串行化
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		Dog dog = new Dog("大黄", 3);
		Person p = new Person();
		Cat cat = new Cat("加肥");
		//设置关联关系
		dog.setOwner(p);
		dog.setPartner(cat);
		cat.setOwner(p);
		//
		oos.writeObject(dog);
		oos.writeObject(dog);
		oos.close();
		baos.close();
		//
		byte[] bytes = baos.toByteArray();
		System.out.println();
		
		//反串行化
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Dog copyDog = (Dog) ois.readObject();
		Dog copyDog1 = (Dog) ois.readObject();
		ois.close();
		bais.close();
		System.out.println();
	}
}