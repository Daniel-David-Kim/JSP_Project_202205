package section01;

import java.sql.*;

public class MemberBean {
	private String m_id; // 1
	private String m_pw; // 2
	private String m_name; // 3
	private String m_findq; // 4
	private String m_finda; // 5
	private String m_contact; // 6
	private Blob m_profile; // 7
	private int m_class; // 8
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public String getM_pw() {return m_pw;}
	public void setM_pw(String m_pw) {this.m_pw = m_pw;}
	public String getM_name() {return m_name;}
	public void setM_name(String m_name) {this.m_name = m_name;}
	public String getM_findq() {return m_findq;}
	public void setM_findq(String m_findq) {this.m_findq = m_findq;}
	public String getM_finda() {return m_finda;}
	public void setM_finda(String m_finda) {this.m_finda = m_finda;}
	public String getM_contact() {return m_contact;}
	public void setM_contact(String m_contact) {this.m_contact = m_contact;}
	public Blob getM_profile() {return m_profile;}
	public void setM_profile(Blob m_profile) {this.m_profile = m_profile;}
	public int getM_class() {return m_class;}
	public void setM_class(int m_class) {this.m_class = m_class;}
	public MemberBean(String m_id, String m_pw, String m_name, String m_findq, String m_finda, String m_contact, Blob m_profile, int m_class) {
		this.m_id = m_id; this.m_pw = m_pw; this.m_name = m_name; this.m_findq = m_findq;
		this.m_finda = m_finda; this.m_contact = m_contact; this.m_profile = m_profile; this.m_class = m_class;
	}
}
