package section01;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.stream.*;

// 대부분의 댓글은 강의 페이지에 있는데, 이 인트로 화면의 댓글은 구성과 접근 방식이 달라 새로 구성했습니다.
@WebServlet(urlPatterns= {"/centerComment/*"})
public class CenterCommentController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("CenterCommentController : init method activated.");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterCommentController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterCommentController : doPost method activated.");
		doHandle(req, res);
	}
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterCommentController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();
		ServletContext app = req.getServletContext();
		CommentService service = new CommentService();
		PrintWriter out = res.getWriter();
		
		String pathInfo = req.getPathInfo();
		
		String goTo = "";
		
		if(pathInfo.equals("/enroll")) {
			System.out.println("enroll");
			String m_id = (String)session.getAttribute("uid");
			String content = req.getParameter("content");
			int resRow = service.insertCommentIndex(m_id, content);
			if(resRow == 1) app.setAttribute("commentInsertResult", "success");
			else app.setAttribute("commentInsertResult", "failed");
			
			goTo = req.getContextPath() +  "/center/intro";
			res.sendRedirect(goTo);
			return;
		} else if(pathInfo.equals("/revise")) {
			String revise = req.getParameter("revise"); // 수정한 본문;;;;;
			int c_num = Integer.parseInt(req.getParameter("c_num"));
			int resRow = service.reviseCommentIndex(c_num, revise);
			if(resRow == 1) app.setAttribute("commentResult", "success");
			else app.setAttribute("commentResult", "failed");
			
			goTo = req.getContextPath() +  "/center/intro";
			res.sendRedirect(goTo);
			return;
		} else if(pathInfo.equals("/delete")) {
			String c_numStr = req.getParameter("c_num");
			int c_num = Integer.parseInt(c_numStr);
			int resRow = service.deleteCommentIndex(c_num);
			if(resRow == 1) app.setAttribute("commentResult", "success");
			else app.setAttribute("commentResult", "failed");
			
			goTo = req.getContextPath() +  "/center/intro";
			res.sendRedirect(goTo);
			return;
		}
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	@Override
	public void destroy() {
		System.out.println("CenterCommentController : destroy method activated.");
	}
}
