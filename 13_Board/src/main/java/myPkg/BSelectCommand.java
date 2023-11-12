package myPkg;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardBean;

public class BSelectCommand implements BoardCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pageSize = 10; // 한 페이지에 보이는 레코드 갯수

		String pageNum = request.getParameter("pageNum"); // 넘어오는 페이지 번호
		if (pageNum == null) {
			pageNum = "1"; // 넘어오는 페이지 번호가 없으면 페이지 번호는 1로 설정
		}
		int currentPage = Integer.parseInt(pageNum); // 문자열로 넘어오는 페이지 번호를 숫자 형식으로 변환
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		int count = dao.getArticleCount();
		int number = count - (currentPage - 1) * pageSize; // 페이지
		ArrayList<BoardBean> articleLists = dao.getArticles(startRow, endRow);

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 한 페이지에 10개가 들어갈 수 있음으로 count%pageSize로 나머지가 있을 때 페이지 하나를 더 만들어야 한다.
		int pageBlock = 3; // 한번에 보여지는 페이지 갯수
		int startPage = ((currentPage - 1) / pageBlock * pageBlock) + 1;
		int endPage = startPage + pageBlock - 1;

		request.setAttribute("articleLists", articleLists);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
	}
}
