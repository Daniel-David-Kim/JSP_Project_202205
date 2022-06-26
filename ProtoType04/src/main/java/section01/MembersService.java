package section01;

import java.util.*;

// 서비스. 은행의 종업원이라고 보시면 됩니다. 
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
	
	// 서비스에서 복잡한 거 중복체크들 한번에 묶어서 여기서 처리하고 리턴해서 컨트롤러에 결과 띄우기
	// 아이디 핸드폰 중복 체크
	// dao.checking 이 1이면 아이디나 핸드폰이 중복되는 것이 있고, 0이 리턴되면 둘다 중복이 아님
	public int signUpCheck(String id, String phone) {
		return dao.checking(id, phone);
	}
	
	// 회원 등록하기 
	public void insertSQL(String name, String id, String pw, String question, String answer, String phone) {
		dao.insert(name, id, pw, question, answer, phone);
	}

	public HashMap<String, Object> MemberIP(String id, String pw) {
		return dao.MemberIP(id, pw);
	}
}
