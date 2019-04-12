package jdbc.jdbc;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

/**
 * 测试存储过程
 */
public class TestCallableStatement {
	
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
	public void testAdd(){
		Connection conn = null ;
		CallableStatement cst = null ;
		try {
			conn = getConn();			//连接
			cst  = conn.prepareCall("{call up_add(?,?,?)}");
			cst.setInt(1, 1);
			cst.setInt(2, 3);
			cst.registerOutParameter(3, Types.INTEGER);
			//执行存储过程
			cst.execute();
			int r = cst.getInt(3);
			System.out.println(r);
			
			cst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试存储过程的大数据量插入
	 */
	@Test
	public void testBigInsert(){
		Connection conn = null ;
		CallableStatement cst = null ;
		try {
			conn = getConn();			//连接
			long start = System.currentTimeMillis() ;
			cst  = conn.prepareCall("{call up_biginsert(?)}");
			cst.setInt(1, 1000000);
			//执行存储过程
			cst.execute();
			System.out.println(System.currentTimeMillis() - start);
			cst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试创建删除存储过程
	 */
	@Test
	public void testDropProc(){
		Connection conn = null ;
		try {
			conn = getConn();			//连接
			long start = System.currentTimeMillis() ;
			conn.prepareStatement("drop procedure up_biginsert").executeUpdate();
			System.out.println(System.currentTimeMillis() - start);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试调用函数
	 */
	@Test
	public void testCallFunc(){
		Connection conn = null ;
		CallableStatement cst ;
		try {
			conn = getConn();			//连接
			cst = conn.prepareCall("{? = call uf_add(?,?)}");
			cst.setInt(2, 1);
			cst.setInt(3, 2);
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			System.out.println(cst.getInt(1));
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}