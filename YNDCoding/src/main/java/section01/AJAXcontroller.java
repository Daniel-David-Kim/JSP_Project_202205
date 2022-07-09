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
			if(service.isIdDuplicate(id) == true) resultStr += "id=중복되는 아이디입니다.&";
			else resultStr += "id=ok&";
			if(Validation.pwValidation(pw) == false) resultStr += "pw=비밀번호는 8자 이상이어야 합니다.&";
			else resultStr += "pw=ok&";
			try {
				uclass = Integer.parseInt(uclassStr);
				if(Validation.classValidation(uclass) == false) resultStr += "uclass=0-2 사이의 값만 입력해주세요.&";
				else resultStr += "uclass=ok&";
			} catch(NumberFormatException e) {resultStr += "uclass=정수만 입력해주세요.&";}
			if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=번호의 형식에 맞지 않습니다.<br>010-X000-0000 혹은 011-X00-0000\n(X은 1-9 사이의 정수, 0은 0-9 사이의 정수)&";
			else {
				if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=중복되는 번호입니다.&";
				else resultStr += "phone=ok&";
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/edit_validate")) {
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			
			String resultStr = "";
			if(pw != null && !pw.equals("")) {
				if(Validation.pwValidation(pw) == false) resultStr += "pw=비밀번호는 8자 이상이어야 합니다.&";
				else resultStr += "pw=ok&";
			}
			if(phone != null && !phone.equals("")) {
				if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=번호의 형식에 맞지 않습니다.<br>010-X000-0000 혹은 011-X00-0000\n(X은 1-9 사이의 정수, 0은 0-9 사이의 정수)&";
				else {
					if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=중복되는 번호입니다.&";
					else resultStr += "phone=ok&";
				}
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/update_validate")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			String uclass = req.getParameter("uclass"); // 유저의 클래스값
			
			String resultStr = "";
			if(id != null && !id.equals("")) { // 아이디 중복 체크!
				if(service.isIdDuplicate(id) == true) resultStr += "id=중복되는 아이디입니다.&";
				else resultStr += "id=ok&";
			}
			if(pw != null && !pw.equals("")) {
				if(Validation.pwValidation(pw) == false) resultStr += "pw=비밀번호는 8자 이상이어야 합니다.&";
				else resultStr += "pw=ok&";
			}
			if(phone != null && !phone.equals("")) {
				if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=번호의 형식에 맞지 않습니다.<br>010-X000-0000 혹은 011-X00-0000\n(X은 1-9 사이의 정수, 0은 0-9 사이의 정수)&";
				else {
					if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=중복되는 번호입니다.&";
					else resultStr += "phone=ok&";
				}
			}
			if(uclass != null && !uclass.equals("")) { // 클래스 유효성 체크!
				int uclassInt = Integer.parseInt(uclass);
				if(Validation.classValidation(uclassInt) == false) resultStr += "uclass=0-2 사이의 값만 입력해주세요.&";
				else resultStr += "uclass=ok&";
			}
			out.print(resultStr);
			return;
		} else if(pathInfo.equals("/findid")) {
			String name = req.getParameter("name");
			String tel = req.getParameter("tel");
			HashMap<String, Object> target = service.findOneMember(name, tel);
			if((int)target.get("count") == 0) out.print("회원 정보를 찾을 수 없습니다.");
			else {
				MemberBean bean = (MemberBean)target.get("memberInfo");
				out.print("아이디는 " + bean.getM_id() + " 입니다.");
			}
			return;
		} else if(pathInfo.equals("/findpw")) {
			String id = req.getParameter("id");
			HashMap<String, Object> target = service.getOneMemberInfo(id);
			if((int)target.get("count") == 0) out.print("회원 정보를 찾을 수 없습니다.");
			else {
				MemberBean memberInfo = (MemberBean)target.get("memberInfo");
				app.setAttribute("findMember", memberInfo);
				out.print(memberInfo.getM_findq());
			}
			return;
		} else if(pathInfo.equals("/findpw2")) {
			String answer = req.getParameter("answer");
			MemberBean memberInfo = (MemberBean)app.getAttribute("findMember");
			if(!answer.equals(memberInfo.getM_finda())) out.print("답이 틀렸습니다. 회원 정보를 찾을 수 없습니다.");
			else out.print("비밀번호는 " + memberInfo.getM_pw() + " 입니다.");
			return;
		} else if(pathInfo.equals("/signup_validate")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String phone = req.getParameter("phone");
			
			String resultStr = "";
			if(service.isIdDuplicate(id) == true) resultStr += "id=중복되는 아이디입니다.&";
			else resultStr += "id=ok&";
			if(Validation.pwValidation(pw) == false) resultStr += "pw=비밀번호는 8자 이상이어야 합니다.&";
			else resultStr += "pw=ok&";
			if(Validation.phoneFormValidation(phone) == false) resultStr += "phone=번호의 형식에 맞지 않습니다.<br>010-X000-0000 혹은 011-X00-0000\n(X은 1-9 사이의 정수, 0은 0-9 사이의 정수)&";
			else {
				if(service.isPhoneDuplicate(phone) == true) resultStr += "phone=중복되는 번호입니다.&";
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
