package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 测试流操作
 */
public class TestBufferedStream {
	/**
	 * 测试字符串缓冲区输入流
	 * @throws Exception 
	 */
	@Test
	public void testBufferedReader() throws Exception{
		FileInputStream fis = new FileInputStream("d:/test.txt");
		byte[] arr = new byte[12];
		fis.read(arr);
		fis.close();
		
		System.out.println((int)'\r');
		System.out.println((int)'\n');
		FileReader reader = new FileReader("d:/test.txt");
		BufferedReader br = new BufferedReader(reader);
		String line = null ;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		br.close();
	}
	
	/**
	 * 测试FileReader和BufferedReader读取文件的差距
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testReaderNBufferedReader() throws Exception{
		String file = "F:\\duowan_user.txt" ;
		//FileReader
		FileReader fr = new FileReader(file);
		//
		char[] buf = new char[1024 * 8];
		long start = System.currentTimeMillis() ;
		while(fr.read(buf) != -1){
		}
		fr.close();
		System.out.println(System.currentTimeMillis() - start);
		
		//BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(file));
		start = System.currentTimeMillis() ;
		while(br.readLine() != null){
		}
		System.out.println(System.currentTimeMillis() - start);
		br.close();
	}
	
	/**
	 * 测试不同缓冲区大小的性能
	 * @throws Exception 
	 */
	@Test
	public void testBufferedReaderPerformance() throws Exception{
		FileReader fr = null ;
		BufferedReader br = null ;
		for(int size = 1024 ; size < 1024 * 1024 * 10 ; size = size * 2){
			br = new BufferedReader(new FileReader("F:\\duowan_user.txt"),size);
			long start = System.currentTimeMillis() ;
			while(br.readLine() != null){
			}
			System.out.println((size / 1024) + " k : " +( System.currentTimeMillis() - start));
		}
	}
	
	/**
	 * 测试不同缓冲区大小的性能
	 * @throws Exception 
	 */
	@Test
	public void testBufferedReaderPerformance2() throws Exception{
		FileReader br = null ;
		for(int size = 1024 ; size < 1024 * 1024 * 10 ; size = size * 2){
			br = new FileReader("F:\\duowan_user.txt");
			long start = System.currentTimeMillis() ;
			char[] buf = new char[size];
			while(br.read(buf) != -1){
			}
			System.out.println((size / 1024) + " k : " +( System.currentTimeMillis() - start));
		}
	}
	
	/**
	 * 测试流中指针的skip功能
	 * @throws Exception 
	 */
	@Test
	public void testInputStreamSkip() throws Exception{
		FileInputStream fis = new FileInputStream("d:/test.txt") ;
		System.out.println((char)fis.read());
		fis.skip(-1);
		System.out.println((char)fis.read());
		System.out.println((char)fis.read());
		fis.close();
	}
}
