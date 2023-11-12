package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	private static BoardDao instance = new BoardDao();

	private BoardDao(){
		Context initContext;
		try {
			initContext = new InitialContext();

			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			conn = ds.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static BoardDao getInstance() {
		return instance;
	}
	
	public ArrayList<BoardBean> getArticles(int start, int end) {
		ArrayList<BoardBean> lists = new ArrayList<BoardBean>();
		String sql = "select num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip " ;		        
		sql += "from (select rownum as rank, num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip ";
		sql += "from (select num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip ";
		sql += "from board  ";
		sql += "order by ref desc, re_step asc )) ";
		sql += "where rank between ? and ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setEmail(rs.getString("email"));
				bb.setSubject(rs.getString("subject"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));
				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
				
				lists.add(bb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	} // getArticles
	
	public int getArticleCount() {
		int count = 0;
		String sql = "select count(*) as cnt from board";
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	} // getArticles
	
	public int insertArticle(BoardBean bb) { // 원글쓰기
		int cnt = -1;
		String sql = "insert into board(num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip) values(board_seq.nextval,?,?,?,?,?,board_seq.currval,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bb.getWriter());
			ps.setString(2, bb.getEmail());
			ps.setString(3, bb.getSubject());
			ps.setString(4, bb.getPasswd());
			ps.setTimestamp(5, bb.getReg_date());
			
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setString(8, bb.getContent());
			ps.setString(9, bb.getIp());
			
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	} // insertArticle
	
	public BoardBean getArticleByNum(int num) {
		BoardBean bb = null;
		String sql = "update board set readcount = readcount + 1 where num = ?";
		String sql2 = "select * from board where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeUpdate(); // 조회수 증가
			
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, num);
			
			
			rs = ps.executeQuery();
			if(rs.next()) {
				bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setSubject(rs.getString("subject"));
				bb.setEmail(rs.getString("email"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));
				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bb;
	} // getArticleByNum
	
	public boolean searchPw(int num, String passwd) {
		boolean check = false;
		String sql = "select passwd from board where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(passwd.equals(rs.getString("passwd")))
					check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	} // searchPw
	
	public int deleteArticle(int num) {
		int cnt = -1;
		String sql = "delete board where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	} // deleteArticle
	
	public BoardBean getArticles(int num) {
		BoardBean bb = null;
		String sql = "select * from board where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			
			rs = ps.executeQuery();
			if(rs.next()) {
				bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setSubject(rs.getString("subject"));
				bb.setEmail(rs.getString("email"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));
				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bb;
	} // getArticles
	
	public int updateArticle(BoardBean bb) { // 원글수정
		int cnt = -1;
		String sql = "select passwd from board where num = ?";
		String sql2 = "update board set writer=?, email=?, subject=?, content=? where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bb.getNum());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				if(bb.getPasswd().equals(rs.getString("passwd"))) {
					ps = conn.prepareStatement(sql2);
					ps.setString(1, bb.getWriter());
					ps.setString(2, bb.getEmail());
					ps.setString(3, bb.getSubject());
					ps.setString(4, bb.getContent());
					ps.setInt(5, bb.getNum());
					
					cnt = ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	} // updateArticle
	
	public int replyArticle(BoardBean bb) {
		int cnt = -1;
		
		String sql = "update board set re_step = re_step + 1 where ref=? and re_step > ?";
		String sql2 = "insert into board(num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bb.getRef());
			ps.setInt(2, bb.getRe_step());
			
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql2);
			
			ps.setString(1, bb.getWriter());
			ps.setString(2, bb.getEmail());
			ps.setString(3, bb.getSubject());
			ps.setString(4, bb.getPasswd());
			ps.setTimestamp(5, bb.getReg_date());
			
			ps.setInt(6, bb.getRef());
			ps.setInt(7, bb.getRe_step()+1);
			ps.setInt(8, (bb.getRe_level()+1));
			ps.setString(9, bb.getContent());
			ps.setString(10, bb.getIp());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
