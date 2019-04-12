package network.qq.common;

import network.qq.util.Util;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 消息工厂
 */
public class MessageFactory {
	
	/**
	 * 组装客户端发送的文本消息
	 */
	public static byte[] popClientTextMessage(String msg){
		return popMessage(Message.CLIENT_TO_SERVER_TEXT_MESSAGE, msg.getBytes());
	}
	
	/**
	 * 组装客户端发送的刷新好友列表消息
	 */
	public static byte[] popClientRefreshFriends(){
		return Util.int2Bytes(Message.CLIENT_TO_SERVER_REFRESH_FRIENDS);
	}
	
	/**
	 * 组装服务器端好友列表消息
	 */
	public static byte[] popServerFriends(List<String> list){
		byte[] objArr = Util.serialize((Serializable)list);
		return popMessage(Message.SERVER_TO_CLIENT_FRIEND_LIST, objArr);
	}
	
	/**
	 * 组装服务器端转发的消息-ChatMessage
	 * --------------------
	 * |   |   |    |     |   4字节 -->消息类型
	 * --------------------
	 * |   |   |    |     |   4字节 -->userInfo长度
	 * --------------------
	 * |  ...........     |   n字节 -->userInfo内容
	 * --------------------
	 * |   |   |    |     |   4字节 -->消息长度
	 * --------------------
	 * |  ............    |   n字节 -->消息内容
	 * --------------------
	 */
	public static byte[] popServerChatMessage(String userInfo,byte[] clientChatMsg){
		//用户信息数组
		byte[] userInfoArr = userInfo.getBytes();
		
		//总消息长度
		byte[] allArr = new byte[4 + 4 + userInfoArr.length + 4 + clientChatMsg.length];
		
		//消息类型
		System.arraycopy(Util.int2Bytes(Message.SERVER_TO_CLIENT_CHAT_MESSAGE), 0, allArr, 0, 4);
		
		//用户信息长度
		System.arraycopy(Util.int2Bytes(userInfoArr.length), 0, allArr, 4, 4);
		
		//用户信息内容
		System.arraycopy(userInfoArr,0,allArr,8,userInfoArr.length);
		
		//聊天内容长度
		System.arraycopy(Util.int2Bytes(clientChatMsg.length) , 0 , allArr , (4 + 4 + userInfoArr.length) , 4);
		
		//聊天内容
		System.arraycopy(clientChatMsg , 0 , allArr , 4 + 4 + userInfoArr.length + 4 , clientChatMsg.length);
		
		return allArr ;
	}
	
	/**
	 * 组装一般性消息
	 */
	private static byte[] popMessage(int msgType , byte[] msgArr){
		//消息长度
		byte[] msglenArr = Util.int2Bytes(msgArr.length);
		//消息类性
		byte[] typeArr = Util.int2Bytes(msgType);
		//总消息
		byte[] allArr = new byte[typeArr.length + msglenArr.length + msgArr.length];
		
		//复制消息类型
		System.arraycopy(typeArr, 0, allArr, 0, typeArr.length);
		//复制消息长度
		System.arraycopy(msglenArr, 0, allArr, typeArr.length, msglenArr.length);
		//复制消息内容
		System.arraycopy(msgArr, 0, allArr, (typeArr.length + msglenArr.length), msgArr.length);
		return allArr ;
	}
	
	/**
	 * 从输入流中解析消息
	 */
	public static Message parseMessageFromInputStream(InputStream is){
		try {
			//四个字节的缓冲区
			byte[] buf4 = new byte[4];
			byte[] typeArr = new byte[4];
			is.read(typeArr);
			//类型
			int type = Util.bytes2Int(typeArr);
			//判断消息类型
			switch(type){
				//请求刷新好友
				case Message.CLIENT_TO_SERVER_REFRESH_FRIENDS:
					return new ClientRequestFreshFriendsMessage();
					
				//客户端发送聊天消息
				case Message.CLIENT_TO_SERVER_TEXT_MESSAGE:
					//读取消息长度
					is.read(buf4);
					int msgLen = Util.bytes2Int(buf4);
					//消息内容
					byte[] msgBuf = new byte[msgLen];
					is.read(msgBuf);
					//客户端发送的聊天消息(纯消息内容)
					return new ClientSendChatMessage(msgBuf);
				
				//服务器发送的客户端聊天信息
				case Message.SERVER_TO_CLIENT_CHAT_MESSAGE:
					//读取userInfoLen
					is.read(buf4);
					//读取userInfo
					int userInfoLen = Util.bytes2Int(buf4);
					byte[] bufUserInfo = new byte[userInfoLen];
					is.read(bufUserInfo);
					String strUserInfo = new String(bufUserInfo);
					
					//读取chat的长度
					is.read(buf4);
					int chatLen = Util.bytes2Int(buf4);
					
					//读取chat内容
					byte[] chatBuf = new byte[chatLen];
					is.read(chatBuf);
					String strChat = new String(chatBuf);
					//
					return new ServerChatMessage(new String[]{strUserInfo,strChat});
				
				//服务器发送给客户端的好友列表
				case Message.SERVER_TO_CLIENT_FRIEND_LIST:
					//读取列表缓冲区长度
					is.read(buf4);
					int listLen = Util.bytes2Int(buf4);
					
					//读取好友列表内容
					byte[] friendBuf = new byte[listLen];
					is.read(friendBuf);
					
					//好友列表
					List<String> friendList = (List<String>) Util.deSerialize(friendBuf);
					return new ServerFriendsMessage(friendList);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null ;
	}
	
}
