package network.socket;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestURL {
	@Test
	public void test1() throws Exception{
		//
		FileOutputStream fos = new FileOutputStream("e:/ziling.mp3");
		
		//URL对象
		URL url = new URL("http://localhost:9090/ziling.mp3");
		//打开连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		InputStream is = conn.getInputStream();
		byte[] buf = new byte[1024];
		int len = -1 ;
		while((len = is.read(buf)) != -1){
			fos.write(buf, 0, len);
		}
		fos.close();
		is.close();
		System.out.println("下载完成");
	}
}
