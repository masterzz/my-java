package network.qq.client;

import java.io.OutputStream;
import java.net.Socket;

/**
 * 消息发送器
 */
public class MessageSender {
	
	private Socket sock ;
	
	private OutputStream out;
	
	public MessageSender(Socket sock){
		try {
			this.sock = sock ;
			this.out = sock.getOutputStream() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息
	 */
	public void sendMessage(byte[] msg){
		try {
			out.write(msg);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
