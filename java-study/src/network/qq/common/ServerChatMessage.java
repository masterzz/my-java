package network.qq.common;

/**
 * 服务器端的聊天记录的消息
 */
public class ServerChatMessage extends Message<String[]> {
	public ServerChatMessage(String[] msg) {
		this.setMessageContent(msg);
	}
}
