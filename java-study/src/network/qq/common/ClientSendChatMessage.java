package network.qq.common;

/**
 * 客户端请求刷新好友列表
 */
public class ClientSendChatMessage extends Message<byte[]> {
	public ClientSendChatMessage(byte[] raw){
		this.setMessageContent(raw);
	}
}