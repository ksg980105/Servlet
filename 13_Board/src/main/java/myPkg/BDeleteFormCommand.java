package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BDeleteFormCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		 request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		 request.setAttribute("pageNum", request.getParameter("pageNum"));
	}

}
