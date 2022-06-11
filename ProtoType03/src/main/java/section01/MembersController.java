package section01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns= {"/account/*"})
public class MembersController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("MembersController : init method activated.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MembersController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MembersController : doPost method activated.");
		doHandle(req, res);
	}
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MembersController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		MembersService service = new MembersService();
		
		String pathInfo = req.getPathInfo();
		
		// 새로운 페이지로 가는 url
		String goTo = "";
		
		// 우주정거장 같은 역할. pathInfo를 가지고 다른 곳으로 쏠 url을 결정한다.
		if(pathInfo.equals("/login")) {
			service.printAllMembers();
			goTo = "/view/login.jsp";
		} else if(pathInfo.equals("/signup")) goTo = "/view/signup.jsp";
		else if(pathInfo.equals("/findid")) goTo = "/view/findid.jsp";
		else if(pathInfo.equals("/findpw")) goTo = "/view/findpw.jsp";
		
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	
	@Override
	public void destroy() {
		System.out.println("MembersController : destroy method activated.");
	}
}
