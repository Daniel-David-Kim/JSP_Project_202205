package section01;

import java.util.HashMap;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		this.dao = new BoardDAO();
	}
	
	public HashMap<String, Object> getAllArticlesByIdHier(String author_id) {
		return dao.getAllArticlesHier(author_id);
	}
	
	public HashMap<String, Object> getAllArticlesById(String author_id) {
		return dao.getAllArticles(author_id);
	}
	
	public HashMap<String, Object> getPager(String author_id, int start, int numbers) {
		int allCount = dao.getAllCounts(author_id);
		HashMap<String, Object> result = dao.getSomeArticlesHier(author_id, start, numbers);
		result.put("AllArticles", allCount);
		System.out.println("articlesCount : " + result.get("resultRows"));
		return result;
	}
	
	public HashMap<String, Object> getOneArticle(String author_id, String q_num) {
		return dao.getOneArticleById(author_id, q_num);
	}
	
	public int insertNewQuestion(String author_id, String m_id, String title, String content, java.sql.Date curDate) {
		return dao.insertNewData(author_id, m_id, title, content, curDate);
	}
	
	public int insertReply(String author_id, String p_num, String m_id, String title, String content, java.sql.Date curDate) {
		return dao.insertReplyData(author_id, p_num, m_id, title, content, curDate);
	}
	
	public int updateQuestion(String author_id, String q_num, String title, String content) {
		return dao.updateData(author_id, q_num, title, content);
	}
	
	public int deleteQuestion(String author_id, String q_num) {
		return dao.makeNullData(author_id, q_num);
	}
}
