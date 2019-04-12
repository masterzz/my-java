package jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.Test;

/**
 * 测试预处理语句
 */
public class TestPreparedStatement {
	
	/**
	 * 获得连接
	 */
	private Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/big3" ;
			String user = "root";
			String pass = "root" ;
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 测试保存
	 */
	@Test
	public void testSave(){
		Connection conn = null ;
		PreparedStatement ppst = null ;
		try {
			conn = getConn();			//连接
			conn.setAutoCommit(false);	//自动提交
			//insert into users(name,age) values(?,?)					?是占位符
			ppst = conn.prepareStatement("insert into users(name,age) values(?,?)");
			ppst.setString(1, "tom");
			ppst.setInt(2, 20);
			ppst.executeUpdate();
			conn.commit();
			
			ppst.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试批量保存
	 * 1,000,000 : 65800ms(age不取摸)
	 * 1,000,000 : 225,223ms(age取摸)
	 * 1,000,000 : 218,313(age取摸)
	 * 1,000,000 : 219,429(batch(20))
	 */
	@Test
	public void testBatchSave(){
		int max = 1000000 ;
		Connection conn = null ;
		PreparedStatement ppst = null ;
		try {
			conn = getConn();						//连接
			conn.setAutoCommit(false);				//自动提交
			long start = System.currentTimeMillis() ;
			ppst = conn.prepareStatement("insert into users(name,age) values(?,?)");
			int count = 0 ;
			for(int i = 0 ; i < max ; i ++){
				ppst.setString(1, "tom" + i);
				ppst.setInt(2, 20);
				//添加命令到批次中
				ppst.addBatch();
				//ppst.executeUpdate();
				count ++ ;
				if(count == 100){
					//执行批量命令
					ppst.executeBatch();
					//清除批次
					ppst.clearBatch();
					count = 0 ; 
				}
			}
			ppst.executeBatch();
			ppst.clearBatch();
			conn.commit();
			System.out.println(System.currentTimeMillis() - start);
			
			ppst.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试sql注入
	 * PreparedStatement
	 */
	@Test
	public void testSQLInjection(){
		Connection conn = null ;
		PreparedStatement ppst = null ;
		try {
			conn = getConn();			//连接
			conn.setAutoCommit(false);	//自动提交
			//insert into users(name,age) values(?,?)					?是占位符
			ppst = conn.prepareStatement("select * from users where name = ? and password = ?");
			
			//绑定参数
			ppst.setString(1, "1' or 1=1 -- ");
			ppst.setString(2, "123456");
			
			//执行查询
			ResultSet rs = ppst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			conn.commit();
			ppst.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}