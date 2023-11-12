package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BUpdateCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardBean bb = new BoardBean();
		bb.setNum(Integer.parseInt(request.getParameter("num")));
		bb.setWriter(request.getParameter("writer"));
		bb.setEmail(request.getParameter("email"));
		bb.setSubject(request.getParameter("subject"));
		bb.setPasswd(request.getParameter("passwd"));
		bb.setContent(request.getParameter("content"));
		    
	    dao.updateArticle(bb);
	    
	    request.setAttribute("bb", bb);
	    request.setAttribute("pageNum", request.getParameter("pageNum"));
	}

}
