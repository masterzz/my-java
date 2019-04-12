package network.screenbroadcast.teacher;

import network.screenbroadcast.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 教师端
 */
public class Teacher {
	//机器人类
	private Robot robot ;
	//创建矩形，确定的屏幕区域
	private Rectangle rect ;
	
	public Teacher(){
		try {
			robot = new Robot();
			rect = new Rectangle(0, 0, 1366, 768);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 开启UdpSocket
	 */
	public void startServer(){
		try {
			DatagramSocket sock = new DatagramSocket(8888);
			
			//元数据 + 60K图像切割块
			byte[] buf = new byte[1024 * 60 + 14];
			//数据报包
			DatagramPacket pack = new DatagramPacket(buf, buf.length) ;
			//广播地址
			pack.setAddress(InetAddress.getByName("192.168.11.255"));
			//接受方端口
			pack.setPort(9999);
			
			//每次循环时，发送一屏(多块==多UDP包)
			while(true){
				BufferedImage image = captureScreen();
				List<byte[]> allBlocks = popBlocks(image);
				for(byte[] arr : allBlocks){
					pack.setData(arr);
					sock.send(pack);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 抓一屏幕画面
	 */
	private BufferedImage captureScreen(){
		//生成图像对象
		BufferedImage image = robot.createScreenCapture(rect);
		return image ;
	}
	
	/**
	 * 将一屏组进行切割
	 */
	private List<byte[]> popBlocks(BufferedImage screen){
		List<byte[]> list = new ArrayList<byte[]>();
		
		//结算时间戳
		long ts = System.currentTimeMillis() ;
		byte[] tsArr = Util.long2Bytes(ts);
		
		//每个数据量(不含最后一块)
		byte[] blockBuf ;
		
		//每块大小
		int blockSize = 60 * 1024 ;
		//总块数
		int blockCount = 0 ;
		try {
			//1.先进行压缩
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zout = new ZipOutputStream(baos);
			zout.putNextEntry(new ZipEntry("xxx"));
			ImageIO.write(screen, "jpg", zout);
			zout.close();
			
			//2.切割
			byte[] fullScreenZipArr = baos.toByteArray();
			int mod = fullScreenZipArr.length % blockSize ;
			//计算块数
			if(mod == 0){
				blockCount = fullScreenZipArr.length / blockSize ;
			}
			else{
				blockCount = fullScreenZipArr.length / blockSize + 1 ;
			}
			
			//当前块大小
			int currBlockSize = blockSize ;
			
			//3.生成集合
			for(int i = 0 ; i < blockCount ; i ++){
				//计算最后一块的问题
				if(mod != 0 && (i == (blockCount - 1))){
					currBlockSize = mod ;
				}
				
				//初始化块
				blockBuf = new byte[currBlockSize + 14];
				//1.设置时间戳
				System.arraycopy(tsArr, 0, blockBuf, 0, 8);
				//2.设置块数
				blockBuf[8] = (byte)blockCount ;
				//3.设置块编号
				blockBuf[9] = (byte)i;
				//4.设置块长度
				System.arraycopy(Util.int2Bytes(currBlockSize), 0, blockBuf, 10, 4);
				//5.设置数据
				System.arraycopy(fullScreenZipArr, (i * blockSize), blockBuf, 14, currBlockSize);
				
				//
				list.add(blockBuf);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
}
