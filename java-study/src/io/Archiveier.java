package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 归档器
 */
public class Archiveier {
	/**
	 * 归档多个文件到一个归档文件中.
	 */
	public void archive(String archFile,String... srcFiles){
		for(String srcFile : srcFiles){
			appendFile(archFile,srcFile);
		}
	}

	/**
	 * 追加一个文件到归档文件中。 
	 * 1.处理文件名
	 *   1.1文件名长度
	 *   1.2文件名字节数组
	 * 2.处理文件内容
	 * 	 2.1内容长度
	 * 	 2.2内容数组
	 */
	public void appendFile(String archFile, String srcFile) {
		try {
			//创建归档文件输出流
			FileOutputStream fos = new FileOutputStream(archFile, true);
			
			//1.提取srcFile文件名==>d://a.txt --> a.txt
			String fileName = getFileName(srcFile);
			//2.存储文件名长度
			fos.write(int2ByteArr(fileName.length()));
			//3.存储文件名内容
			fos.write(fileName.getBytes());
			
			//4.存储文件内容长度
			FileInputStream fis = new FileInputStream(srcFile);
			fos.write(int2ByteArr(fis.available()));
			
			//5.存储文件内容
			byte[] buf = new byte[1024];
			int len = 0 ;
			while((len = fis.read(buf)) != -1){
				fos.write(buf, 0, len);
			}
			//
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提取文件名
	 */
	private String getFileName(String srcFilePath) {
		String sep = null ;
		if(srcFilePath.contains("\\")){
			sep = "\\" ;
		}
		else{
			sep = "/";
		}
		return srcFilePath.substring(srcFilePath.lastIndexOf(sep) + 1);
	}
	
	/**
	 *  解档文件到指定目录下
	 */
	public void unarchive(String archFile,String destDir){
		try {
			//
			FileInputStream fis = new FileInputStream(archFile);
			
			//文件名长度缓冲区
			byte[] fnameLenBuf = new byte[4];
			while((fis.read(fnameLenBuf)) != -1){
				//读取4个字节的文件名长度
				int fnameLength = byteArr2Int(fnameLenBuf);
				
				//读取文件名
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);
				
				//文件内容长度
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);
				
				//输出归档文件的内容
				FileOutputStream fos = new FileOutputStream(destDir + "/" + fname);
				byte[] buf = new byte[1024];
				
				int mod = flen % buf.length ;
				int count = 0 ;
				if(mod == 0){
					count = flen / buf.length  ;
				}
				else{
					count = flen / buf.length + 1 ;
				}
				//循环读取文件内容
				for(int i = 0 ; i < count ; i ++){
					//最后一次
					if(i == (count - 1)){
						mod = (mod == 0 ? buf.length : mod) ;
						fis.read(buf, 0, mod);
						fos.write(buf, 0, mod);
						fos.close();
					}
					else{
						fis.read(buf);
						fos.write(buf);
					}
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  提取归档文件内的所有文件名
	 */
	public List<String> getAllFileNames(String archFile){
		List<String> names = new ArrayList<String>();
		try {
			//
			FileInputStream fis = new FileInputStream(archFile);
			
			//文件名长度缓冲区
			byte[] fnameLenBuf = new byte[4];
			while((fis.read(fnameLenBuf)) != -1){
				//读取4个字节的文件名长度
				int fnameLength = byteArr2Int(fnameLenBuf);
				
				//读取文件名
				byte[] fnameBuf = new byte[fnameLength];
				fis.read(fnameBuf);
				String fname = new String(fnameBuf);
				//添加文件名
				names.add(fname);
				
				//文件内容长度
				byte[] fcontBuf = new byte[4];
				fis.read(fcontBuf);
				int flen = byteArr2Int(fcontBuf);
				
				//跳过内容
				fis.skip(flen);
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names ;
	}
	
	/**
	 * 将整数转换成字节数组
	 */
	private byte[] int2ByteArr(int i){
		byte[] arr = new byte[4] ;
		arr[0] = (byte)(i >> 24) ;
		arr[1] = (byte)(i >> 16) ;
		arr[2] = (byte)(i >> 8) ;
		arr[3] = (byte)(i >> 0) ;
		return arr ;
	}
	
	/**
	 * 将整数转换成字节数组
	 */
	private int byteArr2Int(byte[] arr){
		return arr[0] << 24 
				| ((arr[1] & 0xff) << 16)
				|  ((arr[2] & 0xff) << 8)
				|  ((arr[3] & 0xff) << 0);
	}
}
