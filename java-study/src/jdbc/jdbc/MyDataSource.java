package jdbc.jdbc;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * 数据源类
 */
public class MyDataSource implements DataSource {

	// 连接池
	private ConnectionPool pool;

	public MyDataSource() {
		String driver = "com.mysql.jdbc.Driver" ;
		String url = "jdbc:mysql://localhost:3306/big3" ;
		String user = "root" ;
		String pass = "root" ;
		pool = new ConnectionPool(driver,url,user,pass);
		// 初始化连接池
		pool.init();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
	}

	public void setLoginTimeout(int seconds) throws SQLException {

	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public Connection getConnection() throws SQLException {
		return pool.get();
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

}
