package prd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductDao {
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
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
	
	public int insertData(ProductBean pb) {
		int cnt = -1;
		String sql = "insert into products values(seqprd.nextval,?,?,sysdate)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setInt(2, pb.getPrice());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public ArrayList<ProductBean> getAllProduct(){
		ArrayList<ProductBean> lists = new ArrayList<ProductBean>();
		String sql = "select * from products order by id asc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductBean pb = new ProductBean();
				pb.setId(rs.getInt("id"));
				pb.setName(rs.getString("name"));
				pb.setPrice(rs.getInt("price"));
				pb.setRegdate(rs.getString("regdate"));
				
				lists.add(pb);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	
	public int deleteData(int id) {
		int cnt = -1;
		String sql = "delete from products where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ProductBean getOneProduct(int id) {
		ProductBean pb = null;
		String sql = "select * from products where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				pb = new ProductBean();
				pb.setName(rs.getString("name"));
				pb.setPrice(rs.getInt("price"));
				pb.setRegdate(rs.getString("regdate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return pb;
	}
	
	public int updateData(ProductBean pb) {
		int cnt = -1;
		String sql = "update products set name=?,price=? where id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setInt(2, pb.getPrice());
			ps.setInt(3, pb.getId());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
