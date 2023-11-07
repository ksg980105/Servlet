package prd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
//@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String user;
	private String password;
	ProductDao dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		driver = config.getInitParameter("driver");
		url = config.getInitParameter("url");
		user = config.getInitParameter("user");
		password = config.getInitParameter("password");

		dao = new ProductDao(driver,url,user,password);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		int len = conPath.length();
		String command = uri.substring(len);
		String viewPage = null;

		if(command.equals("/insert.prd")) {
			ProductBean pb = new ProductBean();
			pb.setName(request.getParameter("name"));
			pb.setPrice(Integer.parseInt(request.getParameter("price")));
			
			int cnt = dao.insertData(pb);
			System.out.println("cnt:" + cnt);
			viewPage = "/select.prd";
			
		}else if(command.equals("/select.prd")) {
			viewPage = "prodList.jsp";
			ArrayList<ProductBean> lists = dao.getAllProduct();
			request.setAttribute("lists", lists);
			
		}else if(command.equals("/delete.prd")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int cnt = dao.deleteData(id);
			viewPage = "/select.prd";
		
		}else if(command.equals("/updateForm.prd")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ProductBean pb = dao.getOneProduct(id);
			pb.setId(id);
			request.setAttribute("pb",pb);
			viewPage = "updateForm.jsp";
		
		}else if(command.equals("/update.prd")) {
			ProductBean pb = new ProductBean();
			pb.setId(Integer.parseInt(request.getParameter("id")));
			pb.setName(request.getParameter("name"));
			pb.setPrice(Integer.parseInt(request.getParameter("price")));
			int cnt = dao.updateData(pb);
			System.out.println(cnt);
			viewPage = "/select.prd";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}

