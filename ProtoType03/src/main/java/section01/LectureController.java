package section01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns= {"/lecture/*"})
public class LectureController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("LectureController : init method activated.");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LectureController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LectureController : doPost method activated.");
		doHandle(req, res);
	}
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LectureController : doHandle method activated.");
		boolean toDispatch = true;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();
		PrintWriter out = res.getWriter();
		LectureService lectureService = new LectureService();
		MenusService menusService = new MenusService();
		HashMap<String, Vector<MenusBean>> menus = menusService.getSplitedCategory();
		
		System.out.println(req.getRequestURI());
		String pathInfo = req.getPathInfo();
		String[] divs = pathInfo.split("/");
		for(int i = 0; i < divs.length; i++) System.out.println("idx " + i + " -> " + divs[i]);
		int bcat = Integer.parseInt(divs[1]);
		int scat = 1;
		if(divs.length > 2) scat = Integer.parseInt(divs[2]);
		String categoryName = menusService.getCategoryName(bcat);
		
		// ���ο� �������� ���� url
		String goTo = "";
		
		// ���������� ���� ����. pathInfo�� ������ �ٸ� ������ �� url�� �����Ѵ�.
		// 1�� 3�� ��� �������� ó�� �ȿ����� ���� �� �ʿ� ������, Ȥ�� url�� ������ �峭ġ�� ����� ������ �߻��� �� �ִ� ���ܸ� ó���ϱ� ���� �ۼ��߽��ϴ�.
		if(categoryName == null) { // 1
			// session�� �α��� Ưȭ��ɿ� ����Ϸ��� �纸�߽��ϴ�. ������ �� ���ø����̼� ���� ���ؽ�Ʈ ��ü ServletContext�� �̿��߽��ϴ�. jsp������ application�̶�� ������ ���˴ϴ�.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "�ش� ������ ã�� �� �����ϴ�.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else if(lectureService.getLecturesNum(categoryName) == 0) { // 2 : �ش� ������ ���� ������ �ϳ��� ���� �� ����� ���ɴϴ�.
			// �̰� ����ڳ� �� ������ ���� �����ڰ� �ƴ� �� �ߴ� �Ű�, �� ������ ���� �����ڳ� �����ڴ� ���� ������ �ۼ� �������� �Ѿ�� �մϴ�.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "���� �غ� ���Դϴ�.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else {
			HashMap<String, Object> lectureResult = lectureService.getLecturesAndOneLecture(categoryName, scat);
			if(lectureResult.get("resultCode").equals("no_data")) { // 3
				req.setAttribute("resultCode", "noPage");
				req.setAttribute("noPage", "�ش� ���Ǹ� ã�� �� �����ϴ�.");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				goTo = "/view/lecture.jsp";
			} else { // 4
				System.out.println("ok");
				req.setAttribute("resultCode", "ok");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				goTo = "/view/lecture.jsp";
			}
		}
		
		if(toDispatch == true) {
			System.out.println("goTo : " + goTo);
			RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
			dispatch.forward(req, res);
		} else {
			res.sendRedirect(goTo);
		}
		System.out.println("end");
		System.out.println();
	}
	
	@Override
	public void destroy() {
		System.out.println("LectureController : destroy method activated.");
	}

}
