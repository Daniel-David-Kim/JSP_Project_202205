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
}
