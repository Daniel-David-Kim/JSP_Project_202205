package section01;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns= {"/center/*"})
public class CenterController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("CenterController : init method activated.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterController : doPost method activated.");
		doHandle(req, res);
	}
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("CenterController : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		MenusService menusService = new MenusService();
		
		String pathInfo = req.getPathInfo();
		String goTo = "";
		
		// ���������� ���� ����. pathInfo�� ������ �ٸ� ������ �� url�� �����Ѵ�.
		if(pathInfo.equals("/intro")) {
			HashMap<String, Vector<MenusBean>> menus = menusService.getSplitedCategory();
			req.setAttribute("menus", menus);
			goTo = "/view/intro.jsp";
		}
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	
	@Override
	public void destroy() {
		System.out.println("CenterController : destroy method activated.");
	}
}
