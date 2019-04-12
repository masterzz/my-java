package jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.Test;

/**
 * 测试事务
 */
public class TestTransaction {
	
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
	 * 测试保存点
	 */
	@Test
	public void testSavePoint(){
		Connection conn = null ;
		Statement st = null ;
		try {
			conn = getConn();			//连接
			conn.setAutoCommit(false);	//自动提交
			st = conn.createStatement();
			st.execute("insert into users(name,age) values('jerry',15)");
			Savepoint s1 = conn.setSavepoint("1");
			st.execute("insert into users(name,age) values('jerry',16)");
			Savepoint s2 = conn.setSavepoint("2");
			st.execute("insert into users(name,age) values('jerry',17)");
			Savepoint s3 = conn.setSavepoint("3");
			st.execute("insert into users(name,age) values('jerry',18)");
			Savepoint s4 = conn.setSavepoint("4");
			conn.rollback(s2);
			conn.commit();
			st.close();
			conn.close();
			System.out.println("insert over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试update
	 */
	@Test
	public void testUpdate() throws Exception{
		Connection conn = getConn();			//连接
		conn.setAutoCommit(false);				//自动提交
		Statement st = conn.createStatement();
		st.executeUpdate("update users set name = 'tom' where id = 25");
		conn.commit();
		st.close();
		conn.close();
	}
	
	/**
	 * 测试查询
	 */
	@Test
	public void testSelect() throws Exception{
		Connection conn = getConn();							//连接
		conn.setAutoCommit(false);								//自动提交
		Statement st = conn.createStatement();					//创建语句对象
		ResultSet rs = st.executeQuery("select * from users");	//执行查询
		//
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Integer age = (Integer)rs.getObject("age");			//getInt()null只返回基本类型，getObject返回对象null
			System.out.println(id + "," + name + "," + age);
		}
		rs.close();
		conn.commit();
		st.close();
		conn.close();
	}
	

	/**
	 * 测试查询(sql注入)
	 */
	@Test
	public void testSelect2() throws Exception{
		Connection conn = getConn();							//连接
		conn.setAutoCommit(false);								//自动提交
		Statement st = conn.createStatement();					//创建语句对象
		String name = "1' or 1=1 -- " ;
		String pwd = "123" ;
		//								select * from users where name = '1' or 1=1 -- ' and password='123'
		ResultSet rs = st.executeQuery("select * from users where name = '" + name + "' and password='" + pwd + "'");	//执行查询
		//
		while(rs.next()){
			int id = rs.getInt("id");
			String name0 = rs.getString("name");
			Integer age = (Integer)rs.getObject("age");			//getInt()null只返回基本类型，getObject返回对象null
			System.out.println(id + "," + name0 + "," + age);
		}
		rs.close();
		conn.commit();
		st.close();
		conn.close();
	}
	
	
	
	/**
	 * 测试批量插入
	 * 1,000,000 : 188419ms
	 */
	@Test
	public void testBatchInsert() throws Exception{
		Connection conn = getConn();							//连接
		conn.setAutoCommit(false);								//自动提交
		Statement st = conn.createStatement();					//创建语句对象
		int max = 1000000 ;										
		long start = System.currentTimeMillis() ;				//开始时间
		for(int i = 0 ; i < max ; i ++){
			//				  insert into users(name,age) values('tom345',12);
			st.executeUpdate("insert into users(name,age) values('tom" + i + "'," + (i % 100) + ")");
		}
		conn.commit();
		System.out.println(System.currentTimeMillis() - start);	//
		st.close();
		conn.close();
	}
}