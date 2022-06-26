package section01;

import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;
import javax.naming.*;

// Model. 은행의 처리기계들이라고 보시면 됩니다. 처리기계는 한 번 명령을 주면 하나만 수행하죠? 
public class MembersDAO {
	private DataSource dataFactory;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;

	public MembersDAO() {
		try {
			Context init = new InitialContext();
			Context enc = (Context) init.lookup("java:/comp/env");
			dataFactory = (DataSource) enc.lookup("jdbc/mysql");
		} catch (NamingException e) {
			System.out.println("MembersDAO : Naming Exception");
		}
	}
	
	public HashMap<String, Object> getOneMemberIdName(String m_id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select m_id, m_name, m_profile from members where m_id=?");
		) {
			pstmt.setString(1, m_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.put("id", rs.getString(1));
				result.put("name", rs.getString(2));
				result.put("profile", rs.getBlob(3));
			}
			rs.close();
		} catch(SQLException e) {System.out.println("MembersDAO : SQL Exception (getOneMemberIdName)");}
		return result;
	}

	public void allMembersList() {
		try {
			conn = dataFactory.getConnection();
			stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery("select * from members");
			while (cur.next()) {
				System.out.println(cur.getString(1) + " / " + cur.getString(2) + " / " + cur.getString(3));
			}
			cur.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MembersDAO : SQL Exception");
		}
	}

	public HashMap<String, Object> MemberIP(String id, String pw) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		MemberBean bean = null;
		int count = 0;
		try {
			conn = dataFactory.getConnection();
			stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery(String.format("select * from members where m_id='%s' and m_pw='%s'", id, pw));
			
			while (cur.next()) {
				//if (id1.equals(cur.getString(1)) && pw1.equals(cur.getString(2)))
				bean = new MemberBean(cur.getString(1), cur.getString(2), cur.getString(3), cur.getString(4), cur.getString(5), cur.getString(6), cur.getBlob(7), cur.getInt(8));
				count++;
			}
			cur.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {System.out.println("MembersDAO : SQL Exception");}
		result.put("count", count);
		result.put("member", bean);
		return result;
	}
	
	// 아이디랑 핸드폰 한번에 중복 체크하기 
		// 둘 다 있어야 count 1 리턴
		public int checking(String id, String phone) {
			
			int count=0;
			
			// 아이디 중복체크
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet cur = stmt.executeQuery("select * from members");
				while(cur.next()) {
					if(id.equals(cur.getString(1))) {
						count=1;
					} else {
						System.out.println("일치하는 아이디가 없습니다.");
					}
				}
				cur.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("MembersDAO : SQL Exception");
			}
			
			// 핸드폰 중복체크
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet cur = stmt.executeQuery("select * from members");
				while(cur.next()) {
					if(phone.equals(cur.getString(6))) {
						count=1;
					} else {
						System.out.println("일치하는 핸드폰이 없습니다.");
					}
				}
				cur.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("MembersDAO : SQL Exception");
			}
			return count;
		}
	
	// SQL에 등록하기 
		public void insert(String name, String id, String pw, String question, String answer, String phone) {
			
			try {
				conn = dataFactory.getConnection();
				String sql = null;
				PreparedStatement pstmt = null;
				
				sql = "insert into members values (?,?,?,?,?,?, null, 2);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, question);
				pstmt.setString(5, answer);
				pstmt.setString(6, phone);
				
				pstmt.execute();
				
				System.out.println("insert sucsess... ");
			} catch(SQLException e) {
				System.out.println("insert fail... ");
				e.printStackTrace();
			}
		}
		
		
}

/*
 * Servers > Tomcat > context.xml에 <context> 태그 안 하단에다가 붙여넣으면 됩니다!
 * 
 * <Resource name="jdbc/mysql" auth="Container" type="javax.sql.DataSource"
 * driverClassName="com.mysql.cj.jdbc.Driver"
 * url="jdbc:mysql://localhost:3306/yndcoding?serverTimezone=Asia/Seoul"
 * username="root" password="1234" maxWait="-1" maxActive="50" />
 * 
 * 
 */