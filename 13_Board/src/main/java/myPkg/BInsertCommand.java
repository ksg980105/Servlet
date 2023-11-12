package myPkg;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BInsertCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardBean bb = new BoardBean();
		bb.setWriter(request.getParameter("writer"));
		bb.setEmail(request.getParameter("email"));
		bb.setSubject(request.getParameter("subject"));
		bb.setPasswd(request.getParameter("passwd"));
		bb.setReg_date(new Timestamp(System.currentTimeMillis()));
		bb.setContent(request.getParameter("content"));
		bb.setIp(request.getRemoteAddr());
		    
		dao.insertArticle(bb);
		ServletContext sc = request.getServletContext();
		sc.setAttribute("flag", true);
	}	

}
