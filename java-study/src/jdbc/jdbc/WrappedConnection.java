package jdbc.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * WrappedConnection连接包装类
 */
public class WrappedConnection extends ConnectionAdapter {

	private Connection rawConn ;
	
	private ConnectionPool pool ;
	
	public WrappedConnection(Connection rawConn, ConnectionPool pool) {
		this.rawConn = rawConn;
		this.pool = pool;
	}

	public void commit() throws SQLException {
		rawConn.commit();
	}

	public void rollback() throws SQLException {
		rawConn.rollback();
	}

	/**
	 * 关闭连接,
	 */
	public void close() throws SQLException {
		pool.add(this);
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return rawConn.prepareStatement(sql);
	}
}
