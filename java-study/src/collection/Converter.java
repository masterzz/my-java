package collection;

import org.junit.Test;

public class Converter {
	
	@Test
	public void test3(){
		int i = -15 ; 
		System.out.println(toHexString(i));
	}
	
	@Test
	public void test2(){
		byte b = (byte)0xff ;
		System.out.println(b);
	}
	
	@Test
	public void test1(){
		int  i = -1 ;
		byte[] arr = int2ByteArray(i);
		outArr(arr);
		System.out.println(byteArr2Int(arr));
		
	}
	/**
	 * 将整数转换成字节数组
	 */
	public byte[] int2ByteArray(int i){
		byte[] arr = new byte[4];
		arr[0] = (byte)(i >> 24);
		arr[1] = (byte)(i >> 16);
		arr[2] = (byte)(i >> 8);
		arr[3] = (byte)i;
		return arr ;
	}
	
	/**
	 * 
	 */
	public int byteArr2Int(byte[] arr){
		int i0 = arr[0] << 24 ;
		int i1 = (arr[1] << 16) & 0x00ffffff ;
		int i2 = (arr[2] << 8) & 0x0000ffff ;
		int i3 = arr[3] & 0x000000ff ;
		return i0 | i1 | i2 | i3 ;
	}
	
	/**
	 * 
	 */
	public int byteArr2Int0(byte[] arr){
		int i0 = (arr[0] & 0xff) << 24 ;
		int i1 = (arr[1] & 0xff) << 16 ;
		int i2 = (arr[2] & 0xff) << 8 ;
		int i3 = arr[3] & 0xff ;
		return i0 | i1 | i2 | i3 ;
	}
	
	/**
	 * 输出数组
	 */
	private void outArr(byte[] arr){
		for(int i = 0 ; i < arr.length ; i ++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 
	 */
	public String toHexString(int x){
		char[] arr = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'} ;
		StringBuilder builder = new StringBuilder();
		int y = x ;
		if(x < 0 ){
			y = -x ;
			builder.append("-0x");
		}
		else{
			builder.append("0x");
		}
		for(int i = 7 ; i >= 0 ; i -- ){
			int temp = (y >> (i * 4) & 0xf);//0-15
			builder.append(arr[temp]);
		}
		return builder.toString();
	}
	
	
	
}
