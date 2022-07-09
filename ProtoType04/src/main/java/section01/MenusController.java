package section01;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.stream.*;
import javax.sql.rowset.serial.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns= {"/menusControl/*"})
public class MenusController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("MenusController : init method activated.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MenusController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MenusController : doPost method activated.");
		doHandle(req, res);
	}
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MenusController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		ServletContext app = req.getServletContext();
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		MenusService service = new MenusService();
		
		String pathInfo = req.getPathInfo();
		System.out.println("pathInfo : " + pathInfo);
		String queryString = req.getQueryString();
		System.out.println("queryString : " + queryString);
		
		String[] divs = pathInfo.split("/");
		for(int i = 0; i < divs.length; i++) System.out.println("idx " + i + " -> " + divs[i]);
		String goTo = "";
		
		if(divs[1].equals("addMenu")) {
			String author = (String)session.getAttribute("uid");
			String targetBigcat = divs[2]; 
			String subject = URLDecoder.decode(queryString.split("=")[1], "utf-8");
			System.out.println("bigcat : " + targetBigcat);
			System.out.println("subject : " + subject);
			int resRow = service.insertMenu(subject, targetBigcat, author);
			if(resRow == 1) app.setAttribute("menuResult", "success");
			else app.setAttribute("menuResult", "failed");
			
			goTo = req.getContextPath() + "/center/intro";
			res.sendRedirect(goTo);
			return;
		} else if(divs[1].equals("delMenu")) {
			String subject = URLDecoder.decode(queryString.split("=")[1], "utf-8");
			System.out.println("subject : " + subject);
			int resRow = service.deleteMenu(subject);
			if(resRow == 1) app.setAttribute("menuResult", "success");
			else app.setAttribute("menuResult", "failed");
			
			goTo = req.getContextPath() + "/center/intro";
			res.sendRedirect(goTo);
			return;
		}
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	@Override
	public void destroy() {
		System.out.println("MenusController : destroy method activated.");
	}
}
