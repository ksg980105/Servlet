package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BUpdateFormCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardBean bb = dao.getArticles(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("bb", bb);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
	}

}
