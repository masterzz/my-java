package jdbc.jdbc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

import org.junit.Test;

/**
 * 测试事务
 */
public class TestLOB {
	
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
	 * 测试大对象的插入,使用流的方式.
	 * alter table users add column pic longblob ;
	 * alter table users add column info text ;
	 */
	@Test
	public void testSavePic(){
		Connection conn = null ;
		PreparedStatement ppst = null ;
		try {
			conn = getConn();			//连接
			conn.setAutoCommit(false);	//自动提交
			String sql = "insert into users(name,pic,info) values(?,?,?)" ;
			ppst = conn.prepareStatement(sql);
			ppst.setString(1,"tom");
			
			//设置大对象
			File file = new File("d:/Koala.jpg");
			FileInputStream fis = new FileInputStream(file);
			ppst.setBinaryStream(2, fis, file.length());			//设置二进制流,指定长度.
			ppst.setString(3, "xxxxxx");
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
	 * 测试大对象的插入,使用流的方式.
	 * alter table users add column pic longblob ;
	 * alter table users add column info text ;
	 */
	@Test
	public void testReadPic(){
		Connection conn = null ;
		PreparedStatement ppst = null ;
		try {
			conn = getConn();			//连接
			conn.setAutoCommit(false);	//自动提交
			String sql = "select pic from users where id = ?" ;
			ppst = conn.prepareStatement(sql);
			ppst.setInt(1,1);
			ResultSet rs = ppst.executeQuery();
			if(rs.next()){
				byte[] bytes = rs.getBytes(1);
				FileOutputStream fos = new FileOutputStream("d:/KKK.jpg");
				fos.write(bytes);
				fos.close();
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