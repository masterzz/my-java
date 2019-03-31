package io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 测试递归输出文件夹
 */
public class TestFile {
	
	@Test
	public void testPrintDir(){
		printDir("D:\\arch");
	}
	/**
	 * 打印输出目录结构
	 */
	private void printDir(String file){
		File f = new File(file);
		if(f.exists()){
			System.out.println(f.getAbsolutePath());
			if(f.isDirectory()){
				File[] ff = f.listFiles();
				if(ff != null && ff.length > 0 ){
					for(File f0 : ff){
						printDir(f0.getAbsolutePath());
					}
				}
			}
		}
	}
	
	/**
	 * 测试递归复制文件夹
	 */
	@Test
	public void testRecursiveDir(){
		copyDir("d:/arch", "e:/dest");
	}
	
	/**
	 * 打印输出目录结构
	 */
	private void copyDir(String file,String destDir){
		File f = new File(file);
		if(f.exists()){
			//如果file是目录
			if(f.isDirectory()){
				//创建目录
				File newFile = new File(destDir,f.getName());
				newFile.mkdirs();
				
				File[] ff = f.listFiles();
				if(ff != null && ff.length > 0 ){
					for(File f0 : ff){
						copyDir(f0.getAbsolutePath(),newFile.getAbsolutePath());
					}
				}
			}
			//复制文件
			else{
				copyFile(file,destDir);
			}
		}
	}
	
	/**
	 * 复制文件
	 */
	private void copyFile(String srcFile,String destDir){
		try {
			File file = new File(srcFile);
			//通过父目录和文件名创建file对象
			File newFile = new File(destDir, file.getName());
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len = 0 ;
			byte[] buf = new byte[1024];
			while((len = fis.read(buf)) != -1){
				fos.write(buf, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRenameTo() throws Exception{
		File f = new File("D:\\arch\\aaaa.txt");
		File newFile = new File("E:\\arch");
		newFile.mkdirs();
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		newFile = new File("E:\\arch\\aaaa.txt");
		f.renameTo(newFile);
	}
}
