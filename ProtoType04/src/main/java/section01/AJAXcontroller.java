package section01;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = { "/ajax/*" })
public class AJAXcontroller extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("AJAXcontroller : init method activated.");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AJAXcontroller : doGet method activated.");
		doHandle(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AJAXcontroller : doPost method activated.");
		doHandle(req, res);
	}
	protected void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AJAXcontroller : doHandle method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		MembersService service = new MembersService();
		ServletContext app = req.getServletContext();
		
		String pathInfo = req.getPathInfo();
		
		String goTo = "";
		
		if(pathInfo.equals("/enroll_validate")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String uclassStr = req.getParameter("uclass");
			int uclass = -1;
			String phone = req.getParameter("phone");
			
			String resultStr = "";
			if(service.isIdDuplicate(id) == true) resultStr += "id=�ߺ��Ǵ� ���̵��Դϴ�.&";
			else resultStr += "id=ok&";
			if(Validation.pwValidation(pw) == false) resultStr += "pw=��й�ȣ�� 8�� �̻��̾�� �մϴ�.&";
			else resultStr += "pw=ok&";
			try {
				uclass = Integer.parseInt(uclassStr);
				if(Validation.classValidation(uclass) == false) resultStr += "uclass=0-2 ������ ���� �Է����ּ���.&";
				else resultStr += "uclass=ok&";
			} catch(NumberFormatException e) {resultStr += "uclass=������ �Է����ּ���.&";}
			if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=��ȣ�� ���Ŀ� ���� �ʽ��ϴ�.<br>010-X000-0000 Ȥ�� 011-X00-0000\n(X�� 1-9 ������ ����, 0�� 0-9 ������ ����)&";
			else {
				if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=�ߺ��Ǵ� ��ȣ�Դϴ�.&";
				else resultStr += "phone=ok&";
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/edit_validate")) {
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			
			String resultStr = "";
			if(pw != null && !pw.equals("")) {
				if(Validation.pwValidation(pw) == false) resultStr += "pw=��й�ȣ�� 8�� �̻��̾�� �մϴ�.&";
				else resultStr += "pw=ok&";
			}
			if(phone != null && !phone.equals("")) {
				if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=��ȣ�� ���Ŀ� ���� �ʽ��ϴ�.<br>010-X000-0000 Ȥ�� 011-X00-0000\n(X�� 1-9 ������ ����, 0�� 0-9 ������ ����)&";
				else {
					if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=�ߺ��Ǵ� ��ȣ�Դϴ�.&";
					else resultStr += "phone=ok&";
				}
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/update_validate")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			String uclass = req.getParameter("uclass"); // ������ Ŭ������
			
			String resultStr = "";
			if(id != null && !id.equals("")) { // ���̵� �ߺ� üũ!
				if(service.isIdDuplicate(id) == true) resultStr += "id=�ߺ��Ǵ� ���̵��Դϴ�.&";
				else resultStr += "id=ok&";
			}
			if(pw != null && !pw.equals("")) {
				if(Validation.pwValidation(pw) == false) resultStr += "pw=��й�ȣ�� 8�� �̻��̾�� �մϴ�.&";
				else resultStr += "pw=ok&";
			}
			if(phone != null && !phone.equals("")) {
				if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=��ȣ�� ���Ŀ� ���� �ʽ��ϴ�.<br>010-X000-0000 Ȥ�� 011-X00-0000\n(X�� 1-9 ������ ����, 0�� 0-9 ������ ����)&";
				else {
					if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=�ߺ��Ǵ� ��ȣ�Դϴ�.&";
					else resultStr += "phone=ok&";
				}
			}
			if(uclass != null && !uclass.equals("")) { // Ŭ���� ��ȿ�� üũ!
				int uclassInt = Integer.parseInt(uclass);
				if(Validation.classValidation(uclassInt) == false) resultStr += "uclass=0-2 ������ ���� �Է����ּ���.&";
				else resultStr += "uclass=ok&";
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/findid")) {
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			HashMap<String, Object> target = service.findOneMember(name, tel);
			if((int)target.get("count") == 0) out.print("ȸ�� ������ ã�� �� �����ϴ�.");
			else {
				MemberBean bean = (MemberBean)target.get("memberInfo");
				out.print("���̵�� " + bean.getM_id() + " �Դϴ�.");
			}
			return;
		} else if(pathInfo.equals("/findpw")) {
			String id = req.getParameter("id");
			HashMap<String, Object> target = service.getOneMemberInfo(id);
			if((int)target.get("count") == 0) out.print("ȸ�� ������ ã�� �� �����ϴ�.");
			else {
				MemberBean memberInfo = (MemberBean)target.get("memberInfo");
				app.setAttribute("findMember", memberInfo);
				out.print(memberInfo.getM_findq());
			}
			return;
		} else if(pathInfo.equals("/findpw2")) {
			String answer = req.getParameter("answer");
			MemberBean memberInfo = (MemberBean)app.getAttribute("findMember");
			if(!answer.equals(memberInfo.getM_finda())) out.print("���� Ʋ�Ƚ��ϴ�. ȸ�� ������ ã�� �� �����ϴ�.");
			else out.print("��й�ȣ�� " + memberInfo.getM_pw() + " �Դϴ�.");
			return;
		} else if(pathInfo.equals("/signup_validate")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			
			String resultStr = "";
			if(service.isIdDuplicate(id) == true) resultStr += "id=�ߺ��Ǵ� ���̵��Դϴ�.&";
			else resultStr += "id=ok&";
			if(Validation.pwValidation(pw) == false) resultStr += "pw=��й�ȣ�� 8�� �̻��̾�� �մϴ�.&";
			else resultStr += "pw=ok&";
			if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=��ȣ�� ���Ŀ� ���� �ʽ��ϴ�.<br>010-X000-0000 Ȥ�� 011-X00-0000\n(X�� 1-9 ������ ����, 0�� 0-9 ������ ����)&";
			else {
				if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=�ߺ��Ǵ� ��ȣ�Դϴ�.&";
				else resultStr += "phone=ok&";
			}
			out.print(resultStr);
			return;
		}
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}
	@Override
	public void destroy() {
		System.out.println("AJAXcontroller : destroy method activated.");
	}
}
