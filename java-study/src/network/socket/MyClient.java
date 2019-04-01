package network.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;
/**
 * 客户端 
 */
public class MyClient {
	@Test
	public void send(){
		try {
			Socket s = new Socket("localhost", 8888);
			//获得输出流
			OutputStream os = s.getOutputStream();
			//输入流
			InputStream is = s.getInputStream();
			//缓冲区字符流
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = null ;
			byte[] buf = new byte[1024];
			int len = -1 ;
			//读行
			while((line = reader.readLine()) != null){
				os.write(line.getBytes());
				len = is.read(buf);
				System.out.println(new String(buf,0,len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}