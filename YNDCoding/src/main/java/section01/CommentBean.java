package section01;

import java.sql.*;
import java.sql.Date.*;

public class CommentBean {
	private int c_num;
	private String m_id;
	private String content;
	private Date writeDate;
	private int scat_num;
	// 댓글은 회원 테이블과 조인해서 사용하는 경우가 대부분이므로 회원 이름과 프로필 사진도 같이 가져옵니다.
	private String m_name;
	private Blob m_profile;
	
	public int getC_num() {return c_num;}
	public void setC_num(int c_num) {this.c_num = c_num;}
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public Date getWriteDate() {return writeDate;}
	public void setWriteDate(Date writeDate) {this.writeDate = writeDate;}
	public int getScat_num() {return scat_num;}
	public void setScat_num(int scat_num) {this.scat_num = scat_num;}
	public String getM_name() {return m_name;}
	public void setM_name(String m_name) {this.m_name = m_name;}
	public Blob getM_profile() {return m_profile;}
	public void setM_profile(Blob m_profile) {this.m_profile = m_profile;}
	public CommentBean(int c_num, String m_id, String content, Date writeDate, int scat_num) {this.c_num = c_num; this.m_id = m_id; this.content = content; this.writeDate = writeDate; this.scat_num = scat_num;}
	public CommentBean(int c_num, String m_id, String content, Date writeDate, int scat_num, String m_name, Blob m_profile) {
		this.c_num = c_num; this.m_id = m_id; this.content = content; this.writeDate = writeDate;
		this.scat_num = scat_num; this.m_name = m_name; this.m_profile = m_profile;
	}
	
}
