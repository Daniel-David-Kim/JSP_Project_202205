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
	
}
