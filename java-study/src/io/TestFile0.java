package io;

import org.junit.Test;

import java.io.File;

/**
 * File类
 */
public class TestFile0 {
	@Test
	public void test1() throws Exception{
		File f = new File("d:\\arch0\\a") ;
		System.out.println(f.exists());
		System.out.println(f.isDirectory());
		System.out.println(f.isFile());
		//创建一系列目录
		//f.mkdirs();
		//创建空文件
		//f.createNewFile();
		f = new File("d:\\downloads") ;
		File[] files = f.listFiles();
		for(File ff : files){
			//绝对路径
			//System.out.println(ff.getAbsolutePath());
			//文件名
			//System.out.println(ff.getName());
			//得到正规的路径名
			//System.out.println(ff.getCanonicalPath());
		}
		
		//
		File fff = new  File("D:\\downloads\\..\\downloads\\bigdata\\hbase-1.2.3\\bin\\hbase.cmd") ;
		System.out.println(fff.getCanonicalPath());
		System.out.println(fff.getAbsolutePath());
		File f4 = new File(".");
		System.out.println(f4.getAbsolutePath());
		
		System.out.println("File.pathSeparator : " + File.pathSeparator);
		System.out.println("File.pathSeparatorChar : " + File.pathSeparatorChar);
		System.out.println("File.separator : " + File.separator);
		System.out.println("File.separatorChar : " + File.separatorChar);
		
		File[] roots = File.listRoots();
		for(File f0 : roots){
			System.out.println(f0.getAbsolutePath());
		}
	}
	
	
}
