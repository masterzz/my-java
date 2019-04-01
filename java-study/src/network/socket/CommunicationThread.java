package network.socket;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 通信线程
 */
public class CommunicationThread extends Thread{
	//sock
	private Socket sock ;
	private String clientInfo ;
	
	public CommunicationThread(Socket sock) {
		this.sock = sock;
		clientInfo = getClientInfo();
	}
	public void run() {
		try {
			//接受数据
			InputStream is = sock.getInputStream();	 //获取输入流，读取客户端内容
			OutputStream os = sock.getOutputStream();//获取输出,回传给client
			
			InputStreamReader reader = new InputStreamReader(is);
			char[] buf = new char[1024];
			int len = 0 ;
			while((len = reader.read(buf)) != -1){
				String msg = new String(buf,0,len) ;
				System.out.println("[" + clientInfo + "] " + msg);
				os.write(("[from Teacher] " + msg).getBytes());
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得socket的远程client的信息(ip:port)
	 */
	private String getClientInfo(){
		try {
			InetSocketAddress addr = (InetSocketAddress) sock.getRemoteSocketAddress();
			String ip = addr.getAddress().getHostAddress();
			int port = addr.getPort();
			return ip + ":" + port ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
