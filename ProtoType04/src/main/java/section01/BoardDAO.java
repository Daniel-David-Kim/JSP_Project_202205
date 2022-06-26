package section01;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			Context enc = (Context) init.lookup("java:/comp/env");
			dataFactory = (DataSource) enc.lookup("jdbc/mysql");
		} catch (NamingException e) {
			System.out.println("MembersDAO : Naming Exception");
		}
	}
	
	public HashMap<String, Object> getAllArticles(String author_id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<BoardBean> lists = new Vector<BoardBean>();
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			ResultSet rs = stmt.executeQuery(String.format("select * from %s_board", author_id));
			while(rs.next()) {
				BoardBean bean = new BoardBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
				lists.add(bean);
				count++;
			}
			rs.close();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(getAllArticles)");}
		result.put("resultRows", count);
		result.put("articles", lists);
		return result;
	}
	
	public HashMap<String, Object> getAllArticlesHier(String author_id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<BoardBean> lists = new Vector<BoardBean>();
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			String sql = String.format("select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate "
										+ "from (select getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join %s_board) hier "
										+ "left outer join %s_board tb on tb.q_num=hier.hid;", author_id, author_id);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardBean bean = new BoardBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
				lists.add(bean);
				count++;
			}
			rs.close();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(getAllArticlesHier)");}
		result.put("resultRows", count);
		result.put("articles", lists);
		return result;
	}
	
	public HashMap<String, Object> getSomeArticlesHier(String author_id, int start, int numbers) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<BoardBean> lists = new Vector<BoardBean>();
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			) {
				String sql = String.format("select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate "
											+ "from (select %s_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join %s_board) hier "
											+ "left outer join %s_board tb on tb.q_num=hier.hid limit %d, %d;", author_id, author_id, author_id, start-1, numbers);
				System.out.println("dao : query : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					BoardBean bean = new BoardBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
					lists.add(bean);
					count++;
				}
				rs.close();
			} catch(SQLException e) {e.printStackTrace(); System.out.println("SQLException : BoardDAO(getSomeArticlesHier)");}
		result.put("resultRows", count);
		System.out.println("dao : count : " + count);
		result.put("currentPage", (int)((start - (start % 10))/10.0) + 1);
		result.put("articles", lists);
		return result;
	}
	
	public int getAllCounts(String author_id) {
		int result = -1;
		try(
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			ResultSet rs = stmt.executeQuery(String.format("select count(*) from %s_board", author_id));
			while(rs.next()) result = rs.getInt(1);
			rs.close();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(getAllCounts)");}
		return result;
	}
}
