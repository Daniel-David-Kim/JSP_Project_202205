package section01;

// 서비스. 은행의 종업원이라고 보시면 됩니다. 
public class MembersService {
	private MembersDAO dao;
	public MembersService() {
		this.dao = new MembersDAO();
	}
	public void printAllMembers() {
		dao.allMembersList();
	}
}
