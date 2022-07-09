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
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LectureController : doHandle method activated.");
		
		// 여기다 저장할 일은 없지만, 파일 저장시 경로는 등록을 해놔야 하므로 입력해줍니다.
		String imgPath = "D:\\WS\\JavaServer\\ProtoType04\\src\\main\\webapp\\resources\\images";
		
		boolean toDispatch = true;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();
		PrintWriter out = res.getWriter();
		LectureService lectureService = new LectureService();
		MenusService menusService = new MenusService();
		MembersService membersService = new MembersService();
		CommentService commentService = new CommentService();
		ServletContext app = req.getServletContext();
		HashMap<String, Vector<MenusBean>> menus = menusService.getSplitedCategory();
		
		System.out.println(req.getRequestURI());
		String pathInfo = req.getPathInfo();
		String[] divs = pathInfo.split("/");
		for(int i = 0; i < divs.length; i++) System.out.println("idx " + i + " -> " + divs[i]);
		int bcat = Integer.parseInt(divs[1]); // C : 2001 -> 과목 번호는 무조건 있을 수밖에 없습니다.
		
		int scat = 1; // 강의 번호. 강의를 추가, 수정, 삭제할 때는 무조건 0이 나옵니다.  
		if(divs.length > 2) {
			// 다만, 강의 번호는 처음 메뉴에서 과목을 선택해서 들어올 때는 여기서 생성되지 않습니다.
			scat = Integer.parseInt(divs[2]);
			System.out.println("changed scat : " + scat);
		}
		// scat 1 : 처음 메뉴에서 과목을 선택해서 들어올 때
		
		MenusBean targetMenu = menusService.getCategory(bcat); // 과목번호가 무조건 있듯이, 얘도 무조건 값이 있습니다.
		String author_id = targetMenu.getM_id(); // 무조건 값이 있습니다.
		HashMap<String, Object> authorInfo = membersService.getAuthorInfo(author_id); // 무조건 값이 있습니다.
		String categoryName = ""; // 과목의 이름. 무조건 값이 있습니다. -> 댓글을 가져올 때 유용하게 사용합니다.
		if(targetMenu != null) categoryName = targetMenu.getMenu_name(); // 무조건 값이 있습니다.
		
		HashMap<String, Object> comments = null;
		// 이 지점에서 댓글을 소환해줍니다.
		if(scat != 0) { // scat이 0인 경우는 댓글을 가져올 필요가 없으므로 생략합니다.
			comments = commentService.getCommentsByScat(categoryName, scat);
		}
		//
		
		int uclass = 2;
		String uid = null;
		if(session.getAttribute("uclass") != null) uclass = (int)session.getAttribute("uclass");
		if(session.getAttribute("uid") != null) uid = (String)session.getAttribute("uid");
		
		// 새로운 페이지로 가는 url
		String goTo = "";
		
		// 우주정거장 같은 역할. pathInfo를 가지고 다른 곳으로 쏠 url을 결정한다.
		// 1과 3은 사실 정상적인 처리 안에서는 굳이 할 필요 없지만, 혹시 url을 가지고 장난치는 사람이 있으면 발생할 수 있는 예외를 처리하기 위해 작성했습니다.
		if((divs.length > 3) && divs[3].equals("addLecture")) { // 강의 추가 페이지 진입
			req.setAttribute("add", "true");
			req.setAttribute("categoryCode", bcat);
			req.setAttribute("authorInfo", authorInfo);
			goTo = "/view/addLecture.jsp";
		} else if ((divs.length > 3) && divs[3].equals("addLecture_process")) { // 강의 추가 진행
			String title = null; String content = null; String vid_title = null; String vid_url = null; String code = null; String summary = null;
			String vid_url2 = null; String code2 = null; String summary2 = null; String vid_title2 = null; Blob img1 = null; Blob img2 = null;
			boolean oneOnly = false;
			File imgd = new File(imgPath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(imgd);
			factory.setSizeThreshold(1024*1024);
			ServletFileUpload sfu = new ServletFileUpload(factory);
			try {
				List<FileItem> files = sfu.parseRequest(req);
				for(FileItem file : files) {
					if(file.isFormField()) {
						switch(file.getFieldName()) {
							case "title": title = file.getString("utf-8"); break;
							case "content": content = file.getString("utf-8"); break;
							case "vid_title": vid_title = file.getString("utf-8"); break;
							case "vid_url": vid_url = file.getString("utf-8"); break;
							case "code": code = file.getString("utf-8"); break;
							case "summary": summary = file.getString("utf-8"); break;
							case "vid_title2": vid_title2 = file.getString("utf-8"); break;
							case "vid_url2": vid_url2 = file.getString("utf-8"); break;
							case "code2": code2 = file.getString("utf-8"); break;
							case "summary2": summary2 = file.getString("utf-8"); break;
						}
						if(file.getFieldName().equals("vid_title2") && (vid_title2 == null || vid_title2.equals(""))) oneOnly = true;
					} else {
						BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] temp = new byte[512];
						while(bis.read(temp) != -1) baos.write(temp);
						byte[] result = baos.toByteArray();
						try {
							switch(file.getFieldName()) {
								case "img" : img1 = new SerialBlob(result); break;
								case "img2" : img2 = new SerialBlob(result); break;
							}
						}catch(SQLException e) {System.out.println("Blob create failed..... check the error : LectureControl");}
						bis.close();
						baos.close();
					}
				}
				long curTime = System.currentTimeMillis();
				java.sql.Date writeDate = new java.sql.Date(curTime); 
				LectureBean bean = null;
				if(oneOnly == true) bean = new LectureBean(-1, title, content, vid_title, vid_url, code, summary, img1, writeDate, null, null, null, null, null);
				else bean = new LectureBean(-1, title, content, vid_title, vid_url, code, summary, img1, writeDate, vid_title2, vid_url2, code2, summary2, img2);
				System.out.println("categoryName : " + categoryName);
				int resRow = lectureService.addLecture(bean, categoryName);
				if(resRow == 1) app.setAttribute("lectureAdd", "true");
				else app.setAttribute("lectureAdd", "false");
				goTo = req.getContextPath() + "/lecture/" + String.valueOf(bcat);
				toDispatch = false;
			} catch(FileUploadException e) {
				System.out.println("File upload Exception : LectureController");
			}
			
			
		} else if ((divs.length > 3) && divs[3].equals("reviseLecture")) { // 강의 수정 페이지 진입
			req.setAttribute("add", "false");
			req.setAttribute("categoryCode", bcat);
			int lectureNum = (int)app.getAttribute("lectureNum");
			app.removeAttribute("lectureNum");
			System.out.println("lectureNumStr -> " + lectureNum);
			
			HashMap<String, Object> lectureResult = lectureService.getLecturesAndOneLecture(categoryName, lectureNum);
			if(lectureResult.get("resultCode").equals("no_data")) { 
				req.setAttribute("resultCode", "noPage");
				req.setAttribute("noPage", "해당 강의를 찾을 수 없습니다.");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				req.setAttribute("lectureNum", 1);
				req.setAttribute("authorInfo", authorInfo);
			} else {
				req.setAttribute("resultCode", "ok");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				req.setAttribute("lectureNum", lectureNum);
				req.setAttribute("authorInfo", authorInfo);
			}
			goTo = "/view/addLecture.jsp";
			
		} else if ((divs.length > 3) && divs[3].equals("reviseLecture_process")) { // 강의 수정 진행
			System.out.println("in process : revise");
			String title = null; String content = null; String vid_title = null; String vid_url = null; String code = null; String summary = null;
			String vid_url2 = null; String code2 = null; String summary2 = null; String vid_title2 = null; Blob img1 = null; Blob img2 = null; String lectureNumStr = null;
			String img1Changedbool = null; boolean img1Changed = false; String img2Changedbool = null; boolean img2Changed = false; String changedFields = null;
			Vector<String> changed = new Vector();
			int[] idxArr = new int[14];
            Object[] objArr = new Object[14];
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
							case "title": if(changed.contains(field)) {title = item.getString("utf-8"); tempidx = 1; target = title;} break;
							case "content": if(changed.contains(field)) {content = item.getString("utf-8"); tempidx = 2; target = content;}  break;
							case "vid_title": if(changed.contains(field)) {vid_title = item.getString("utf-8"); tempidx = 3; target = vid_title;} break;
							case "vid_url": if(changed.contains(field)) {vid_url = item.getString("utf-8"); tempidx = 4; target = vid_url;} break;
							case "code": if(changed.contains(field)) {code = item.getString("utf-8"); tempidx = 5; target = code;} break;
							case "summary": if(changed.contains(field)) {summary = item.getString("utf-8"); tempidx = 6; target = summary;} break;
							case "vid_title2": if(changed.contains(field)) {vid_title2 = item.getString("utf-8"); tempidx = 9; target = vid_title2;} break;
							case "vid_url2": if(changed.contains(field)) {vid_url2 = item.getString("utf-8"); tempidx = 10; target = vid_url2;} break;
							case "code2": if(changed.contains(field)) {code2 = item.getString("utf-8"); tempidx = 11; target = code2;} break;
							case "summary2": if(changed.contains(field)) {summary2 = item.getString("utf-8"); tempidx = 12; target = summary2;} break;
							case "img1Changedbool" : img1Changedbool = item.getString("utf-8"); img1Changed = Boolean.parseBoolean(img1Changedbool); break;
							case "img2Changedbool" : img2Changedbool = item.getString("utf-8"); img2Changed = Boolean.parseBoolean(img2Changedbool); break;
							case "lectureNum" : lectureNumStr = item.getString("utf-8"); break;
							case "changedFields" : changedFields = item.getString("utf-8"); String[] temp = changedFields.split(","); 
								changed = Arrays.stream(temp).collect(Collectors.toCollection(Vector::new)); System.out.println(changed); break;
						}
						if(tempidx != -1) {
							idxArr[tempidx] = 1;
							objArr[tempidx] = target;
						}
					} else {
						if((item.getFieldName().equals("img")&&(img1Changed == true))||(item.getFieldName().equals("img2")&&(img2Changed == true))) {
							BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							byte[] buffer = new byte[512];
							while(bis.read(buffer) != -1) baos.write(buffer);
							byte[] resArr = baos.toByteArray();
							try {
								Blob target = null;
								if(resArr.length != 0) target = new SerialBlob(resArr);
								int tempidx = -1;
								if(item.getFieldName().equals("img")) tempidx = 7;
								else tempidx = 13;
								
								idxArr[tempidx] = 1;
								objArr[tempidx] = target;
							} catch(SQLException e) {System.out.println("Blob create failed : LectureController(reviseLecture_process)");}
						}	
					}
				}
				// 필드 처리 완료. 처리 로직 밑에다가 작성.
				
				int result = lectureService.reviseLecture(idxArr, objArr, categoryName, lectureNumStr);
				if(result == 1) app.setAttribute("lectureRevise", "true");
				else app.setAttribute("lectureRevise", "false");
				goTo = req.getContextPath() + "/lecture/" + String.valueOf(bcat) + "/" + lectureNumStr;
				toDispatch = false;
			} catch(FileUploadException e) {System.out.println("File upload Exception : LectureController(reviseLecture_process)");}
			
		} else if ((divs.length > 3) && divs[3].equals("deleteLecture")) { // 강의 삭제 진행
			System.out.println("delete");
			req.setAttribute("categoryCode", bcat);
			String lectureNum = req.getParameter("lecture_num");
			int result = lectureService.removeLecture(categoryName, lectureNum);
			if(result == 1) System.out.println("삭제 성공");
			goTo = req.getContextPath() + "/lecture/" + bcat;
			toDispatch = false;
		} else if(categoryName == null) { // 1 : 이거와 바로 아래 분기는 url 가지고 조작하면서 장난하는 거 방지용입니다.
			// session은 로그인 특화기능에 사용하려고 양보했습니다. 지금은 앱 애플리케이션 단의 컨텍스트 객체 ServletContext를 이용했습니다. jsp에서는 application이라는 변수로 사용됩니다.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "해당 과목을 찾을 수 없습니다.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else if(lectureService.getLecturesNum(categoryName) == 0 && (uclass == 2||(uclass == 1 && (uid != null && !authorInfo.get("id").equals(uid))))) { // 2 : 해당 과목의 강의 갯수가 하나도 없을 때 여기로 들어옵니다.
			// 이건 사용자나 이 과목을 만든 강의자가 아닐 때 뜨는 거고, 이 과목을 만든 강의자나 관리자는 강의 페이지 작성 페이지로 넘어갑니다.
			context.setAttribute("resultCode", "error");
			context.setAttribute("error_msg", "과목 준비 중입니다.");
			goTo = req.getContextPath() + "/center/intro";
			toDispatch = false;
		} else { // 강의를 보려고 접속할 때
			HashMap<String, Object> lectureResult = lectureService.getLecturesAndOneLecture(categoryName, scat);
			if(lectureResult.get("resultCode").equals("no_data")) { // 3 이것도 url 조작 대비용. scat번호로 들어가는 강의 번호에 해당하는 강의가 없을 때 첫번째 강의를 자동으로 띄웁니다.
				// 
				System.out.println("noPage!");
				req.setAttribute("resultCode", "noPage");
				req.setAttribute("noPage", "해당 강의를 찾을 수 없습니다.");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				req.setAttribute("authorInfo", authorInfo);
				req.setAttribute("comments", comments);
				req.setAttribute("bcat", bcat);
				req.setAttribute("scat", scat);
				req.setAttribute("categoryName", categoryName);
				req.setAttribute("lectureNum", (int)lectureResult.get("beanCode"));
				goTo = "/view/lecture.jsp";
			} else { // 4 : 가장 정상적인 상황. 강의 목록을 찾아 누를 때. 
				System.out.println("ok");
				req.setAttribute("resultCode", "ok");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
				req.setAttribute("authorInfo", authorInfo);
				req.setAttribute("comments", comments);
				req.setAttribute("bcat", bcat);
				req.setAttribute("scat", scat);
				req.setAttribute("categoryName", categoryName);
				
				// 강의 번호. 강의가 없어서 '강의를 추가해주세요' 빈이 담겨있으면 -1 / 정상적으로 들어있으면 1
				req.setAttribute("lectureNum", (int)lectureResult.get("beanCode"));
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
	}
	
	@Override
	public void destroy() {
		System.out.println("LectureController : destroy method activated.");
	}

}
