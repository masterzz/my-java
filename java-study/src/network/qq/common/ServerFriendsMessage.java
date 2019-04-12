package network.qq.common;

import java.util.List;

/**
 * 服务器端的好友列表消息
 */
public class ServerFriendsMessage extends Message<List<String>> {

	public ServerFriendsMessage(List<String> message) {
		this.setMessageContent(message);
	}
}
