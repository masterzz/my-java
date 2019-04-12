package network.qq.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 工具类
 */
public class Util {
	
	/**
	 * 将整型转换成字节数组
	 */
	public static byte[] int2Bytes(int i){
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(i >> 24) ;
		bytes[1] = (byte)(i >> 16) ;
		bytes[2] = (byte)(i >> 8) ;
		bytes[3] = (byte)(i >> 0) ;
		return bytes ;
	} 
	
	/**
	 * 将字节数组转换成整型
	 */
	public static int bytes2Int(byte[] bytes){
		return ((bytes[0] & 0xff) << 24)
			| ((bytes[1] & 0xff) << 16)
			| ((bytes[2] & 0xff) << 8)
			| ((bytes[3] & 0xff) << 0) ;
	} 
	
	/**
	 * 将字节数组转换成整型
	 */
	public static int bytes2Int(byte[] bytes,int offset){
		return ((bytes[0 + offset] & 0xff) << 24)
				| ((bytes[1 + offset] & 0xff) << 16)
				| ((bytes[2 + offset] & 0xff) << 8)
				| ((bytes[3 + offset] & 0xff) << 0) ;
	} 
	
	/**
	 * 将src对象串行化成byte[]
	 */
	public static byte[] serialize(Serializable src){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 将byte[]反串行成Object
	 */
	public static Serializable deSerialize(byte[] bytes){
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable)ois.readObject();
			ois.close();
			bais.close();
			return copy ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	

	/**
	 * 提取客户端ip和端口号
	 */
	public static String getUserInfo(Socket s) {
		InetSocketAddress addr = (InetSocketAddress) s.getRemoteSocketAddress();
		//远程ip地址
		String ip = addr.getAddress().getHostAddress();
		int port = addr.getPort();
		
		return ip + ":" + port ;
	}
}
