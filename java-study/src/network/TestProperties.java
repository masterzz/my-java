package network;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
	
	@Test
	public void test() throws Exception{
		InputStream is = ClassLoader.getSystemResourceAsStream("xxx.properties");
		Properties prop = new Properties();
		prop.load(is);
		System.out.println(prop.getProperty("name"));
		
	}
}
