package section01;

import java.util.*;

public class CommentService {
	private CommentDAO dao;
	public CommentService() {
		this.dao = new CommentDAO();
	}
	
	public HashMap<String, Object> getCommentsByScat(String categoryName, int scat) {
		return dao.getCommentsByScat(categoryName, scat);
	}
	
	public int insertComment(String categoryName, String m_id, String content, int scat) {
		return dao.insertData(categoryName, m_id, content, scat);
	}
	
	public int reviseComment(String categoryName, int c_num, String content) {
		return dao.reviseData(categoryName, c_num, content);
	}
	
	public int deleteComment(String categoryName, int c_num) {
		return dao.deleteData(categoryName, c_num);
	}
	
	public HashMap<String, Object> getCommentsIntro() {
		return dao.getIntroDatas();
	}
	
	public int insertCommentIndex(String m_id, String content) {
		return dao.insertDataIndex(m_id, content);
	}
	
	public int reviseCommentIndex(int c_num, String content) {
		return dao.reviseDataIndex(c_num, content);
	}
	
	public int deleteCommentIndex(int c_num) {
		return dao.deleteDataIndex(c_num);
	}

}