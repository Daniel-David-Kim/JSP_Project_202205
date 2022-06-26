package section01;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.stream.*;
import javax.sql.rowset.serial.*;
import javax.servlet.annotation.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

@WebServlet(urlPatterns= {"/board/*"})
public class BoardController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("BoardController : init method activated.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("BoardController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("BoardController : doPost method activated.");
		doHandle(req, res);
	}
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("BoardController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		ServletContext app = req.getServletContext();
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		MembersService service = new MembersService();
		BoardService boardService = new BoardService();
		
		String pathInfo = req.getPathInfo();
		String[] divs = pathInfo.split("/");
		for(int i = 0; i < divs.length; i++) System.out.println("idx " + i + " -> " + divs[i]);
		
		
		// 새로운 페이지로 가는 url
		String goTo = "";
		
		if(divs.length == 6 && divs[5].equals("next")) {
			String authorId = (String)app.getAttribute("author_id");
			int newStart = Integer.parseInt(divs[4]) + 10;
			HashMap<String, Object> articles = boardService.getPager(authorId, newStart, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/lecturerBoard.jsp";
		} else if(divs.length == 6 && divs[5].equals("prev")) {
			String authorId = (String)app.getAttribute("author_id");
			int newStart = Integer.parseInt(divs[4]) - 10;
			HashMap<String, Object> articles = boardService.getPager(authorId, newStart, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/lecturerBoard.jsp";
		} else if(divs.length == 5) {
			int newStart = Integer.parseInt(divs[4]);
			String authorId = (String)app.getAttribute("author_id");
			HashMap<String, Object> articles = boardService.getPager(authorId, newStart, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/lecturerBoard.jsp";
		} else if (divs.length == 3 && divs[2].equals("newQuestion")) {
			
			goTo = "/view/lecturerBoard.jsp";
		} else if (divs[1].equals("lectureBoard")) { // 강의자 게시판으로 접속할 때 : 무조건 첫페이지!
			String authorId = req.getParameter("author_id");
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			goTo = "/view/lecturerBoard.jsp";	
		} 
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	@Override
	public void destroy() {
		System.out.println("BoardController : destroy method activated.");
	}
}
