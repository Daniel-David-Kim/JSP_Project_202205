package section01;

import java.sql.*;
import java.util.*;
import java.text.*;
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
	
	// 수정에도 본문 로직 수정해주어ㅑ 함! \n이나 \r, ' 있으면 나중에 수정이 안됨.
	public int updateData(int[] idxArr, Object[] objArr, String categoryName, String lectureNum) {
        String[] labels = {"scat_num", "title", "content", "vid_title", "vid_url", "code", "summary", "img", "writeDate", "vid_title2", "vid_url2", "code2", "summary2", "img2"};
        //					   1          2         3           4           5        6         7       8          9            10            11        12        13        14
        int res = -1;
        Vector<Integer> updatedIdx = new Vector<Integer>();
        StringBuffer sql = new StringBuffer(String.format("update %s_TBL set ", categoryName));
        for(int i = 0; i < idxArr.length; i++) {
            if(idxArr[i] != -1) {
                sql.append(labels[i] + "=?");
                sql.append(", ");
                updatedIdx.add(i + 1); // pstmt용 : 원소가 1부터 시작
            }
        }
        String sqlStr = sql.substring(0, sql.length()-2);
        sqlStr += String.format(" where scat_num=%s", lectureNum);
        try(
        	Connection conn = dataFactory.getConnection();
    		PreparedStatement pstmt = conn.prepareStatement(sqlStr.toString());	
        ) {
	        for(int j=1; j <= updatedIdx.size(); j++) {
	            int targetIdx = updatedIdx.get(j-1);
	            if(targetIdx == 8 || targetIdx == 14) pstmt.setBlob(j, (Blob)objArr[targetIdx-1]);
	            else if(targetIdx == 1) pstmt.setInt(j, (int)objArr[targetIdx-1]);
	            else if(targetIdx == 9) pstmt.setDate(j, (java.sql.Date)objArr[targetIdx-1]);
	            else {
	            	switch(labels[targetIdx-1]) {
		            	case "content" : System.out.println("!!!!!!!!!!!!!!!!content update!!!!!!!!!!!!!!!!!!!!!"); objArr[targetIdx-1] = ((String)objArr[targetIdx-1]).replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""); break;
		            	case "code" : System.out.println("!!!!!!!!!!!!!!!!code update!!!!!!!!!!!!!!!!!!!!!!!"); objArr[targetIdx-1] = ((String)objArr[targetIdx-1]).replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""); break;
		            	case "summary" : System.out.println("!!!!!!!!!!!!!!!!summary update!!!!!!!!!!!!!!!!!!!!!!!"); objArr[targetIdx-1] = ((String)objArr[targetIdx-1]).replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""); break;
		            	case "code2" : System.out.println("!!!!!!!!!!!!!!!!code2 update!!!!!!!!!!!!!!!!!!!!!!!"); objArr[targetIdx-1] = ((String)objArr[targetIdx-1]).replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""); break;
		            	case "summary2" : System.out.println("!!!!!!!!!!!!!!!!!!!!summary2 update!!!!!!!!!!!!!!!!!"); objArr[targetIdx-1] = ((String)objArr[targetIdx-1]).replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""); break;
	            	}
	            	pstmt.setString(j, (String)objArr[targetIdx-1]);  
	            }
	        }
	        res = pstmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); System.out.println("LectureDAO : SQL Exception(updateData)"); }
        return res;
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
	
	// 내용 안에 \n이나 \r, ' 있으면 나중에 수정이 안됨. 여기서 처리해서 들어가야 함.
	public int insertLecture(LectureBean data, String categoryName) {
		String sql = String.format("insert into %s_TBL (title, content, vid_title, vid_url, code, summary, img, writeDate, vid_title2, vid_url2, code2, summary2, img2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", categoryName);
		int count = 0;
		// 여기서 처리!
		if(data.getContent() != null) data.setContent(data.getContent().replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""));
		if(data.getSummary() != null) data.setSummary(data.getSummary().replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""));
		if(data.getCode() != null) data.setCode(data.getCode().replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""));
		if(data.getSummary2() != null) data.setSummary2(data.getSummary2().replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""));
		if(data.getCode2() != null) data.setCode2(data.getCode2().replaceAll("[\n\r]", "<br>").replaceAll("[\']", "\""));
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
