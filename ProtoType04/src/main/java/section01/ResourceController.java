package section01;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns={"/getRes/*"})
public class ResourceController extends HttpServlet {
	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		System.out.println("ResourceController : init method activated.");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ResourceController : doGet method activated.");
		doHandle(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ResourceController : doPost method activated.");
		doHandle(req, res);
	}
	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ResourceController : doHandle method activated.");
		res.setContentType("image/jpeg");
		ServletContext app = req.getServletContext();
		BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
		String pathInfo = req.getPathInfo();
		
		if(pathInfo.equals("/lecture/image") || pathInfo.equals("/lecture/image2")) {
			Blob image = null;
			if(pathInfo.equals("/lecture/image")) {
				image = (Blob)app.getAttribute("lectureImg");
				app.removeAttribute("lectureImg");
			} else if(pathInfo.equals("/lecture/image2")) {
				image = (Blob)app.getAttribute("lectureImg2");
				app.removeAttribute("lectureImg2");
			}
			try {
				BufferedInputStream bis = new BufferedInputStream(image.getBinaryStream());
				byte[] buffer = new byte[512];
				while(bis.read(buffer) != -1) bos.write(buffer);
				bos.flush();
				bis.close();
				bos.close();
			} catch(SQLException e) { System.out.println("SQLException : ResourceController : image image2"); }
		} else if(pathInfo.equals("/lecture/profileAuthor")) {
			Blob profile = (Blob)app.getAttribute("profileAuthor");
			try {
				BufferedInputStream bis = new BufferedInputStream(profile.getBinaryStream());
				byte[] buffer = new byte[512];
				while(bis.read(buffer) != -1) bos.write(buffer);
				bos.flush();
				bis.close();
				bos.close();
				app.removeAttribute("profileAuthor");
			} catch(SQLException e) { System.out.println("SQL Exception : ResourceController : profileAuthor"); }
		} else if(pathInfo.equals("/account/profile")) {
			Blob profile = (Blob)app.getAttribute("profile");
			try {
				BufferedInputStream bis = new BufferedInputStream(profile.getBinaryStream());
				byte[] buffer = new byte[512];
				while(bis.read(buffer) != -1) bos.write(buffer);
				bos.flush();
				bis.close();
				bos.close();
				app.removeAttribute("profile");
			} catch(SQLException e) { System.out.println("SQL Exception : ResourceController : /account/profile"); }
		} else if(pathInfo.equals("/comment/profile")) {
			String qs = req.getQueryString();
			String num = qs.split("=")[1];
			System.out.println("num : " + num);
			Blob profile = (Blob)app.getAttribute("profile" + num);
			try {
				BufferedInputStream bis = new BufferedInputStream(profile.getBinaryStream());
				byte[] buffer = new byte[512];
				while(bis.read(buffer) != -1) bos.write(buffer);
				bos.flush();
				bis.close();
				bos.close();
				app.removeAttribute("profile" + num);
			} catch(SQLException e) { System.out.println("SQL Exception : ResourceController : /account/profile"); }
		}
		
	}
	@Override
	public void destroy() {
		System.out.println("ResourceController : destroy method activated.");
	}
}
