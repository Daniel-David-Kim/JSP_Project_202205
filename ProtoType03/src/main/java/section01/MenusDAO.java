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
	
	
}
