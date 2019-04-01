package network.downloader;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载器
 */
public class Downloader {
	//url
	private String url ;
	//location
	private String location ;
	//线程数
	private int count ;
	
	//要下载的文件长度
	private int length ;
	//ui窗口
	private DownloaderUI ui ;
	
	public Downloader(String url, String location, int count,DownloaderUI ui ) {
		this.url = url;
		this.location = location;
		this.count = count;
		this.ui = ui ;
		//
		this.length = calcFileLength();
	}

	/**
	 * 获取服务器资源的文件长度
	 */
	private int calcFileLength() {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			return conn.getContentLength() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 开始下载
	 */
	public void startDownload() {
		//每个线程下载的数据量
		int blockSize = length / count ;
		int startPos;
		int endPos ;
		for(int i = 0 ; i < count ; i ++){
			startPos = i * blockSize ;
			if(i == (count - 1)){
				endPos = length - 1 ;
			}
			else{
				endPos = (i + 1) * blockSize - 1 ;
			}
			new DownloadThread(url,location,startPos,endPos,ui).start();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public DownloaderUI getUi() {
		return ui;
	}

	public void setUi(DownloaderUI ui) {
		this.ui = ui;
	}
}
