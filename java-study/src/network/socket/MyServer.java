package network.socket;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 服务器类
 */
public class MyServer {
	@Test
	//启动服务器
	public void start(){
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true){
				Socket s = ss.accept();					//接受客户端请求
				new CommunicationThread(s).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
