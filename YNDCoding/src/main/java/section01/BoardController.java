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
			String authorId = (String)app.getAttribute("author_id");
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/addQuestion.jsp";
		
		} else if(divs.length == 3 && divs[2].equals("addQuestion_process")) { // 질문 그냥 추가
			String authorId = (String)app.getAttribute("author_id");
			String m_id = (String)session.getAttribute("uid");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			long curTime = System.currentTimeMillis();
			java.sql.Date curDate = new java.sql.Date(curTime);
			int resRow = boardService.insertNewQuestion(authorId, m_id, title, content, curDate);
			
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			//app.removeAttribute("author_id");
			if(resRow == 1) app.setAttribute("boardResult", "success");
			else app.setAttribute("boardResult", "failed");
			goTo = req.getContextPath() + "/board/lectureBoard";
			res.sendRedirect(goTo);
			return;
			
		} else if(divs.length == 3 && divs[2].equals("question")) {
			String q_id = req.getParameter("q_id"); // 해당 질문의 id를 가져온다.
			String authorId = (String)app.getAttribute("author_id");
			System.out.println("q_id : " + q_id + " / authorId : " + authorId);
			HashMap<String, Object> result = boardService.getOneArticle(authorId, q_id);
			if((int)result.get("numOfRows") == 1) {
				app.setAttribute("questionDraw", "true");
				BoardBean bean = (BoardBean)result.get("article");
				req.setAttribute("article", bean);
			} else {
				app.setAttribute("questionDraw", "false");
				app.setAttribute("error_message", "질문을 찾는 데 실패했습니다.");
				goTo = req.getContextPath() + "/view/lecturerBoard.jsp";
				res.sendRedirect(goTo);
				return;
			}
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/question.jsp";
		
		} else if(divs.length == 3 && divs[2].equals("reply")) { // 응답(답글) 화면으로 넘어갈 때
			String q_num = req.getParameter("q_num"); // 해당 질문의 id를 가져온다.
			String authorId = (String)app.getAttribute("author_id");
			req.setAttribute("p_num", q_num);
			
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/addReply.jsp";
		
		} else if(divs.length == 3 && divs[2].equals("reply_process")) {
			String authorId = (String)app.getAttribute("author_id");
			String p_num = req.getParameter("p_num");
			String m_id = (String)session.getAttribute("uid");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			long curTime = System.currentTimeMillis();
			java.sql.Date curDate = new java.sql.Date(curTime);
			
			int resRow = boardService.insertReply(authorId, p_num, m_id, title, content, curDate);
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			//app.removeAttribute("author_id");
			if(resRow == 1) app.setAttribute("boardResult", "success");
			else app.setAttribute("boardResult", "failed");
			goTo = req.getContextPath() + "/board/lectureBoard";
			res.sendRedirect(goTo);
			return;
		
		} else if (divs.length == 3 && divs[2].equals("update")) {
			String authorId = (String)app.getAttribute("author_id");
			String q_num = req.getParameter("q_num");
			HashMap<String, Object> result = boardService.getOneArticle(authorId, q_num);
			if((int)result.get("numOfRows") == 1) {
				app.setAttribute("questionDraw", "true");
				BoardBean bean = (BoardBean)result.get("article");
				req.setAttribute("article", bean);
			} else {
				app.setAttribute("questionDraw", "false");
				app.setAttribute("error_message", "질문을 찾는 데 실패했습니다.");
				goTo = req.getContextPath() + "/view/lecturerBoard.jsp";
				res.sendRedirect(goTo);
				return;
			}
			req.setAttribute("q_num", q_num);
			req.setAttribute("update", "true");
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			app.removeAttribute("author_id");
			goTo = "/view/addQuestion.jsp";
			
		} else if (divs.length == 3 && divs[2].equals("update_process")) {
			String authorId = (String)app.getAttribute("author_id");
			String q_num = req.getParameter("q_num");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int resRow = boardService.updateQuestion(authorId, q_num, title, content);
			
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			if(resRow == 1) app.setAttribute("boardResult", "success");
			else app.setAttribute("boardResult", "failed");
			goTo = req.getContextPath() + "/board/lectureBoard";
			res.sendRedirect(goTo);
			return;
		
		}else if (divs.length == 3 && divs[2].equals("deleteQ")) {
			String authorId = (String)app.getAttribute("author_id");
			String q_num = req.getParameter("q_num");
			int resRow = boardService.deleteQuestion(authorId, q_num);
			
			HashMap<String, Object> articles = boardService.getPager(authorId, 1, 10);
			HashMap<String, Object> authorInfo = service.getAuthorInfo(authorId);
			req.setAttribute("articles", articles);
			req.setAttribute("authorInfo", authorInfo);
			if(resRow == 1) app.setAttribute("boardResult", "success");
			else app.setAttribute("boardResult", "failed");
			goTo = req.getContextPath() + "/board/lectureBoard";
			res.sendRedirect(goTo);
			return;
			
		} else if (divs[1].equals("lectureBoard")) { // 강의자 게시판으로 접속할 때 : 무조건 첫페이지!
			String authorId = req.getParameter("author_id");
			if(authorId == null || authorId.equals("")) {
				authorId = (String)app.getAttribute("author_id");
				app.removeAttribute("author_id");
			}
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
