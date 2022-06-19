package section01;

import java.sql.*;
import java.util.Vector;

import javax.servlet.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;
import javax.naming.*;

// Model. ������ ó�������̶�� ���ø� �˴ϴ�. ó������ �� �� ����� �ָ� �ϳ��� ��������? 
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

	public int MemberIP(String id, String pw) {
		String id1 = id;
		String pw1 = pw;
		int count = 0;

		try {
			conn = dataFactory.getConnection();
			stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery("select * from members");

			while (cur.next()) {
				if (id1.equals(cur.getString(1)) && pw1.equals(cur.getString(2)))
					count = 1;

			}
			System.out.println(3);
			cur.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MembersDAO : SQL Exception");
		}
		return count;

	}
	
	// ���̵�� �ڵ��� �ѹ��� �ߺ� üũ�ϱ� 
		// �� �� �־�� count 1 ����
		public int checking(String id, String phone) {
			
			int count=0;
			
			// ���̵� �ߺ�üũ
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet cur = stmt.executeQuery("select * from members");
				while(cur.next()) {
					if(id.equals(cur.getString(1))) {
						count=1;
					} else {
						System.out.println("��ġ�ϴ� ���̵� �����ϴ�.");
					}
				}
				cur.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("MembersDAO : SQL Exception");
			}
			
			// �ڵ��� �ߺ�üũ
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet cur = stmt.executeQuery("select * from members");
				while(cur.next()) {
					if(phone.equals(cur.getString(6))) {
						count=1;
					} else {
						System.out.println("��ġ�ϴ� �ڵ����� �����ϴ�.");
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
	
	// SQL�� ����ϱ� 
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
 * Servers > Tomcat > context.xml�� <context> �±� �� �ϴܿ��ٰ� �ٿ������� �˴ϴ�!
 * 
 * <Resource name="jdbc/mysql" auth="Container" type="javax.sql.DataSource"
 * driverClassName="com.mysql.cj.jdbc.Driver"
 * url="jdbc:mysql://localhost:3306/yndcoding?serverTimezone=Asia/Seoul"
 * username="root" password="1234" maxWait="-1" maxActive="50" />
 * 
 * 
 */