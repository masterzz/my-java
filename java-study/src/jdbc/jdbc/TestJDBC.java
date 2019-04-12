package jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class TestJDBC {
	public static void main(String[] args) {
		try {
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
			//创建语句对象
			Statement st = conn.createStatement();
			
			//执行sql语句
			st.execute("delete from users where id > 2");
			
			//释放资源
			st.close();
			System.out.println("删除over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}