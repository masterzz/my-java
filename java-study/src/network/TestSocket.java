package network;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * 端口0 - 65535
 *   0 - 1023
 */
public class TestSocket {
	/**
	 * 创建ServerSocket
	 */
	@Test
	public void testServerSocket() throws Exception{
		//创建服务器socket
		byte[] addr1 = new byte[]{(byte)192,(byte)168,(byte)231,1};
		byte[] addr2 = new byte[]{(byte)192,(byte)168,(byte)5,1};
		byte[] addr3 = new byte[]{(byte)192,(byte)168,(byte)11,34};
		byte[] all = new byte[]{0,0,0,0};
		
		//创建ServerSocket对象
		ServerSocket ss = new ServerSocket();
		InetSocketAddress adr1 = new InetSocketAddress(InetAddress.getByAddress(all), 8888);
		ss.bind(adr1);
		//接受请求SocketAddress endpoint) throws IOException


		while(true){
			Socket s = ss.accept();
			//本地地址
			InetSocketAddress localAddr = (InetSocketAddress)s.getLocalSocketAddress();
			System.out.println("local = " + localAddr.getHostName() + " : " + localAddr.getPort());
			//远程地址
			InetSocketAddress remoteAddr = (InetSocketAddress)s.getRemoteSocketAddress();
			System.out.println("remote = " + remoteAddr.getHostName() + " : " + remoteAddr.getPort());
			InputStream is = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0 ;
			while((len = is.read(buf)) != -1){
				System.out.println(new String(buf,0,len));
			}
		}
	}
	
	/**
	 * 测试客户端Socket
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	@Test
	public void testClient() throws Exception{
		Socket s = new Socket("192.168.231.1", 8888);
		//输出流
		OutputStream os = s.getOutputStream();
		os.write("hello".getBytes());
		os.flush();
		System.out.println("连上了!");
		while(true){
			Thread.sleep(50000);
		}
	}
	
	/**
	 * 服务器Socket
	 */
	@Test
	public void testServerSocket2(){
		try {
			//
			ServerSocket ss = new ServerSocket(8888);
			while(true){
				Socket s = ss.accept();
				InetSocketAddress r = (InetSocketAddress) s.getRemoteSocketAddress();
				int port = r.getPort();
				String host = r.getHostString();
				System.out.println(host + ":" + port);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
