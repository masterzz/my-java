package network.qq.server;

public class ServerMain {
	public static void main(String[] args) {
		QQServer server = new QQServer();
		System.out.println("服务器启动了.....");
		server.startServer();
	}
}
