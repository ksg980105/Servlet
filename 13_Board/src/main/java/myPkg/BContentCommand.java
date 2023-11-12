package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BContentCommand implements BoardCommand{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardBean bb = dao.getArticleByNum(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		request.setAttribute("bb", bb);
	}

}
