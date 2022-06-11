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
		
		// 새로운 페이지로 가는 url
		String goTo = "";
		
		// 우주정거장 같은 역할. pathInfo를 가지고 다른 곳으로 쏠 url을 결정한다.
		// 1과 3은 사실 정상적인 처리 안에서는 굳이 할 필요 없지만, 혹시 url을 가지고 장난치는 사람이 있으면 발생할 수 있는 예외를 처리하기 위해 작성했습니다.
		if(categoryName == null) { // 1
			// session은 로그인 특화기능에 사용하려고 양보했습니다. 지금은 앱 애플리케이션 단의 컨텍스트 객체 ServletContext를 이용했습니다. jsp에서는 application이라는 변수로 사용됩니다.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "해당 과목을 찾을 수 없습니다.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else if(lectureService.getLecturesNum(categoryName) == 0) { // 2 : 해당 과목의 강의 갯수가 하나도 없을 때 여기로 들어옵니다.
			// 이건 사용자나 이 과목을 만든 강의자가 아닐 때 뜨는 거고, 이 과목을 만든 강의자나 관리자는 강의 페이지 작성 페이지로 넘어가야 합니다.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "과목 준비 중입니다.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else {
			HashMap<String, Object> lectureResult = lectureService.getLecturesAndOneLecture(categoryName, scat);
			if(lectureResult.get("resultCode").equals("no_data")) { // 3
				req.setAttribute("resultCode", "noPage");
				req.setAttribute("noPage", "해당 강의를 찾을 수 없습니다.");
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
