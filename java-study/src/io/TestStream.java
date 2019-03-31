package io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * 测试流操作
 */
public class TestStream {
	/**
	 * 复制文件
	 */
	@Test
	public void testCopyFile() throws Exception {
		// 创建文件输入流
		FileInputStream fis = new FileInputStream("d:\\Koala.jpg");
		// 创建输出流对象
		FileOutputStream fos = new FileOutputStream("d:\\1.jpg");
		// 缓冲区
		byte[] buffer = new byte[1024];
		//
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}

		fos.close();
		fis.close();
		System.out.println("复制完成");
	}

	/**
	 * 读取文件 300M/s
	 */
	@Test
	public void testDiskReadRatio() throws Exception {
		FileInputStream fis = new FileInputStream("D:/test.txt");
		long start = System.currentTimeMillis();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			System.out.println(new String(buffer));
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 读取文件 150m/s
	 */
	@Test
	public void testDiskWriterRatio() throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/test.txt");
		long start = System.currentTimeMillis();
		byte[] arr = new byte[1024];
		Arrays.fill(arr, (byte) 97);
		for (int i = 0; i < 1024 * 1024; i++) {
			fos.write(arr);
		}
		fos.close();
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 读取文件 150m/s
	 */
	@Test
	public void testCharsetFile() throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/test.txt");
		byte[] arr = new byte[1023];
		Arrays.fill(arr, (byte) 97);
		fos.write(arr);
		fos.write("\ud585\u4e2d".getBytes("utf-8"));
		fos.close();
	}

	/**
	 * 测试复杂文件
	 */
	@Test
	public void testFuzaFile() throws Exception {
		FileInputStream fis = new FileInputStream("d:/test.txt");
		byte[] buf = new byte[6];
		int len = 0;
		while ((len = fis.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len, "utf-8"));
		}
		fis.close();
	}

	/**
	 * 测试复杂文件
	 */
	@Test
	public void testFuzaFile2() throws Exception {
		FileInputStream fis = new FileInputStream("d:/test.txt");
		byte[] buf = new byte[fis.available()];
		fis.read(buf);
		fis.close();
		System.out.println(new String(buf, "utf-8"));
	}

	/**
	 * 使用字符流:FileReader,使用默认的字符集,和工作空间的一致。
	 */
	@Test
	public void testFileReader() throws Exception {
		FileReader reader = new FileReader("d:/test.txt");
		reader.getEncoding();
		char[] buf = new char[5];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len));
		}
		reader.close();
	}

	/**
	 * inputStreamReader:转换流，将字节流转换成字符流。
	 */
	@Test
	public void testInputStreamReader() throws Exception {
		FileInputStream fis = new FileInputStream("d:/test.txt");
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		char[] buf = new char[5];
		int len = 0;
		while ((len = isr.read(buf)) != -1) {
			System.out.println(new String(buf, 0, len));
		}
		isr.close();
	}

	/**
	 * 标准流操作
	 */
	public void testStandardInputStream() {
		FileReader reader = null;
		try {
			reader = new FileReader("d:/test.txt");
			char[] buf = new char[1024];
			int len = 0;
			while ((len = reader.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试字符输出流
	 */
	@Test
	public void testFileWriter() {
		FileWriter w = null;
		try {
			w = new FileWriter("d:/test.txt", true);
			for (int i = 0; i < 100; i++) {
				w.write("jerry" + i + ",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (w != null) {
					w.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
