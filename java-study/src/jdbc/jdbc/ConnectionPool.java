package jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接池
 */
public class ConnectionPool {
	
	private int max = 2 ;
	
	//Connection集合
	private List<Connection> connections = new ArrayList<Connection>();

	private String driver;

	private String url;

	private String user;

	private String pass;
	
	public ConnectionPool(String driver,String url,String user,String pass){
		try {
			this.driver = driver ;
			this.url = url ;
			this.user = user ;
			this.pass = pass ;
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化
	 */
	public void init() {
		try {
			for(int i = 0 ; i < max ; i ++){
				//原声连接
				Connection conn = DriverManager.getConnection(url, user, pass);
				connections.add(new WrappedConnection(conn, this));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回收连接
	 */
	public synchronized void add(WrappedConnection c) {
		connections.add(c);
	}

	/**
	 * 剪切第一个元素
	 */
	public synchronized Connection get() {
		if(connections.isEmpty()){
			return null ;
		}
		return connections.remove(0);
	}
}
