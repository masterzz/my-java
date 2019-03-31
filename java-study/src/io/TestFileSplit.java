package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

/**
 * 测试递归输出文件夹
 */
public class TestFileSplit {
	
	/**
	 * 测试文件切片
	 */
	@Test
	public void testSplit() throws Exception{
		//创建File对象和流
		File f = new File("d:\\111.txt");
		FileInputStream fis = new FileInputStream(f);
		
		//获取文件大小
		int len = (int)f.length();
		
		//定义块数
		int blocks = 3 ;
		
		//每个块大小
		int blockSize = len / blocks ;
		
		//定义缓冲区
		byte[] buf = new byte[1024];
		int len0 = 0 ;
		
		FileOutputStream fos = null ;
		for(int i = 0 ; i < blocks ; i ++){
			fos = new FileOutputStream(new File(f.getParent(), f.getName() + "-part-" + i));
			//是否是最后一个块
			if(i == (blocks - 1)){
				while(((len0 = fis.read(buf)) != -1)){
					fos.write(buf, 0, len0);
				}
				fos.close();
			}
			//不是最后一块
			else{
				//不足缓冲区
				if(blockSize <= buf.length){
					buf = new byte[blockSize];
					fis.read(buf);
					fos.write(buf);
					fos.close();
				}
				//超过缓冲区
				else{
					int count = blockSize / buf.length;
					for(int j = 0 ; j < count ; j ++){
						if(j == (count - 1)){
							if(blockSize % buf.length != 0){
								buf = new byte[buf.length + blockSize % buf.length];
								fis.read(buf);
								fos.write(buf);
								fos.close();
							}
						}
						else{
							fis.read(buf);
							fos.write(buf);
						}
					}
				}
			}
		}
		fis.close();
	}
}
 