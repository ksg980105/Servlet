package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDao;

public interface BoardCommand {
	BoardDao dao = BoardDao.getInstance();
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
