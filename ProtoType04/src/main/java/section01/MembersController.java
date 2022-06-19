package section01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = { "/account/*" })
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

		// ���ο� �������� ���� url
		String goTo = "";
		

		// ���������� ���� ����. pathInfo�� ������ �ٸ� ������ �� url�� �����Ѵ�.
		if (pathInfo.equals("/login")) {
			goTo = "/view/login.jsp";
		} else if(pathInfo.equals("/login_process")) {
			String id = req.getParameter("id");
			System.out.println(id);
			String pw = req.getParameter("pw");
			System.out.println(service.MemberIP(id, pw));
			if (service.MemberIP(id, pw) == 1) {
				session.setAttribute("id", id);
				session.setAttribute("pw", pw);
				goTo = req.getContextPath() + "/center/intro";
				res.sendRedirect(goTo);
				return;
			} else {
				
			}
		} else if (pathInfo.equals("/signup"))
			goTo = "/view/signup.jsp";
		else if (pathInfo.equals("/editProfile"))
			goTo = "/view/editProfile.jsp";
		else if (pathInfo.equals("/signup_process")) {
			String sname = req.getParameter("sname");
			String sid = req.getParameter("sid");
			String spw1 = req.getParameter("spw1");
			String spw2 = req.getParameter("spw2");
			String squestion = req.getParameter("squestion");
			String sanswer = req.getParameter("sanswer");
			String sphone1 = req.getParameter("sphone1");
			String sphone2 = req.getParameter("sphone2");
			String sphone3 = req.getParameter("sphone3");
			String sphone = sphone1 + "-" + sphone2 + "-" + sphone3;	
			
			// ó���� �� �� ������ �� - > ������ ��� 
			try {
			if(service.signUpCheck(sid, sphone) == 1) { System.out.println("overlapping... change plz");
			} else
				service.insertSQL(sname, sid, spw2, squestion, sanswer, sphone);
				System.out.println("not overlapping... u can use it");
			}catch(Exception e) { System.out.println("aa"); }
			
			// signup.jsp�� �б⸦ ���� -> 2���� ����� : ����Ʈ �־ Ŭ���� ������ ó���ϴ� �Ŷ� //// �Ϲ����� ������ �׳� ���� �ܼ��� Ŭ���� ������ ���ӿ����� �����
			
			// ��й�ȣ 8�� �̻� üũ
			try {
				if(spw1.length() < 8) { System.out.println("passwd < 8");
				} else System.out.println("passwd >= 8");
			}catch(Exception e) { System.out.println("2222"); }
			
			// 1�� 2�� ��й�ȣ Ȯ��
			try {
				if (spw1.equals(spw2)) { System.out.println("same");
				} else System.out.println("differ");
			}catch(Exception e) { System.out.println("3333"); }
			
			goTo = "/view/signup.jsp";
		} else if (pathInfo.equals("/findid"))
			goTo = "/view/findid.jsp";
		else if (pathInfo.equals("/findpw"))
			goTo = "/view/findpw.jsp";
		else if (pathInfo.equals("/accountInfo"))
			goTo = "/view/accountMain.jsp";
		else if (pathInfo.equals("/lectureBoard"))
			goTo = "/view/lecturerBoard.jsp";
		else if (pathInfo.equals("/adminBoard"))
			goTo = "/view/adminBoard.jsp";
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("MembersController : destroy method activated.");
	}
}
