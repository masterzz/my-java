package network.downloader1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	
	//下载线程信息集合
	private List<DownloadInfo> downloadInfos ;
	
	//内存中的下载信息
	public Properties prop ;
	
	/**
	 * 下载器
	 */
	public Downloader(String url, String location, int count,DownloaderUI ui ) {
		this.url = url;
		this.location = location;
		this.count = count;
		this.ui = ui ;
		//初始化下载线程信息集合
		initDownloadInfos();
	}

	/**
	 * 初始化下载线程信息集合
	 */
	private void initDownloadInfos() {
		//1.创建集合
		downloadInfos = new ArrayList<DownloadInfo>();
		//1.判断是新传还是续传
		if(!isFirstDownload()){
			//2.计算文件大小
			int len = calcFileLength();
			//3.计算每个线程块大小
			int blockSize = len / count ;
			//4.循环创建下载信息
			for(int i = 0 ; i < count ; i ++){
				int startPos = i * blockSize ;
				int endPos = 0;
				if(i == (count - 1)){
					endPos = len - 1 ;
				}
				else{
					endPos = (i + 1) * blockSize - 1 ;
				}
				DownloadInfo di = new DownloadInfo(i, url, location, startPos, endPos,0);
				downloadInfos.add(di);
			}
			//TODO 5.生成下载信息元数据文件
			createMetaFile(downloadInfos);
		}
		//续传
		else{
			try {
				FileInputStream fis = new FileInputStream(location + ".meta");
				prop = new Properties();
				prop.load(fis);
				//线程数
				int count = Integer.parseInt(prop.getProperty("thread.count"));
				for(int i = 0 ; i < count ; i ++){
					int startPos = Integer.parseInt(prop.getProperty("thread." + i + ".startPos"));
					int endPos = Integer.parseInt(prop.getProperty("thread." + i + ".endPos"));
					int amount = Integer.parseInt(prop.getProperty("thread." + i + ".amount"));
					DownloadInfo di = new DownloadInfo(i, url, location, startPos, endPos,amount);
					downloadInfos.add(di);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建元数据文件
	 */
	private void createMetaFile(List<DownloadInfo> list) {
		try {
			prop = new Properties();
			prop.setProperty("thread.count", list.size() + "");
			for(DownloadInfo di : list){
				prop.setProperty("thread." + di.getIndex() + ".startPos", di.getStartPos() + "");
				prop.setProperty("thread." + di.getIndex() + ".endPos", di.getEndPos() + "");
				prop.setProperty("thread." + di.getIndex() + ".amount", di.getAmount() + "");
			}
			
			File file = new File(location + ".meta") ;
			FileOutputStream fos = new FileOutputStream(file);
			prop.store(fos,"this is comment:)");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是否是首次下载
	 */
	private boolean isFirstDownload() {
		return new File(location + ".meta").exists();
	}

	/**
	 * 获取服务器资源的文件长度
	 */
	private int calcFileLength() {
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			int len =  conn.getContentLength() ;
			conn.disconnect();
			return len ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 开始下载
	 */
	public void startDownload() {
		//开启下载线程
		for(DownloadInfo di : downloadInfos){
			new DownloadThread(di, ui,this.prop).start();
		}
		//
		new BackGroundWriteMetaThread(prop,location).start();
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

	public List<DownloadInfo> getDownloadInfos() {
		return downloadInfos;
	}

	public void setDownloadInfos(List<DownloadInfo> downloadInfos) {
		this.downloadInfos = downloadInfos;
	}
	
	public void deleteMetaFile(){
		File f = new File(location + ".meta") ;
		f.delete();
	}
}
