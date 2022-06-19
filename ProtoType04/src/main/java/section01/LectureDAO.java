package section01;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class LectureDAO {
	private DataSource dataFactory;
	public LectureDAO() {
		try {
			Context init = new InitialContext();
			Context enc = (Context)init.lookup("java:/comp/env");
			dataFactory = (DataSource)enc.lookup("jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("MembersDAO : Naming Exception");
		}
	}
	
	public Vector<LectureBean> getLecturesOnCategory(String categoryName) {
		Vector<LectureBean> lectures = new Vector<LectureBean>();
		String sql = String.format("select * from %s_TBL ", categoryName);
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()) {
				LectureBean lecture = new LectureBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBlob(8), rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getBlob(14));
				lectures.add(lecture);
			}
		} catch(SQLException e) { System.out.println("LectureDAO : SQL Exception(getLecturesOnCategory)"); }
		return lectures;
	}
	
	public int getLecturesCount(String categoryName) {
		int count = 0;
		String sql = String.format("select count(*) from %s_TBL ", categoryName);
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) { System.out.println("LectureDAO : SQL Exception(getLecturesCount)"); }
		return count;
	}
	
	public int deleteLecture(String categoryName, String lectureNum, int total) {
		int count = 0;
		String sql = String.format("delete from %s_TBL where scat_num=%s", categoryName, lectureNum);
		try (
				Connection conn = dataFactory.getConnection();
				Statement stmt = conn.createStatement();
			) {
				count = stmt.executeUpdate(sql);
				stmt.executeUpdate(String.format("alter table %s_TBL auto_increment=1", categoryName));
				stmt.executeUpdate("set @count = 0");
				stmt.executeUpdate(String.format("update %s_TBL set scat_num = @count := @count + 1", categoryName));
				stmt.executeUpdate(String.format("alter table %s_TBL auto_increment=%d", categoryName, total));
			} catch(SQLException e) { System.out.println("LectureDAO : SQL Exception(deleteLecture)"); }
		return count;
	}
	
	public int insertLecture(LectureBean data, String categoryName) {
		String sql = String.format("insert into %s_TBL (title, content, vid_title, vid_url, code, summary, img, writeDate, vid_title2, vid_url2, code2, summary2, img2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", categoryName);
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, data.getTitle()); pstmt.setString(2, data.getContent()); pstmt.setString(3, data.getVid_title()); pstmt.setString(4, data.getVid_url());
			pstmt.setString(5, data.getCode()); pstmt.setString(6, data.getSummary()); pstmt.setBlob(7, data.getImg()); pstmt.setDate(8, data.getWriteDate());
			pstmt.setString(9, data.getVid_title2()); pstmt.setString(10, data.getVid_url2()); pstmt.setString(11, data.getCode2()); pstmt.setString(12, data.getSummary2());
			pstmt.setBlob(13, data.getImg2());
			count = pstmt.executeUpdate();
		} catch(SQLException e) { System.out.println("LectureDAO : SQL Exception(insertLecture)"); }
		return count;
	}
	
}
