package jdbc.screenbroadcast;

import org.junit.Test;

/**
 * 工具类
 */
public class Util {
	
	/**
	 * 将整型转换成字节数组
	 */
	public static byte[] long2Bytes(long i){
		byte[] bytes = new byte[8];
		bytes[0] = (byte)(i >> 56) ;
		bytes[1] = (byte)(i >> 48) ;
		bytes[2] = (byte)(i >> 40) ;
		bytes[3] = (byte)(i >> 32) ;
		bytes[4] = (byte)(i >> 24) ;
		bytes[5] = (byte)(i >> 16) ;
		bytes[6] = (byte)(i >> 8) ;
		bytes[7] = (byte)(i >> 0) ;
		return bytes ;
	} 
	
	/**
	 * 将字节数组转换成整型
	 */
	public static long bytes2Long(byte[] bytes){
		return ((bytes[0] & 0xffL) << 56)
			| ((bytes[1] & 0xffL) << 48)
			| ((bytes[2] & 0xffL) << 40)
			| ((bytes[3] & 0xffL) << 32)
			| ((bytes[4] & 0xffL) << 24)
			| ((bytes[5] & 0xffL) << 16)
			| ((bytes[6] & 0xffL) << 8)
			| ((bytes[7] & 0xffL) << 0) ;
	} 
	

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
	public static int bytes2Int(byte[] bytes , int offset){
		return ((bytes[0 + offset] & 0xff) << 24)
				| ((bytes[1 + offset] & 0xff) << 16)
				| ((bytes[2 + offset] & 0xff) << 8)
				| ((bytes[3 + offset] & 0xff) << 0) ;
	} 
	
	@Test
	public void test1(){
		long time = Long.MIN_VALUE;
		System.out.println(time);
		byte[] arr = long2Bytes(time);
		long time2 = bytes2Long(arr);
		System.out.println(time2);
	}
}
