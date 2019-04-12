package network.qq.client;

import network.qq.common.Message;
import network.qq.common.MessageFactory;
import network.qq.common.ServerChatMessage;
import network.qq.common.ServerFriendsMessage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;


/**
 * 接受服务器消息
 */
public class ServerMessageReceiver extends Thread {
	private Socket sock ;
	private InputStream in ;
	private OutputStream out ;
	private QQClientUI ui ;
	
	public ServerMessageReceiver(Socket sock,QQClientUI ui){
		try {
			this.sock = sock ;
			this.in= sock.getInputStream();
			this.out = sock.getOutputStream() ;
			this.ui = ui ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void run() {
		try {
			//循环接受服务器发送来的消息
			while(true){
				Message msg = MessageFactory.parseMessageFromInputStream(in);
				//好友列表
				if(msg.getClass() == ServerFriendsMessage.class){
					List<String> list = ((ServerFriendsMessage)msg).getMessageContent();
					//刷新好友列表
					ui.refreshFriendList(list);
				}
				//好友聊天消息
				else if(msg.getClass() == ServerChatMessage.class){
					String[] ss = ((ServerChatMessage)msg).getMessageContent();
					//更新聊天历史区
					ui.updateHistory(ss);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
