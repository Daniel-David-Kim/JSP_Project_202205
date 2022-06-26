package section01;

import java.util.*;

public class LectureService {
	private LectureDAO dao;
	public LectureService() {
		this.dao = new LectureDAO();
	}
	
	public HashMap<String, Object> getLecturesAndOneLecture(String categoryName, int scat) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Vector<LectureBean> datas = dao.getLecturesOnCategory(categoryName);
		int scat_idx = scat-1;
		if(scat_idx > datas.size()) {
			result.put("resultCode", "no_data");
			result.put("all", datas);
			result.put("one", datas.get(0));
		} else {
			result.put("resultCode", "ok");
			result.put("all", datas);
			result.put("one", datas.get(scat_idx));
		}
		return result;
	}
	
	public int getLecturesNum(String categoryName) {
		return dao.getLecturesCount(categoryName);
	}
	
	public int removeLecture(String categoryName, String lectureNum) {
		int total = dao.getLecturesCount(categoryName);
		return dao.deleteLecture(categoryName, lectureNum, total);
	}
	
	public int addLecture(LectureBean bean, String categoryName) {
		return dao.insertLecture(bean, categoryName);
	}
	
	public int reviseLecture(int[] idxArr, Object[] objArr, String categoryName, String lectureNum) {
		return dao.updateData(idxArr, objArr, categoryName, lectureNum);
	}

}