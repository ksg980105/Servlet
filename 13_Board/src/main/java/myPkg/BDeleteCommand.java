package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BDeleteCommand implements BoardCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		boolean check = dao.searchPw(num, request.getParameter("passwd"));

		int cnt = 0;

		if (check) {
			cnt = dao.deleteArticle(num);
		}

		if (cnt > 0) {
			int count = dao.getArticleCount();
			int pageSize = 10;
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			if (pageCount < pageNum) {
				request.setAttribute("pageNum", pageNum - 1);
			} else {
				request.setAttribute("pageNum", pageNum);
			}
		}
	}

}
