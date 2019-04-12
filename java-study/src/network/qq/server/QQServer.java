package network.qq.server;

import network.qq.common.MessageFactory;
import network.qq.util.Util;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * 消息通信服务器
 */
public class QQServer {
	
	//好友列表
	public Map<String,Socket> allSockets = new HashMap<String,Socket>() ;
	
	public void startServer(){
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true){
				//接受客户端连接
				Socket s = ss.accept();
				allSockets.put(Util.getUserInfo(s),s);
				//开启分线程，单独处理每个client的请求
				new ClientMessageReceiver(s,this).start();
				//
				this.broadcastFriendList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到好友名称列表
	 */
	public List<String> getFriendList(){
		return new ArrayList<String>(allSockets.keySet());
	}
	
	/**
	 * 广播消息
	 */
	public void broadcastMessage(byte[] message){
		try {
			Collection<Socket> sockets = allSockets.values();
			for(Socket ss : sockets){
				OutputStream os = ss.getOutputStream();
				os.write(message);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 群发好友列表
	 */
	public void broadcastFriendList(){
		broadcastMessage(MessageFactory.popServerFriends(getFriendList()));
	}

	/**
	 * 删除好友
	 */
	public void removeFriend(String userInfo) {
		allSockets.remove(userInfo);
	}
}
