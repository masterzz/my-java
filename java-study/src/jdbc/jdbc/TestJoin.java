package jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

/**
 * 测试连接查询
 */
public class TestJoin {
	/**
	 * 内连接
	 */
	@Test
	public void testInnerJoin() throws Exception {
			//加载类(加载驱动程序)
			Class.forName("com.mysql.jdbc.Driver");
			//数据库连接url
			String url = "jdbc:mysql://localhost:3306/big3" ;
			//username
			String user = "root";
			//password
			String pass = "root" ;
			
			//得到连接
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			//执行sql语句
			String sql = "select a.id aid,a.name aname,b.orderno borderno,b.price bprice from customers a , orders b "
					+ "where a.id = b.cid" ;
			//创建语句对象
			PreparedStatement ppst = conn.prepareStatement(sql);
			
			ResultSet rs = ppst.executeQuery(sql);
			while(rs.next()){
				int aid = rs.getInt("aid");
				String aname = rs.getString("aname");
				String borderno= rs.getString("borderno");
				float bprice= rs.getFloat("bprice");
				System.out.println(aid + "," + aname + "," + borderno + "," + bprice);
			}
			//释放资源
			rs.close();
			ppst.close();
			conn.close();
	}
	/**
	 * 左外连接
	 */
	@Test
	public void testLeftOuterJoin() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/big3" ;
		String user = "root";
		String pass = "root" ;
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		String sql = "select a.id aid,a.name aname,b.orderno borderno,b.price bprice from customers a left outer join orders b "
				+ "on a.id = b.cid" ;
		PreparedStatement ppst = conn.prepareStatement(sql);
		ResultSet rs = ppst.executeQuery(sql);
		while(rs.next()){
			int aid = rs.getInt("aid");
			String aname = rs.getString("aname");
			String borderno= rs.getString("borderno");
			float bprice= rs.getFloat("bprice");
			System.out.println(aid + "," + aname + "," + borderno + "," + bprice);
		}
		rs.close();
		ppst.close();
		conn.close();
	}
	
	/**
	 * 测试隔离级别
	 */
	@Test
	public void testDirtyReadA() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/big3" ;
		String user = "root";
		String pass = "root" ;
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		//设置事务的隔离级别.
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		String sql = "select * from customers where id = 1" ;
		PreparedStatement ppst = conn.prepareStatement(sql);
		ResultSet rs = ppst.executeQuery(sql);
		while(rs.next()){
			int aid = rs.getInt("id");
			String name = rs.getString("name");
			System.out.println(aid + "," + name);
		}
		rs.close();
		ppst.close();
		conn.close();
	}
	
	/**
	 * 测试隔离级别
	 */
	@Test
	public void testDirtyReadB() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/big3" ;
		String user = "root";
		String pass = "root" ;
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		//关闭自动提交
		conn.setAutoCommit(false);
		String sql = "update customers set name = 'xxx' where id = 1" ;
		PreparedStatement ppst = conn.prepareStatement(sql);
		ppst.executeUpdate();
		System.out.println("-------------");
		conn.commit();
		ppst.close();
		conn.close();
	}
}