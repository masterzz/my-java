package io;

import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * 测试随机访问文件
 */
public class TestRandomAccessFile {
	
	@Test
	public void test1(){
		try {
			RandomAccessFile raf = new RandomAccessFile("d:\\111.txt","rw");
			//raf.seek(3);
			byte[] buf = new byte[2] ;
			int len = 0 ;
			while((len = raf.read(buf)) != -1){
				System.out.println(new String(buf,0,len));
			}
			raf.seek(raf.getFilePointer() - 1);
			//raf.skipBytes();
			len = 0 ;
			while((len = raf.read(buf)) != -1){
				System.out.println(new String(buf,0,len));
			}
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置文件大小
	 */
	@Test
	public void testRafLength(){
		try {
			RandomAccessFile raf = new RandomAccessFile("d:\\222.txt","rw");
			raf.setLength(1024);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置文件大小
	 */
	@Test
	public void testRafConent(){
		try {
			RandomAccessFile raf = new RandomAccessFile("d:\\222.txt","rw");
			byte[] buf = new byte[10];
			raf.read(buf);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
 