package section01;

public class MenusBean {
	private String menu_name;
	private long menu_catnum;
	private long menu_snum;
	private String m_id;
	private long p_no;
	
	public String getMenu_name() {return menu_name;}
	public void setMenu_name(String menu_name) {this.menu_name = menu_name;}
	public long getMenu_catnum() {return menu_catnum;}
	public void setMenu_catnum(long menu_catnum) {this.menu_catnum = menu_catnum;}
	public long getMenu_snum() {return menu_snum;}
	public void setMenu_snum(long menu_snum) {this.menu_snum = menu_snum;}
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public long getP_no() {return p_no;}
	public void setP_no(long p_no) {this.p_no = p_no;}
	
	public MenusBean(String menu_name, long menu_catnum, long menu_snum, String m_id, long p_no) {
		this.menu_name = menu_name; this.menu_catnum = menu_catnum; this.menu_snum = menu_snum;
		this.m_id = m_id; this.p_no = p_no;
	}
}
