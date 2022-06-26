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
	
	// ���񽺿��� ������ �� �ߺ�üũ�� �ѹ��� ��� ���⼭ ó���ϰ� �����ؼ� ��Ʈ�ѷ��� ��� ����
	// ���̵� �ڵ��� �ߺ� üũ
	// dao.checking �� 1�̸� ���̵� �ڵ����� �ߺ��Ǵ� ���� �ְ�, 0�� ���ϵǸ� �Ѵ� �ߺ��� �ƴ�
	public int signUpCheck(String id, String phone) {
		return dao.checking(id, phone);
	}
	
	// ȸ�� ����ϱ� 
	public void insertSQL(String name, String id, String pw, String question, String answer, String phone) {
		dao.insert(name, id, pw, question, answer, phone);
	}

	public HashMap<String, Object> MemberIP(String id, String pw) {
		return dao.MemberIP(id, pw);
	}
}
