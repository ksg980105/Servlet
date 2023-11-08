package svy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyDao {
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public SurveyDao(String driver, String url, String user, String password) {
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
	
	public int insertData(SurveyBean sb) {
		int cnt = -1;
		String sql = "insert into survey values(seqmy.nextval,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getCompany());
			ps.setString(3, sb.getEmail());
			ps.setString(4, sb.getSatisfaction());
			ps.setString(5, sb.getPart());
			ps.setString(6, sb.getHowto());
			ps.setInt(7, sb.getAgree());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public ArrayList<SurveyBean> getAllSurvey() {
		ArrayList<SurveyBean> lists = new ArrayList<SurveyBean>();
		String sql = "select * from survey order by no asc";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SurveyBean sb = new SurveyBean();
				sb.setNo(rs.getInt("no"));
				sb.setName(rs.getString("name"));
				sb.setCompany(rs.getString("company"));
				sb.setEmail(rs.getString("email"));
				sb.setSatisfaction(rs.getString("satisfaction"));
				sb.setPart(rs.getString("part"));
				sb.setHowto(rs.getString("howto"));
				sb.setAgree(rs.getInt("agree"));
				
				lists.add(sb);
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
	
	public int deleteData(int no) {
		int cnt = -1;
		
		String sql = "delete from survey where no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public SurveyBean getSurveyByNo(int no) {
		SurveyBean sb = null;
		
		String sql = "select * from survey where no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				sb = new SurveyBean();
				sb.setNo(no);
				sb.setName(rs.getString("name"));
				sb.setCompany(rs.getString("company"));
				sb.setEmail(rs.getString("email"));
				sb.setSatisfaction(rs.getString("satisfaction"));
				sb.setPart(rs.getString("part"));
				sb.setHowto(rs.getString("howto"));
				sb.setAgree(rs.getInt("agree"));
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}
	
	public int updateData(SurveyBean sb) {
		int cnt = -1;
		String sql = "update survey set name=?,company=?,email=?,satisfaction=?,part=?,howto=?,agree=? where no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getCompany());
			ps.setString(3, sb.getEmail());
			ps.setString(4, sb.getSatisfaction());
			ps.setString(5, sb.getPart());
			ps.setString(6, sb.getHowto());
			ps.setInt(7, sb.getAgree());
			ps.setInt(8, sb.getNo());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
