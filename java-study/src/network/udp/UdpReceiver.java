package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDPSocket 发送者
 */
public class UdpReceiver {
	public static void main(String[] args) {
		try {
			//创建用户数据报套接字
			DatagramSocket socket = new DatagramSocket(9999);
			
			byte[] buf = new byte[1024 * 1024];
			//创建数据报包
			DatagramPacket pack = new DatagramPacket(buf, buf.length);
			//把数据收到pack的buf中.
			socket.receive(pack);
			//获取收到数据的长度.
			int len = pack.getLength();
			
			String str = new String(buf , 0 ,len);
			System.out.println("received data : " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
