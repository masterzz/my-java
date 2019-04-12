package network.downloader1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 后台写入元数据线程
 */
public class BackGroundWriteMetaThread extends Thread {
	//属性集合
	private Properties prop ;
	//位置
	private String location ;
	public BackGroundWriteMetaThread(Properties prop, String location) {
		this.prop = prop;
		this.location = location;
		//守护线程
		this.setDaemon(true);
	}
	public void run() {
		try {
			File f = new File(location + ".meta") ;
			while(prop != null && f.exists()){
				FileOutputStream fos = new FileOutputStream(f);
				prop.store(fos, "");
				fos.close();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
