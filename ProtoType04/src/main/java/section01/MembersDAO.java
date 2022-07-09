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
	
	public int updateData(int[] idxArr, Object[] objArr, String user_id) { // 회원 업데이트는 무조건 여기서 한다!!!!!!!  여기서 테이블 처리 하면 된다!
        String[] labels = {"m_id", "m_pw", "m_name", "m_findq", "m_finda", "m_contact", "m_profile", "m_class"};
        //					   1      2       3           4         5            6          7            8         
        int res = -1;
        Vector<Integer> updatedIdx = new Vector<Integer>();
        StringBuffer sql = new StringBuffer("update members set ");
        for(int i = 0; i < idxArr.length; i++) {
            if(idxArr[i] != -1) {
                sql.append(labels[i] + "=?");
                sql.append(", ");
                updatedIdx.add(i + 1); // pstmt용 : 원소가 1부터 시작
                if(i == 7) { // 클래스가 변경되나 확인합니다. 강의자로 변경되면 강의자 게시판 테이블과 스토어드 함수를 만들어주고, 회원으로 변경되면 강의자 게시판 테이블과 스토어드 함수를 삭제합니다.
                	int changed = (int)objArr[i];
                	try(
                		Connection conn = dataFactory.getConnection();
                		Statement stmt = conn.createStatement();
                	) {
                		stmt.executeUpdate("SET GLOBAL log_bin_trust_function_creators = 1;");
    	            	if(changed == 0 || changed == 1) { // 0 또는 1로 변경 : 강의자 게시판 테이블과 스토어드 함수를 만들어줍니다 : 스토어드 함수 생성! 게시판 테이블 생성!
    	            		// 강의자 게시판 테이블 생성
    	            		stmt.executeUpdate(String.format("create table if not exists %s_board("
    	            				+ "	q_num bigint not null primary key,"
    	            				+ " p_num bigint not null default 0,"
    	            				+ " m_id varchar(30) not null,"
    	            				+ " title text not null,"
    	            				+ " content text not null,"
    	            				+ " writeDate date not null default(current_date())"
    	            				+ ");", user_id));
    	            		// 강의자용 스토어드 함수 생성
    	            		stmt.executeUpdate(String.format("drop function if exists %s_getHierboard", user_id));
    	            		stmt.executeUpdate(String.format(""
    							            				+ "create function %s_getHierboard() returns int"
    							            				+" begin"
    							            				+" declare v_no int;"
    							            				+" declare v_parent int;"
    							            				+" set v_no = -1;"
    							            				+" set v_parent = @num;"
    							            				+" while true do"
    							            				+" select min(q_num) into @num from %s_board where p_num=v_parent and q_num > v_no;"
    							            				+" if (@num is not null) or (v_parent = @init) then"
    							            				+" set @depth = @depth + 1;"
    							            				+" return @num;"
    							            				+" end if;"
    							            				+" set @depth = @depth - 1;"
    							            				+" select q_num, p_num into v_no, v_parent from %s_board where q_num=v_parent;"
    							            				+" end while;"
    							            				+" end"
    							            				+"", user_id, user_id, user_id));
    	            	} else { // 2로 변경: 강의자 게시판 테이블과 스토어드 함수를 삭제합니다. : 스토어드 함수 삭제! 강의자 테이블 삭제!
    	            		stmt.executeUpdate(String.format("drop function if exists %s_getHierboard", user_id));
    	            		stmt.executeUpdate(String.format("drop table if exists %s_board;", user_id));
    	            	}
                	} catch(SQLException e) {e.printStackTrace(); System.out.println("스토어드 함수, 테이블 생성과 삭제에서 문제 발생!");}
                }
            }
            
        }
        String sqlStr = sql.substring(0, sql.length()-2);
        sqlStr += String.format(" where m_id='%s'", user_id);
        System.out.println("sql : " + sqlStr);
        try(
        	Connection conn = dataFactory.getConnection();
    		PreparedStatement pstmt = conn.prepareStatement(sqlStr.toString());	
        ) {
	        for(int j=1; j <= updatedIdx.size(); j++) {
	            int targetIdx = updatedIdx.get(j-1);
	            if(targetIdx == 7) pstmt.setBlob(j, (Blob)objArr[targetIdx-1]);
	            else if(targetIdx == 8) pstmt.setInt(j, (int)objArr[targetIdx-1]);
	            else pstmt.setString(j, (String)objArr[targetIdx-1]);  
	        }
	        res = pstmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); System.out.println("LectureDAO : SQL Exception(updateData)"); }
        return res;
        //return 1;
    }
	
	public HashMap<String, Object> getOneMember(String m_id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		MemberBean bean = null;
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from members where m_id=?");
		) {
			pstmt.setString(1, m_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bean = new MemberBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBlob(7), rs.getInt(8));
				count++;
			}
			rs.close();
		} catch(SQLException e) {System.out.println("MembersDAO : SQL Exception (getOneMemberIdName)");}
		result.put("count", count);
		result.put("memberInfo", bean);
		return result;
	}
	
	public HashMap<String, Object> getOneMemberNoId(String name, String tel) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		MemberBean bean = null;
		int count = 0;
		try(
			Connection conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from members where m_name=? and m_contact=?");
		) {
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bean = new MemberBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBlob(7), rs.getInt(8));
				count++;
			}
			rs.close();
		} catch(SQLException e) {System.out.println("MembersDAO : SQL Exception (getOneMemberIdName)");}
		result.put("count", count);
		result.put("memberInfo", bean);
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
	
	public int phoneCheck(String phone) {
		int res = 0;
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery("select * from members where m_contact='" + phone + "'");		
		) {
			while (cur.next()) res++;
		} catch (SQLException e) {System.out.println("MembersDAO : SQL Exception(phoneCheck)");}
		return res;
	}
	
	public int idCheck(String id) {
		int res = 0;
		try (
			Connection conn = dataFactory.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet cur = stmt.executeQuery("select * from members where m_id='" + id + "'");		
		) {
			while (cur.next()) res++;
		} catch (SQLException e) {System.out.println("MembersDAO : SQL Exception(idCheck)");}
		return res;
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
		public int insert(String name, String id, String pw, String question, String answer, String phone) {
			int res = -1;
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
				
				res = pstmt.executeUpdate();
				
			} catch(SQLException e) {
				System.out.println("insert fail... ");
				e.printStackTrace();
			}
			return res;
		}
		
		// 아이디로 프로필에 쓸 정보 가져오기
		MemberBean bean = new MemberBean(null, null, null, null, null, null, null, 2);
		public MemberBean find(String id) {
			
			try {
				conn = dataFactory.getConnection();
				stmt = conn.createStatement();
				ResultSet cur = stmt.executeQuery("select * from members");
				while(cur.next()) {
					if(id.equals(cur.getString(1))) {
						
						bean.setM_pw(cur.getString(2));
						bean.setM_name(cur.getString(3));
						bean.setM_findq(cur.getString(4));
						bean.setM_finda(cur.getString(5));
						bean.setM_contact(cur.getString(6));
						
					}
				}
				cur.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("MembersDAO : SQL Exception");
			}
			return bean;
		}
			
		////////////////////////////////////////////////////////////////////////
		// 프로필에딧 수정하기 / save 버튼
		MemberBean bean1 = new MemberBean(null, null, null, null, null, null, null, 2);
		public MemberBean update(String id, String name, String pw, String question, String answer, String phone) {
			
			try {
				conn = dataFactory.getConnection();
				String sql = null;
				PreparedStatement pstmt = null;
				
				sql = "update members set m_pw = ?, m_name = ?, m_findq = ?, m_finda = ?, m_contact = ? where m_id = '" + id + "';";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pw);
				pstmt.setString(2, name);
				pstmt.setString(3, question);
				pstmt.setString(4, answer);
				pstmt.setString(5, phone);
				
				pstmt.execute();
				
				System.out.println("update sucsess... ");
			} catch(SQLException e) {
				System.out.println("update fail... ");
				e.printStackTrace();
			}
			return bean;
		}
		////////////////////////////////////////////////////////////////////////
		
		public int insertMember(String name, String id, String pw, String question, String answer, String phone, int class1) {
			int res = -1;
		    try {
		       conn = dataFactory.getConnection();
		       String sql = null;
		       PreparedStatement pstmt = null;

		       sql = "insert into members values (?,?,?,?,?,?, null, ?);";
		       pstmt = conn.prepareStatement(sql);
		       pstmt.setString(1, id);
		       pstmt.setString(2, pw);
		       pstmt.setString(3, name);
		       pstmt.setString(4, question);
		       pstmt.setString(5, answer);
		       pstmt.setString(6, phone);
		       pstmt.setInt(7, class1);

		       res = pstmt.executeUpdate();
		       System.out.println("insert sucsess... ");
			} catch(SQLException e) {
				System.out.println("insert fail... ");
				e.printStackTrace();
			}
		    return res;
		}
		
		public List<MemberBean> SelectMembersList() {
		      List<MemberBean> list = new ArrayList<>();
		      try {
		         conn = dataFactory.getConnection();
		         stmt = conn.createStatement();
		         ResultSet cur = stmt.executeQuery("select * from members");
		         while (cur.next()) {

		            String mid = cur.getString(1);
		            String mname = cur.getString(3);
		            int mclass = cur.getInt(8);
		            String mphone = cur.getString(6);
		            String mpw = cur.getString(2);
		            String mpwq = cur.getString(4);
		            String mpwa = cur.getString(5);
		            MemberBean member = new MemberBean(mid, mpw, mname, mpwq, mpwa, mphone, null, mclass);
		            list.add(member);
		         }
		         cur.close();
		         stmt.close();
		         conn.close();
		      } catch (SQLException e) {
		         e.printStackTrace();
		         System.out.println("MembersDAO : SQL Exception");
		      }
		      return list;
		   }
		
		public int DeleteMember(String id) {
			int res = -1;
		      try {
		         Connection conn = dataFactory.getConnection();
		         String sql = null;
		         PreparedStatement pstmt = null;
		            
		         sql = "delete from members where m_id=?";
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, id);
		         
		         res = pstmt.executeUpdate();
		      } catch (SQLException e) {
		         System.out.println("MembersDAO : SQL Exception");
		      }
		     return res;
		      
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