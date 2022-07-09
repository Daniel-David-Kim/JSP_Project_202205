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
	
	public HashMap<String, Object> getOneArticleById(String author_id, String q_num) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		BoardBean bean = null;
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
		) {
			// 주의!! BoardBean을 보면, 첫번째가 depth이다. 계층형 쿼리를 쓰니까 이렇게 되지만,
			String sql = String.format("select * from %s_board where q_num=%s", author_id, q_num);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				count++;
				// 그냥 쿼리를 써서 가져올 때는 depth 부분을 비워야 한다! 안그럼 에러남!
				//							1.q_num		2.p_num			3.m_id			 4.title		 5.content       6.writeDate
				bean = new BoardBean(-1, rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getString(3), rs.getDate(6));
			}
			rs.close();
		} catch(SQLException e) {e.printStackTrace(); System.out.println("SQLException : BoardDAO(getOneArticleById)");}
		result.put("numOfRows", count);
		result.put("article", bean);
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
	
	public int insertNewData(String author_id, String m_id, String title, String content, java.sql.Date curDate) {
		int result = -1;
		int total = getAllCounts(author_id);
		// total + 1 지점에 id를 주어 데이터 삽입
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(String.format("insert into %s_board values(%d, 0, ?, ?, ?, ?)", author_id, total+1));
		) {
			pstmt.setString(1, m_id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setDate(4, curDate);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(insertNewData)");}
		return result;
	}
	
	public int insertReplyData(String author_id, String p_num, String m_id, String title, String content, java.sql.Date curDate) {
		int result = -1;
		int total = getAllCounts(author_id);
		// total + 1 지점에 id를 주어 데이터 삽입
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(String.format("insert into %s_board values(%d, %s, ?, ?, ?, ?)", author_id, total+1, p_num));
		) {
			pstmt.setString(1, m_id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setDate(4, curDate);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(insertReplyData)");}
		return result;
	}
	
	public int updateData(String author_id, String q_num, String title, String content) {
		int result = -1;
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(String.format("update %s_board set title=?, content=? where q_num=%s", author_id, q_num));
		) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(updateData)");}
		return result;
	}
	
	public int makeNullData(String author_id, String q_num) {
		int result = -1;
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(String.format("update %s_board set title='삭제된 글입니다.', content='게시자에 의해 삭제된 글입니다.', m_id='' where q_num=%s", author_id, q_num));
		) {
			result = pstmt.executeUpdate();
		} catch(SQLException e) {System.out.println("SQLException : BoardDAO(makeNullData)");}
		return result;
	}
	
}
