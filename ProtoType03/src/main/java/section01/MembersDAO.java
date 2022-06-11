package section01;

import java.sql.*;
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
			Context enc = (Context)init.lookup("java:/comp/env");
			dataFactory = (DataSource)enc.lookup("jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("MembersDAO : Naming Exception");
		}
	}
	
	
	public void allMembersList() {
		try {
			conn = dataFactory.getConnection();
			stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery("select * from members");
			while(cur.next()) {
				System.out.println(cur.getString(1) + " / " + cur.getString(2) + " / " + cur.getString(3));
			}
			cur.close();
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("MembersDAO : SQL Exception");
		}
	}
	
}

/*
Servers > Tomcat > context.xml�� <context> �±� �� �ϴܿ��ٰ� �ٿ������� �˴ϴ�! 

<Resource 
    	name="jdbc/mysql"
    	auth="Container"
    	type="javax.sql.DataSource"
    	driverClassName="com.mysql.cj.jdbc.Driver"
    	url="jdbc:mysql://localhost:3306/yndcoding?serverTimezone=Asia/Seoul"
    	username="root"
    	password="1234"
    	maxWait="-1"
    	maxActive="50"
    />


*/