package jdbc.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPSocket 发送者
 */
public class UdpSender {
	public static void main(String[] args) {
		try {
			//创建用户数据报套接字
			DatagramSocket socket = new DatagramSocket(8888);

			//数据
			byte[] buf = "hello world".getBytes();
			
			//数据报包
			InetAddress addr = InetAddress.getByName("192.168.11.25");
			DatagramPacket pack = new DatagramPacket(buf, buf.length, addr, 9999);
			
			//发送数据报包
			socket.send(pack);
			System.out.println("send over");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
