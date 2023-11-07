package prd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductDao {
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	
	public ProductDao(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
