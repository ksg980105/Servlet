package myPkg;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BReplyCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
	    String pageNum = request.getParameter("pageNum");
	    
	    BoardBean bb = new BoardBean();
		bb.setWriter(request.getParameter("writer"));
		bb.setEmail(request.getParameter("email"));
		bb.setSubject(request.getParameter("subject"));
		bb.setPasswd(request.getParameter("passwd"));
		bb.setReg_date(new Timestamp(System.currentTimeMillis()));
		bb.setContent(request.getParameter("content"));
		
		bb.setIp(request.getRemoteAddr());
		
		bb.setRef(Integer.parseInt(request.getParameter("ref")));
		bb.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bb.setRe_level(Integer.parseInt(request.getParameter("ref")));
	    
	    dao.replyArticle(bb);
	    
	    request.setAttribute("pageNum", pageNum);
	}

}
