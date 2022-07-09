package section01;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class MenusDAO {
	private DataSource dataFactory;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	public MenusDAO() {
		try {
			Context init = new InitialContext();
			Context enc = (Context)init.lookup("java:/comp/env");
			dataFactory = (DataSource)enc.lookup("jdbc/mysql");
		} catch(NamingException e) {
			System.out.println("MembersDAO : Naming Exception");
		}
	}
	
	public Vector<MenusBean> getCategoryMenus(int num) {
		String sql = "select * from menus where menu_catnum=";
		Vector<MenusBean> result = new Vector<MenusBean>();
		if(num < 1001 || num > 1006) return null;
		else {
			sql += num + " order by menu_snum asc";
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MenusBean bean = new MenusBean(rs.getString(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5));
					result.add(bean);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch(SQLException e) {
				System.out.println("MenusDAO : SQL Exception(getCategoryMenus)");
			}
			return result;
		}
	}
	
	public MenusBean getOneCategory(int bcat) {
		MenusBean result = null;
		String sql = "select * from menus where menu_snum=" + bcat;
		try (
			Connection conn2 = dataFactory.getConnection();
			Statement stmt2 = conn2.createStatement();
			ResultSet rs = stmt2.executeQuery(sql);
		) {
			while(rs.next()) {
				result = new MenusBean(rs.getString(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5));
			}
		} catch(SQLException e) {
			System.out.println("MenusDAO : SQL Exception(getOneCategory)");
		}
		return result;
	}
	
	public int getMaxCat() {
		int result = -1;
		String sql = "select max(menu_snum) from menus";
		try (
			Connection conn2 = dataFactory.getConnection();
			Statement stmt2 = conn2.createStatement();
			ResultSet rs = stmt2.executeQuery(sql);
		) {
			while(rs.next()) result = rs.getInt(1);
		} catch(SQLException e) {System.out.println("MenusDAO : SQL Exception(getMaxCat)");}
		return result;
	}
	
	public int insertMenu(String subject, String bigcat, String author) {
		int total = getMaxCat() + 1;
		int res = -1;
		String sql = String.format("insert into menus values (?, %s, %s, ?, 0)", bigcat, total);
		try (
			Connection conn2 = dataFactory.getConnection();
			PreparedStatement pstmt2 = conn2.prepareStatement(sql);
			Statement stmt2 = conn2.createStatement();
		) {
			pstmt2.setString(1, subject);
			pstmt2.setString(2, author);
			res = pstmt2.executeUpdate();
			String sql2 = String.format("create table %s_TBL("
					+ "	scat_num int not null auto_increment,"
					+ " title text not null,"
					+ " content text null,"
					+ " vid_title varchar(50) not null,"
					+ " vid_url text not null,"
					+ " code text null,"
					+ " summary text null,"
					+ " img mediumblob null,"
					+ " writeDate date default (current_date()),"
					+ " vid_title2 varchar(50) null,"
					+ " vid_url2 text null,"
					+ " code2 text null,"
					+ " summary2 text null,"
					+ " img2 mediumblob null,"
					+ " primary key(scat_num)"
					+ ");", subject);
			stmt2.executeUpdate(sql2);
			String sql3 = String.format("create table comment_%s("
					+ "	c_num bigint not null primary key auto_increment,"
					+ " m_id varchar(30) not null,"
					+ " content text not null,"
					+ " writeDate date not null default (current_date()),"
					+ " scat_num int not null,"
					+ " foreign key(scat_num) references %s_TBL(scat_num) on delete cascade on update cascade"
					+ ");", subject, subject);
			stmt2.executeUpdate(sql3);
		} catch(SQLException e) {e.printStackTrace(); System.out.println("MenusDAO : SQL Exception(insertMenu)");}
		return res;
	}
	
	public int deleteMenu(String menu_name) {
		int res = -1;
		String sql = "delete from menus where menu_name=?";
		try (
			Connection conn2 = dataFactory.getConnection();
			PreparedStatement pstmt2 = conn2.prepareStatement(sql);
			Statement stmt2 = conn2.createStatement();
		) {
			pstmt2.setString(1, menu_name);
			res = pstmt2.executeUpdate();
			String sql2 = String.format("drop table comment_%s", menu_name);
			stmt2.executeUpdate(sql2);
			String sql3 = String.format("drop table %s_TBL", menu_name);
			stmt2.executeUpdate(sql3);
		} catch(SQLException e) {e.printStackTrace(); System.out.println("MenusDAO : SQL Exception(insertMenu)");}
		return res;
	}
	
}
