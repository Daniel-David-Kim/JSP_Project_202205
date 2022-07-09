package section01;

import java.sql.*;

public class BoardBean {
	private int depth; // 0
	private int q_num; // 1
	private int p_num; // 2
	private String title; // 3
	private String content; // 4
	private String m_id; // 5
	private Date writeDate; // 6
	public int getDepth() {return depth;}
	public void setDepth(int depth) {this.depth = depth;}
	public int getQ_num() {return q_num;}
	public void setQ_num(int q_num) {this.q_num = q_num;}
	public int getP_num() {return p_num;}
	public void setP_num(int p_num) {this.p_num = p_num;}
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public Date getWriteDate() {return writeDate;}
	public void setWriteDate(Date writeDate) {this.writeDate = writeDate;}
	public BoardBean(int depth, int q_num, int p_num,  String title, String content, String m_id, Date writeDate) {
		this.depth = depth; this.q_num = q_num; this.p_num = p_num; this.m_id = m_id;
		this.title = title; this.content = content; this.writeDate = writeDate;
	}
}