package jdbc.zip;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 测试压缩文件
 */
public class TestZip {
	/**
	 * 压缩文件
	 * 文件类型   文件大小    压缩大小   压缩时间
	 * --------------------------------------
	 * txt       428M		238m  80542ms
	 * txt       8.49k      8.62k 85ms
	 * txt       28.7k      256字节 37ms
	 */
	@Test
	public void testZip() throws Exception{
		long start = System.currentTimeMillis() ;
		FileOutputStream fout = new FileOutputStream("d:/baidu.txt.zip");
		ZipOutputStream zout = new ZipOutputStream(fout);
		
		FileInputStream fin = new FileInputStream("d:/baidu.txt");
		byte[] buf = new byte[1024 * 1024 * 8];
		int len = -1 ;
		//放置下一个条目
		zout.putNextEntry(new ZipEntry("baidu.txt"));
		while((len = fin.read(buf)) != -1){
			zout.write(buf , 0 , len);
		}
		fin.close();
		zout.close();
		fout.close();
		System.out.println("压缩完成!" + (System.currentTimeMillis() - start));
	}
	
	/**
	 * 解压缩
	 */
	@Test
	public void testUnzip() throws Exception{
		//
		FileInputStream fin = new FileInputStream("d:/baidu.txt.zip");
		//解压缩流
		ZipInputStream zin = new ZipInputStream(fin);
		
		FileOutputStream fout = new FileOutputStream("d:/baidu2.txt");
		byte[] buf = new byte[1024] ;
		int len = 0 ;
		
		//获取下一个条目
		zin.getNextEntry();
		while((len = zin.read(buf)) != -1){
			fout.write(buf, 0, len);
		}
		fout.close();
		zin.close();
		fin.close();
	}
	
	/**
	 * 抓屏
	 */
	@Test
	public void testPrintScreen() throws Exception{
		//机器人类
		Robot robot = new Robot();
		//创建矩形，确定的屏幕区域
		Rectangle rect = new Rectangle(0, 0, 1366, 768);
		
		//生成图像对象
		BufferedImage image = robot.createScreenCapture(rect);
		ImageIO.write(image, "jpg", new FileOutputStream("d:/1.jpg"));
	}
	
	/**
	 * 测试压缩
	 */
	@Test
	public void testZip1() throws Exception{
		String[] arr = {"d:/arch/a.txt","d:/arch/b.jpg","d:/arch/c.mp3"};
		FileOutputStream fout = new FileOutputStream("d:/arch/my.zip");
		ZipOutputStream zout = new ZipOutputStream(fout);
		FileInputStream fin = null ;
		ZipEntry entry = null ;
		
		File f = null ;
		byte[] buf = new byte[1024];
		int len = 0 ;
		
		//循环文件
		for(String s : arr){
			//创建文件对象
			f = new File(s);
			
			//创建压缩条目
			entry = new ZipEntry(f.getName()) ;
			zout.putNextEntry(entry);
			
			//开始压入文件
			fin = new FileInputStream(s);
			while((len = fin.read(buf)) != -1){
				zout.write(buf, 0, len);
			}
			zout.closeEntry();
			fin.close();
		}
		zout.close();
		fout.close();
		System.out.println("压缩完成");
	}
	
	/**
	 * 测试解压缩
	 */
	@Test
	public void testUnzip1() throws Exception{
		//
		FileInputStream fin = new FileInputStream("d:/arch/my.zip");
		//解压缩流
		ZipInputStream zin = new ZipInputStream(fin);
		
		FileOutputStream fout = null ;
		ZipEntry entry = null ;
		
		byte[] buf = new byte[1024];
		int len  = -1 ;
		while((entry = zin.getNextEntry()) != null){
			//解压过程
			fout  = new FileOutputStream("d:/arch/unzip/" + entry.getName());
			while((len = zin.read(buf)) != -1 ){
				fout.write(buf, 0, len);
			}
			fout.close();
			zin.closeEntry();
		}
		zin.close();
		fin.close();
		System.out.println("解压缩over");
	}
}
