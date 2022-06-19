package section01;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
		ServletContext app = req.getServletContext();
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
		if((divs.length > 3) && divs[3].equals("addLecture")) {
			req.setAttribute("add", "true");
			req.setAttribute("categoryCode", bcat);
			goTo = "/view/addLecture.jsp";
		} else if ((divs.length > 3) && divs[3].equals("addLecture_process")) {
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
			
			
		} else if ((divs.length > 3) && divs[3].equals("reviseLecture")) {
			
			
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
			} else {
				req.setAttribute("resultCode", "ok");
				req.setAttribute("categoryCode", bcat);
				req.setAttribute("lectureResult", lectureResult);
				req.setAttribute("menus", menus);
			}
			goTo = "/view/addLecture.jsp";
			
			
		} else if ((divs.length > 3) && divs[3].equals("deleteLecture")) {
			System.out.println("delete");
			req.setAttribute("categoryCode", bcat);
			String lectureNum = req.getParameter("lecture_num");
			int result = lectureService.removeLecture(categoryName, lectureNum);
			if(result == 1) System.out.println("삭제 성공");
			goTo = req.getContextPath() + "/lecture/" + bcat;
			toDispatch = false;
		} else if(categoryName == null) { // 1
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
	}
	
	@Override
	public void destroy() {
		System.out.println("LectureController : destroy method activated.");
	}

}
