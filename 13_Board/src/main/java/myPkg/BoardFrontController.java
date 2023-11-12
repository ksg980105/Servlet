package myPkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
// @WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	/**
	 * Default constructor.
	 */
	public BoardFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		sc = config.getServletContext();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		int len = conPath.length();
		String command = uri.substring(len);
		String viewPage = null;

		BoardCommand bCommand = null;

		if (command.equals("/insert.bd")) {
			if (!(Boolean) sc.getAttribute("flag")) {
				bCommand = new BInsertCommand();
				bCommand.execute(request, response);
			}
			viewPage = "/select.bd";
			
		} else if (command.equals("/select.bd")) {
			bCommand = new BSelectCommand();
			bCommand.execute(request, response);

			viewPage = "list.jsp";
			
		} else if (command.equals("/content.bd")) {
			bCommand = new BContentCommand();
			bCommand.execute(request, response);

			viewPage = "content.jsp";
			
		} else if (command.equals("/updateForm.bd")) {
			bCommand = new BUpdateFormCommand();
			bCommand.execute(request, response);

			viewPage = "updateForm.jsp";
			
		} else if (command.equals("/update.bd")) {
			bCommand = new BUpdateCommand();
			bCommand.execute(request, response);

			viewPage = "/select.bd";
			
		} else if (command.equals("/deleteForm.bd")) {
			bCommand = new MDeleteFormCommand();
			bCommand.execute(request, response);

			viewPage = "deleteForm.jsp";
			
		} else if (command.equals("/delete.bd")) {
			bCommand = new BDeleteCommand();
			bCommand.execute(request, response);

			viewPage = "/select.bd";
			
		} else if (command.equals("/replyForm.bd")) {
			bCommand = new BReplyFormCommand();
			bCommand.execute(request, response);

			viewPage = "replyForm.jsp";
			
		} else if (command.equals("/reply.bd")) {
			bCommand = new BReplyCommand();
			bCommand.execute(request, response);

			viewPage = "/select.bd";
		}

		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
