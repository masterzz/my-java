package jdbc.screenbroadcast.student;

import jdbc.screenbroadcast.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 接受者线程
 */
public class ReceiverThread extends Thread {
	//收集所有帧单元
	private Map<String, FrameUnit> allUnits = new HashMap<String,FrameUnit>();
	
	private DatagramSocket sock ;
	
	private StudentUI ui ;
	
	public ReceiverThread(StudentUI ui){
		try {
			this.ui = ui ;
			sock = new DatagramSocket(9999);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		//缓冲区，需要不小于发送方的包大小
		byte[] buf = new byte[64 * 1024];
		
		//数据报包(接受数据报)
		DatagramPacket pack = new DatagramPacket(buf, buf.length);
		
		while(true){
			try {
				sock.receive(pack);
				//接受到的包总长度
				pack.getLength();
				//解析包
				FrameUnit unit = parsePack(pack);
				//处理帧单元
				processUnit(unit);
				//处理一帧
				processFrame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理一帧:帧单元是否收集齐全
	 */
	private void processFrame() {
		//
		if(!allUnits.isEmpty()){
			//已经收集的个数
			int collectedUnits = allUnits.size();
			//所有unit数量
			int allUnitCount = allUnits.values().iterator().next().getCount();
			//如果收集全了
			if(collectedUnits == allUnitCount){
				//生成图片
				genImage();
			}
		}
	}

	/**
	 * 生成图像
	 */
	private void genImage() {
		try {
			long ts = allUnits.values().iterator().next().getTimestamp();
			FrameUnit unit = null ;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//收集所有压缩帧单元数据
			for(int i = 0 ; i < allUnits.size() ; i ++){
				unit = allUnits.get( ts + "-" + i);
				baos.write(unit.getUnitData());
			}
			//得到压缩的帧画面
			byte[] frameData = baos.toByteArray();
			
			//解压，写入baos
			ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(frameData));
			zin.getNextEntry();
			
			byte[] buf = new byte[1024];
			int len = -1 ;
			
			//创建baos，容纳解压的帧画面数据
			baos = new ByteArrayOutputStream() ;
			while((len = zin.read(buf)) != -1){
				baos.write(buf, 0, len);
			}
			zin.close();
			//得到解压的帧画面数据
			byte[] unzipFrameData = baos.toByteArray();
			//刷新UI的画面
			ui.updateIcon(unzipFrameData);
			//清除allUnits
			allUnits.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理帧单元
	 */
	private void processUnit(FrameUnit unit) {
		if(allUnits.isEmpty()){
			allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
		}
		else{
			//
			long ts = unit.getTimestamp();
			
			//方法链
			//long ts0 = allUnits.values().iterator().next().getTimestamp() ;
			//
			Collection<FrameUnit> values = allUnits.values();
			Iterator<FrameUnit> it = values.iterator();
			FrameUnit fu = it.next();
			long ts0 = fu.getTimestamp();
			//同一帧
			if(ts == ts0){
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
			}
			//新帧
			else if(ts > ts0){
				allUnits.clear();
				allUnits.put(unit.getTimestamp() + "-" + unit.getIndex(), unit);
			}
			//旧帧
			else{
				return ;
			}
			
			
		}
	}

	/**
	 * 解析包,返回帧单元对象
	 */
	private FrameUnit parsePack(DatagramPacket pack){
		byte[] buf = pack.getData();
		//1.时间戳
		long timestamp = Util.bytes2Long(buf);
		//2.blockCount
		int blockCount = buf[8];
		//3.blockIndex
		int blockIndex = buf[9];
		//blockLen
		int blockLen = Util.bytes2Int(buf , 10);
		//
		byte[] blockData = new byte[blockLen] ;
		System.arraycopy(buf, 14, blockData, 0, blockLen);
		
		return new FrameUnit(timestamp, blockIndex, blockCount , blockData);
	}
}
