package svy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/SurveyController")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String user;
	private String password;
	SurveyDao dao;
	ServletContext sc;

    /**
     * Default constructor. 
     */
    public SurveyController() {
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

		dao = new SurveyDao(driver,url,user,password);
		sc = config.getServletContext();
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
		
		String[] part = request.getParameterValues("part");
		String str = "";
		if(part != null) {
			for(int i=0; i<part.length; i++) {
				if(i != part.length) {
					str += part[i]+" "; 
				}else {
					str += part[i];
				}
			}
		}else {
			str = "선택항목 없음";
		}
		
		if(command.equals("/insert.sv")) {
			boolean flag =(Boolean) sc.getAttribute("flag");
			System.out.println(flag);
			if(flag == false) {
				SurveyBean sb = new SurveyBean();
				sb.setName(request.getParameter("name"));
				sb.setCompany(request.getParameter("company"));
				sb.setEmail(request.getParameter("email"));
				sb.setSatisfaction(request.getParameter("satisfaction"));
				sb.setPart(str);
				sb.setHowto(request.getParameter("howto"));
				
				String agree = request.getParameter("agree");
				if (agree != null && !agree.isEmpty()) {
				    sb.setAgree(Integer.parseInt(agree));
				} else {
				    sb.setAgree(0);
				}
				sc.setAttribute("flag", true);
				
				dao.insertData(sb);
			
				viewPage = "/select.sv";
			}else {
				viewPage = "/select.sv";
			}
		}else if(command.equals("/select.sv")) {
			viewPage = "surveyResult.jsp";
			ArrayList<SurveyBean> lists = dao.getAllSurvey();
			request.setAttribute("lists", lists);
		
		}else if(command.equals("/delete.sv")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			dao.deleteData(no);
			viewPage = "/select.sv";
			
		}else if(command.equals("/updateForm.sv")) {
			int no = Integer.parseInt(request.getParameter("no"));
			SurveyBean sb = dao.getSurveyByNo(no);
			request.setAttribute("sb", sb);
			viewPage = "updateForm.jsp";
		
		}else if(command.equals("/update.sv")) {
			SurveyBean sb = new SurveyBean();
			sb.setNo(Integer.parseInt(request.getParameter("no")));
			sb.setName(request.getParameter("name"));
			sb.setCompany(request.getParameter("company"));
			sb.setEmail(request.getParameter("email"));
			sb.setSatisfaction(request.getParameter("satisfaction"));
			sb.setPart(request.getParameter("part"));
			sb.setHowto(request.getParameter("howto"));
			String agree = request.getParameter("agree");
			if (agree != null && !agree.isEmpty()) {
			    sb.setAgree(1);
			} else {
			    sb.setAgree(0);
			}
			
			dao.updateData(sb);
			viewPage="/select.sv";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
