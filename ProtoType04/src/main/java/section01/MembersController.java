package section01;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		String imgPath = "D:\\WS\\JavaServer\\ProtoType04\\src\\main\\webapp\\resources\\images";
		ServletContext app = req.getServletContext();
		
		String pathInfo = req.getPathInfo();

		// 새로운 페이지로 가는 url
		String goTo = "";
		

		// 우주정거장 같은 역할. pathInfo를 가지고 다른 곳으로 쏠 url을 결정한다.
		if (pathInfo.equals("/login")) {
			goTo = "/view/login.jsp";
		} else if(pathInfo.equals("/login_process")) {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			HashMap<String, Object> result = service.MemberIP(id, pw);
			MemberBean user = (MemberBean)result.get("member");
			int count = (int)result.get("count");
			if (count == 1) {
				session.setAttribute("uid", id);
				session.setAttribute("uname", user.getM_name());
				session.setAttribute("utel", user.getM_contact());
				session.setAttribute("uclass", user.getM_class());
				session.setAttribute("uprofile", user.getM_profile());
				session.setAttribute("logined", "true");
				session.setAttribute("loginResult", "true");
				goTo = req.getContextPath() + "/center/intro";
				res.sendRedirect(goTo);
				return;
			} else {
				session.setAttribute("loginResult", "false");
				session.setAttribute("errorLogin", "로그인에 실패했습니다. 계정을 다시 확인해주세요.");
				goTo = req.getContextPath() + "/center/intro";
				res.sendRedirect(goTo);
				return;
			}
		} else if (pathInfo.equals("/logout")) {
			session.removeAttribute("uid");
			session.removeAttribute("uname");
			session.removeAttribute("utel");
			session.removeAttribute("uclass");
			session.removeAttribute("uprofile");
			session.removeAttribute("logined");
			goTo = req.getContextPath() + "/center/intro";
			res.sendRedirect(goTo);
			return;
		} else if (pathInfo.equals("/signup"))
			goTo = "/view/signup.jsp";
		else if (pathInfo.equals("/editProfile")) {
			System.out.println("deitProfile");
			String findId = (String)session.getAttribute("uid");
			HashMap<String, Object> result = service.getOneMemberInfo(findId);
			if((int)result.get("count") == 1) {
				System.out.println("deitProfile1");
				session.setAttribute("memberDraw", "success");
				session.setAttribute("memberInfo", (MemberBean)result.get("memberInfo"));
			} else {
				System.out.println("deitProfile2");
				session.setAttribute("memberDraw", "failed");
			}
			goTo = "/view/editProfile.jsp";
		
		} else if (pathInfo.equals("/editProfile_process")) { // 프로필 수정
			System.out.println("in process : revise");
			String m_name = null; String m_pw = null; String m_findq = null; String m_finda = null; String m_contact = null; Blob m_profile = null;
			String profileChBool = null; boolean profileChanged = false; String changedFields = null; String sphone1 = null; String sphone2 = null; String sphone3 = null;
			Vector<String> changed = new Vector();
			int[] idxArr = new int[8];
            Object[] objArr = new Object[8];
            for(int i = 0; i < idxArr.length; i++) {idxArr[i] = -1; objArr[i] = null;}
            int idx = -1;
			
			File imgd = new File(imgPath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(imgd);
			factory.setSizeThreshold(1024*1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(req);
				for(FileItem item : items) {
					if(item.isFormField()) {
						int tempidx = -1;
						Object target = null;
						String field = item.getFieldName();
						switch(field) {
							case "sname": field="m_name"; if(changed.contains(field)) {m_name = item.getString("utf-8"); tempidx = 2; target = m_name;} break;
							case "spw1": field="m_pw"; if(changed.contains(field)) {m_pw = item.getString("utf-8"); tempidx = 1; target = m_pw;} break;
							case "squestion": field="m_findq"; if(changed.contains(field)) {m_findq = item.getString("utf-8"); tempidx = 3; target = m_findq;} break;
							case "sanswer": field="m_finda"; if(changed.contains(field)) {m_finda = item.getString("utf-8"); tempidx = 4; target = m_finda;} break;
							case "sphone1": field="m_contact"; if(changed.contains(field)) {sphone1 = item.getString("utf-8"); tempidx = -1;} break;
							case "sphone2": field="m_contact"; if(changed.contains(field)) {sphone2 = item.getString("utf-8"); tempidx = -1;} break;
							case "sphone3": 
								field="m_contact";
								if(changed.contains(field)) {
									sphone3 = item.getString("utf-8"); tempidx = -1; 
									m_contact = sphone1 + "-" + sphone2 + "-" + sphone3;
									tempidx = 5;
									target = m_contact;
									System.out.println("m_contact"); 
								} 
								break;
							case "profileChanged" : profileChBool = item.getString("utf-8"); profileChanged = Boolean.parseBoolean(profileChBool); break;
							case "changedFields" : changedFields = item.getString("utf-8"); String[] temp = changedFields.split(","); 
								changed = Arrays.stream(temp).collect(Collectors.toCollection(Vector::new)); System.out.println("changed : " + changed); break;
						}
						if(tempidx != -1) {
							idxArr[tempidx] = 1;
							objArr[tempidx] = target;
						}
					} else {
						if((item.getFieldName().equals("sprofile")&&(profileChanged == true))) {
							BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							byte[] buffer = new byte[512];
							while(bis.read(buffer) != -1) baos.write(buffer);
							byte[] resArr = baos.toByteArray();
							try {
								Blob target = null;
								if(resArr.length != 0) target = new SerialBlob(resArr);
								int tempidx = 6;
								
								idxArr[tempidx] = 1;
								objArr[tempidx] = target;
							} catch(SQLException e) {System.out.println("Blob create failed : MembersController(editProfile_process)");}
						}	
					}
				}
				// 필드 처리 완료. 처리 로직 밑에다가 작성. : 
				
				String user_id = (String)session.getAttribute("uid");
				int result = service.reviseMember(idxArr, objArr, user_id);
				if(result == 1) app.setAttribute("memberRevise", "true");
				else app.setAttribute("memberRevise", "false");
			} catch(FileUploadException e) {System.out.println("File upload Exception : MembersController(editProfile_process)");}
			
			// 프로필 수정 처리 완료.
			goTo = req.getContextPath() + "/account/accountInfo";
			res.sendRedirect(goTo);
			return;
			
		} else if (pathInfo.equals("/signup_process")) { // 회원가입 로직 처리
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
			
			int resRow = service.insertSQL(sname, sid, spw2, squestion, sanswer, sphone);
			if(resRow == 1) app.setAttribute("signupResult", "success");
			else app.setAttribute("signupResult", "failed");
			
			goTo = req.getContextPath() + "/center/intro";
			res.sendRedirect(goTo);
			return;
			
		} else if (pathInfo.equals("/findid"))
			goTo = "/view/findid.jsp";
		else if (pathInfo.equals("/findpw"))
			goTo = "/view/findpw.jsp";
		else if (pathInfo.equals("/accountInfo")) { // 계정 정보 메인
			String findId = (String)session.getAttribute("uid");
			HashMap<String, Object> result = service.getOneMemberInfo(findId);
			if((int)result.get("count") == 1) {
				MemberBean user = (MemberBean)result.get("memberInfo");
				session.setAttribute("uid", findId);
				session.setAttribute("uname", user.getM_name());
				session.setAttribute("utel", user.getM_contact());
				session.setAttribute("uclass", user.getM_class());
				session.setAttribute("uprofile", user.getM_profile());
				session.setAttribute("logined", "true");
				session.setAttribute("loginResult", "true");
			} else {
				session.setAttribute("loginResult", "false");
				session.setAttribute("errorLogin", "계정 연동에 실패했습니다. 다시 로그인해주세요.");
				goTo = req.getContextPath() + "/center/intro";
				res.sendRedirect(goTo);
				return;
			}
			goTo = "/view/accountMain.jsp";
			
		} else if (pathInfo.equals("/adminBoard")) {
			req.setAttribute("memberList", service.SelectMember());
			goTo = "/view/adminBoard.jsp";
		} else if (pathInfo.equals("/insert_member"))
			goTo = "/view/insertMember.jsp";
		else if (pathInfo.equals("/enroll_process")) {
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
	        int sclass =  Integer.parseInt(req.getParameter("sclass"));
	        int resRow = service.insertMember(sname, sid, spw2, squestion, sanswer, sphone, sclass);			
	        if(resRow == 1) app.setAttribute("adminEnrollResult", "success");
			else app.setAttribute("adminEnrollResult", "failed");
	        
			goTo = req.getContextPath() + "/account/adminBoard";
			res.sendRedirect(goTo);
			return;
		} else if (pathInfo.equals("/adminBoard_process")) { // 관리자 게시판에서 회원 삭제 루트
	         String id = req.getParameter("m_id");
	         int resRow = service.deletemember(id);
	         if(resRow == 1) app.setAttribute("adminResult", "success");
	         else app.setAttribute("adminResult", "failed");
	         
	         goTo = req.getContextPath() + "/account/adminBoard";
	         res.sendRedirect(goTo);
	         return;
	    } else if (pathInfo.equals("/updateMember")) {
	    	String m_id = req.getParameter("hdid");
	    	HashMap<String, Object> result = service.getOneMemberInfo(m_id);
	    	if((int)result.get("count") == 1) {
				System.out.println("deitProfile1");
				session.setAttribute("memberDraw", "success");
				session.setAttribute("memberInfo", (MemberBean)result.get("memberInfo"));
			} else {
				System.out.println("deitProfile2");
				session.setAttribute("memberDraw", "failed");
			}
	    	goTo = "/view/adminEditMember.jsp";
	    } else if (pathInfo.equals("/updateMember_process")) {
	    	String origin = null;
	    	// 아이디 추가
	    	String m_id = null; String m_name = null; String m_pw = null; String m_findq = null; String m_finda = null; String m_contact = null; Blob m_profile = null;
			String profileChBool = null; boolean profileChanged = false; String changedFields = null; String sphone1 = null; String sphone2 = null; String sphone3 = null;
			String uclassStr = null; int m_class = -1;
			Vector<String> changed = new Vector();
			int[] idxArr = new int[8];
            Object[] objArr = new Object[8];
            for(int i = 0; i < idxArr.length; i++) {idxArr[i] = -1; objArr[i] = null;}
            int idx = -1;
			
			File imgd = new File(imgPath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(imgd);
			factory.setSizeThreshold(1024*1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(req);
				for(FileItem item : items) {
					if(item.isFormField()) {
						int tempidx = -1;
						Object target = null;
						String field = item.getFieldName();
						switch(field) { // 아이디 추가
							case "sid": field="m_id"; if(changed.contains(field)) {m_id = item.getString("utf-8"); tempidx = 0; target = m_id;} break;
							case "sname": field="m_name"; if(changed.contains(field)) {m_name = item.getString("utf-8"); tempidx = 2; target = m_name;} break;
							case "spw1": field="m_pw"; if(changed.contains(field)) {m_pw = item.getString("utf-8"); tempidx = 1; target = m_pw;} break;
							case "squestion": field="m_findq"; if(changed.contains(field)) {m_findq = item.getString("utf-8"); tempidx = 3; target = m_findq;} break;
							case "sanswer": field="m_finda"; if(changed.contains(field)) {m_finda = item.getString("utf-8"); tempidx = 4; target = m_finda;} break;
							case "sphone1": field="m_contact"; if(changed.contains(field)) {sphone1 = item.getString("utf-8"); tempidx = -1;} break;
							case "sphone2": field="m_contact"; if(changed.contains(field)) {sphone2 = item.getString("utf-8"); tempidx = -1;} break;
							case "sphone3": 
								field="m_contact";
								if(changed.contains(field)) {
									sphone3 = item.getString("utf-8"); tempidx = -1; 
									m_contact = sphone1 + "-" + sphone2 + "-" + sphone3;
									tempidx = 5;
									target = m_contact;
									System.out.println("m_contact"); 
								} 
								break;
							case "sclass": field="m_class"; if(changed.contains(field)) {uclassStr = item.getString("utf-8"); m_class = Integer.parseInt(uclassStr); tempidx = 7; target = m_class;} break;
							case "originalId": origin = item.getString("utf-8"); break;
							case "profileChanged" : profileChBool = item.getString("utf-8"); profileChanged = Boolean.parseBoolean(profileChBool); break;
							case "changedFields" : changedFields = item.getString("utf-8"); String[] temp = changedFields.split(","); 
								changed = Arrays.stream(temp).collect(Collectors.toCollection(Vector::new)); System.out.println("changed : " + changed); break;
						}
						if(tempidx != -1) {
							idxArr[tempidx] = 1;
							objArr[tempidx] = target;
						}
					} else {
						if((item.getFieldName().equals("sprofile")&&(profileChanged == true))) {
							BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							byte[] buffer = new byte[512];
							while(bis.read(buffer) != -1) baos.write(buffer);
							byte[] resArr = baos.toByteArray();
							try {
								Blob target = null;
								if(resArr.length != 0) target = new SerialBlob(resArr);
								int tempidx = 6;
								
								idxArr[tempidx] = 1;
								objArr[tempidx] = target;
							} catch(SQLException e) {System.out.println("Blob create failed : MembersController(editProfile_process)");}
						}	
					}
				}
				// 필드 처리 완료. 처리 로직 밑에다가 작성. : 
				
				// origin이 대신함. 
				// String user_id = (String)session.getAttribute("uid");   
				int result = service.reviseMember(idxArr, objArr, origin);
				if(result == 1) app.setAttribute("adminResult", "success");
				else app.setAttribute("adminResult", "failed");
			} catch(FileUploadException e) {System.out.println("File upload Exception : MembersController(updateMember_process)");}
			
			// 회원 수정 처리 완료.
			goTo = req.getContextPath() + "/account/adminBoard";
			res.sendRedirect(goTo);
			return;
	    	
	    }
		
		RequestDispatcher dispatch = req.getRequestDispatcher(goTo);
		dispatch.forward(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("MembersController : destroy method activated.");
	}
}
