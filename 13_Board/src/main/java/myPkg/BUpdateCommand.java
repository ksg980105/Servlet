package myPkg;

import java.io.IOException;
import java.io.PrintWriter;

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
		    
	    int cnt = dao.updateArticle(bb);
	    
	    if(cnt != 1) {
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = null;
	    	try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	out.print("<script>alert('비번 일치안함');history.go(-1);</script>");
	    	out.flush();
	    	request.setAttribute("match", false);
	    	
	    }else {
	    	request.setAttribute("match", true);
	    }
	    
	    request.setAttribute("bb", bb);
	    request.setAttribute("pageNum", request.getParameter("pageNum"));
	}

}
