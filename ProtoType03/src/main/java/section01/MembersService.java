package section01;

// ����. ������ �������̶�� ���ø� �˴ϴ�. 
public class MembersService {
	private MembersDAO dao;
	public MembersService() {
		this.dao = new MembersDAO();
	}
	public void printAllMembers() {
		dao.allMembersList();
	}
}
