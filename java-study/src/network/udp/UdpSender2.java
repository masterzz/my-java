package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPSocket 发送者
 */
public class UdpSender2 {
	public static void main(String[] args) {
		try {
			//创建用户数据报套接字
			DatagramSocket socket = new DatagramSocket(8888);

			//数据
			byte[] buf = "hello world".getBytes();
			
			//数据报包
			//255:设置广播地址
			InetAddress addr = InetAddress.getByName("192.168.11.255");
			DatagramPacket pack = new DatagramPacket(buf, buf.length, addr, 7777);
			int i = 1; 
			while(true){
				//设置发送的数据
				pack.setData(("tom" + i).getBytes());
				//发送数据报包
				socket.send(pack);
				System.out.println("sended : " + ("tom" + i));
				i ++ ;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
