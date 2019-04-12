package jdbc.jdbc;

public class TestC3p0DataSource {

	public static void main(String[] args) {
		try {
//			ComboPooledDataSource ds = new ComboPooledDataSource();
//			ds.setDriverClass("com.mysql.jdbc.Driver");
//			ds.setJdbcUrl("jdbc:mysql://localhost:3306/big3");
//			ds.setUser("root");
//			ds.setPassword("root");
//
//			ds.setMaxPoolSize(10);		//最大
//			ds.setMinPoolSize(2);		//最小
//			ds.setInitialPoolSize(3);	//初始化连接数
//			ds.setAcquireIncrement(2);	//增量
//
//			Connection conn = ds.getConnection();
//			PreparedStatement ppst = conn.prepareStatement("insert into customers(name) values('v1')");
//			ppst.executeUpdate();
//			ppst.close();
//			conn.close();
//
//			conn = ds.getConnection();
//			ppst = conn.prepareStatement("insert into customers(name) values('v2')");
//			ppst.executeUpdate();
//			ppst.close();
//			conn.close();
//
//			conn = ds.getConnection();
//			ppst = conn.prepareStatement("insert into customers(name) values('v3')");
//			ppst.executeUpdate();
//			ppst.close();
//			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
