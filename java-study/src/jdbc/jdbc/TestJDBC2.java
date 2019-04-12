package jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
public class TestJDBC2 {

	/**
	 * 执行Test标准的方法之前先调用
	 */
	@Before
	private static void loadDriverClass(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得连接
	 */
	private static Connection getConn(){
		try {
			//加载类(加载驱动程序)
			
			//数据库连接url
			String url = "jdbc:mysql://localhost:3306/big3" ;
			//username
			String user = "root";
			//password
			String pass = "root" ;
			
			//得到连接
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 插入
	 */
	@Test
	public static void insertData(){
		//
		Connection conn = null ;
		
		//语句对象
		Statement st = null ;
		try {
			//得到连接
			conn = getConn();
			//创建语句对象
			st = conn.createStatement();
			st.execute("insert into users(name,age) values('tomasLee',14)");
			st.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st != null && !st.isClosed()){
					st.close();
				}
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public static void insertTx(){
		Connection conn = null ;
		//语句对象
		Statement st = null ;
		try {
			//得到连接
			conn = getConn();
			conn.setAutoCommit(false);
			//创建语句对象
			st = conn.createStatement();
			
			st.execute("insert into users(name,age) values('jerry',15)");
			//st.execute("insert into users(id,name,age) values('www','john',16)");
			
			//手动提交事务
			//conn.commit();
			st.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(st != null && !st.isClosed()){
					st.close();
				}
				if(conn != null && !conn.isClosed()){
					
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		loadDriverClass();
		//insertData();
		insertTx();
	}
	
}