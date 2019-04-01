package network.downloader;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载线程
 */
public class DownloadThread extends Thread {
	private String url ;
	private String location ;
	private int startPos ;
	private int endPos ;
	private DownloaderUI ui ;
	
	public DownloadThread(String url, String location, int startPos, int endPos,DownloaderUI ui) {
		this.url = url;
		this.location = location;
		this.startPos = startPos;
		this.endPos = endPos;
		this.ui = ui ;
	}

	public void run() {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			//设置请求属性
			//*****Range  bytes=startPos-endPos*****
			conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			InputStream is = conn.getInputStream();
			
			//本地文件写入
			RandomAccessFile raf = new RandomAccessFile(location, "rw");
			//定位文件位置
			raf.seek(startPos);
			
			byte[] buf = new byte[1024];
			int len = -1 ;
			while((len = is.read(buf)) != -1){
				raf.write(buf, 0, len);
				//设置进度条进度
				synchronized (ui.bar) {
					ui.bar.setValue(ui.bar.getValue() + len);
				}
			}
			raf.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}