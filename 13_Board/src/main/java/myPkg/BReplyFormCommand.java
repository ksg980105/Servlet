package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyFormCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
	    request.setAttribute("ref", request.getParameter("ref"));
	    request.setAttribute("re_step", request.getParameter("re_step"));
	    request.setAttribute("re_level", request.getParameter("re_level"));
	    request.setAttribute("pageNum", request.getParameter("pageNum"));
	}

}
