package section01;

import java.util.*;

// ����. ������ �������̶�� ���ø� �˴ϴ�. 
public class MembersService {
	private MembersDAO dao;

	public MembersService() {
		this.dao = new MembersDAO();
	}

	public void printAllMembers() {
		dao.allMembersList();
	}
	
	public HashMap<String, Object> getAuthorInfo(String m_id) {
		return dao.getOneMemberIdName(m_id);
	}
	
	public HashMap<String, Object> getOneMemberInfo(String m_id) {
		return dao.getOneMember(m_id);
	}
	
	public int reviseMember(int[] idxArr, Object[] objArr, String user_id) {
		return dao.updateData(idxArr, objArr, user_id);
	}
	
	// ���񽺿��� ������ �� �ߺ�üũ�� �ѹ��� ��� ���⼭ ó���ϰ� �����ؼ� ��Ʈ�ѷ��� ��� ����
	// ���̵� �ڵ��� �ߺ� üũ
	// dao.checking �� 1�̸� ���̵� �ڵ����� �ߺ��Ǵ� ���� �ְ�, 0�� ���ϵǸ� �Ѵ� �ߺ��� �ƴ�
	public int signUpCheck(String id, String phone) {
		return dao.checking(id, phone);
	}
	
	// ȸ�� ����ϱ� 
	public int insertSQL(String name, String id, String pw, String question, String answer, String phone) {
		return dao.insert(name, id, pw, question, answer, phone);
	}

	public HashMap<String, Object> MemberIP(String id, String pw) {
		return dao.MemberIP(id, pw);
	}
	
	public boolean isPhoneDuplicate(String phone) {
		int res = dao.phoneCheck(phone);
		if(res == 1) return true;
		else return false;
	}
	
	public boolean isIdDuplicate(String id) {
		int res = dao.idCheck(id);
		if(res == 1) return true;
		else return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// ���̵�� �����ʿ� �� ���� ��������
	public String pw(String id) {MemberBean a = dao.find(id);return a.getM_pw();}
	public String name(String id) {MemberBean a = dao.find(id);return a.getM_name();}
	public String findq(String id) {MemberBean a = dao.find(id);return a.getM_findq();}
	public String finda(String id) {MemberBean a = dao.find(id);return a.getM_finda();}
	public String contact(String id) {MemberBean a = dao.find(id);return a.getM_contact();}
	public String phone1(String contact) {String phone1 = null;StringTokenizer st = new StringTokenizer(contact, "-");phone1 = st.nextToken();return phone1;}
	public String phone2(String contact) {String phone2 = null;StringTokenizer st = new StringTokenizer(contact, "-");st.nextToken();phone2 = st.nextToken();return phone2;}
	public String phone3(String contact) {String phone3 = null;StringTokenizer st = new StringTokenizer(contact, "-");st.nextToken();st.nextToken();phone3 = st.nextToken();return phone3;}
	// �����ϱ�
	public void update(String id, String name, String pw, String question, String answer, String contact) {
		MemberBean a = dao.update(id, name, pw, question, answer, contact);
		////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public int insertMember(String name, String id, String pw, String question, String answer, String phone, int class1) {
	   return dao.insertMember(name, id, pw, question, answer, phone, class1);
	}
	
	public List<MemberBean> SelectMember() {
	   return dao.SelectMembersList();  
	}
	
	public int deletemember(String id) {
	   return dao.DeleteMember(id);
	}
	
	public HashMap<String, Object> findOneMember(String name, String tel) {
		return dao.getOneMemberNoId(name, tel);
	}

	
}
