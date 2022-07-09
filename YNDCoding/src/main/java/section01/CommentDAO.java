package section01;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class CommentDAO {
	private DataSource dataFactory;
	
	public CommentDAO() {
		try {
			Context init = new InitialContext();
			Context enc = (Context) init.lookup("java:/comp/env");
			dataFactory = (DataSource) enc.lookup("jdbc/mysql");
		} catch (NamingException e) {
			System.out.println("CommentDAO : Naming Exception");
		}
	}
	
	public HashMap<String, Object> getCommentsByScat(String categoryName, int scat) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<CommentBean> lists = new Vector<CommentBean>();
		int count = 0;
		//                                      1       2         3            4             5          6            7
		String sql = String.format("select cc.c_num, cc.m_id, cc.content, cc.writeDate, cc.scat_num, m.m_name, m.m_profile from comment_%s cc inner join members m on cc.m_id=m.m_id where scat_num=? order by writeDate desc", categoryName);
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, scat);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				count++;
				CommentBean bean = new CommentBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getBlob(7));
				lists.add(bean);
			}
			rs.close();
		} catch(SQLException e) {e.printStackTrace(); System.out.println("SQLException : CommentDAO(getAllComments)");}
		result.put("numOfRows", count);
		result.put("comments", lists);
		return result;
	}
	
	public int insertData(String categoryName, String m_id, String content, int scat) {
		int res = -1;
		String sql = String.format("insert into comment_%s (m_id, content, writeDate, scat_num) values (?, ?, current_date(), ?)", categoryName);
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, m_id);
			pstmt.setString(2, content);
			pstmt.setInt(3, scat);
			res = pstmt.executeUpdate();
		} catch(SQLException e) {e.printStackTrace(); System.out.println("SQLException : CommentDAO(insertData)");}
		return res;
	}
	
	public int insertDataIndex(String m_id, String content) { // 
		int res = -1;
		String sql = String.format("insert into comment_intro (m_id, content, writeDate) values (?, ?, current_date())");
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, m_id);
			pstmt.setString(2, content);
			res = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(insertData)");}
		return res;
	}
	
	public int reviseData(String categoryName, int c_num, String content) {
		int res = -1;
		String sql = String.format("update comment_%s set content=? where c_num=%d", categoryName, c_num);
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, content);
			res = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(reviseData)");}
		return res;
	}
	
	public int reviseDataIndex(int c_num, String content) { // 
		int res = -1;
		String sql = String.format("update comment_intro set content=? where c_num=%d", c_num);
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, content);
			res = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(reviseDataIndex)");}
		return res;
	}
	
	public int getCommentsCount(String categoryName) {
		int count = 0;
		String sql = String.format("select count(*) from comment_%s ", categoryName);
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()) count = rs.getInt(1);
		} catch(SQLException e) { System.out.println("CommentDAO : SQL Exception(getCommentsCount)"); }
		return count;
	}
	
	public int getCommentsCountIndex() { //
		int count = 0;
		String sql = String.format("select count(*) from comment_intro");
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		) {
			while(rs.next()) count = rs.getInt(1);
		} catch(SQLException e) { System.out.println("CommentDAO : SQL Exception(getCommentsCount)"); }
		return count;
	}
	
	public int deleteData(String categoryName, int c_num) {
		int res = -1;
		int total = getCommentsCount(categoryName);
		String sql = String.format("delete from comment_%s where c_num=%d", categoryName, c_num);
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement();
		){
			res = pstmt.executeUpdate();
			stmt.executeUpdate(String.format("alter table comment_%s auto_increment=1", categoryName));
			stmt.executeUpdate("set @count = 0");
			stmt.executeUpdate(String.format("update comment_%s set c_num = @count := @count + 1", categoryName));
			stmt.executeUpdate(String.format("alter table comment_%s auto_increment=%d", categoryName, total));
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(reviseData)");}
		return res;
	}
	
	public int deleteDataIndex(int c_num) { //
		int res = -1;
		int total = getCommentsCountIndex();
		String sql = String.format("delete from comment_intro where c_num=%d", c_num);
		try(
				Connection conn = dataFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				Statement stmt = conn.createStatement();
		){
			res = pstmt.executeUpdate();
			stmt.executeUpdate(String.format("alter table comment_intro auto_increment=1"));
			stmt.executeUpdate("set @count = 0");
			stmt.executeUpdate(String.format("update comment_intro set c_num = @count := @count + 1"));
			stmt.executeUpdate(String.format("alter table comment_intro auto_increment=%d", total));
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(deleteDataIndex)");}
		return res;
	}
	
	public HashMap<String, Object> getIntroDatas() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<CommentBean> lists = new Vector<CommentBean>();
		int count = 0;
		//                        1       2         3            4             5          6            7
		String sql = "select cc.c_num, cc.m_id, cc.content, cc.writeDate, cc.scat_num, m.m_name, m.m_profile from comment_intro cc inner join members m on cc.m_id=m.m_id order by writeDate desc";
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				count++;
				CommentBean bean = new CommentBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getString(6), rs.getBlob(7));
				lists.add(bean);
			}
			rs.close();
		} catch(SQLException e) {System.out.println("SQLException : CommentDAO(getAllComments)");}
		result.put("numOfRows", count);
		result.put("comments", lists);
		return result;
	}
	
}
