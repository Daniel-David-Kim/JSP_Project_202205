package section01;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.stream.*;

@WebServlet(urlPatterns= {"/comment/*"})
public class CommentController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("CommentController : init method activated.");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CommentController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CommentController : doPost method activated.");
		doHandle(req, res);
	}
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CommentController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();
		ServletContext app = req.getServletContext();
		CommentService service = new CommentService();
		PrintWriter out = res.getWriter();
		
		String pathInfo = req.getPathInfo();
		
		String goTo = "";
		
		int bcat = (int)app.getAttribute("bcat");
		int scat = (int)app.getAttribute("scat");
		String categoryName = (String)app.getAttribute("categoryName");
		if(pathInfo.equals("/enroll")) {
			System.out.println("enroll");
			String m_id = (String)session.getAttribute("uid");
			String content = req.getParameter("content");
			int resRow = service.insertComment(categoryName, m_id, content, scat);
			if(resRow == 1) app.setAttribute("commentInsertResult", "success");
			else app.setAttribute("commentInsertResult", "failed");
			
			goTo = req.getContextPath() + "/lecture/" + bcat + "/" + scat;
			res.sendRedirect(goTo);
			return;
		} else if(pathInfo.equals("/revise")) {
			String revise = req.getParameter("revise");
			int c_num = Integer.parseInt(req.getParameter("c_num"));
			int resRow = service.reviseComment(categoryName, c_num, revise);
			if(resRow == 1) app.setAttribute("commentResult", "success");
			else app.setAttribute("commentResult", "failed");
			
			goTo = req.getContextPath() + "/lecture/" + bcat + "/" + scat;
			res.sendRedirect(goTo);
			return;
		} else if(pathInfo.equals("/delete")) {
			String c_numStr = req.getParameter("c_num");
			int c_num = Integer.parseInt(c_numStr);
			int resRow = service.deleteComment(categoryName, c_num);
			if(resRow == 1) app.setAttribute("commentResult", "success");
			else app.setAttribute("commentResult", "failed");
			
			goTo = req.getContextPath() + "/lecture/" + bcat + "/" + scat;
			res.sendRedirect(goTo);
			return;
		}
		
		app.removeAttribute("bcat");
		app.removeAttribute("scat");
		app.removeAttribute("categoryName");
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	@Override
	public void destroy() {
		System.out.println("CommentController : destroy method activated.");
	}
}
