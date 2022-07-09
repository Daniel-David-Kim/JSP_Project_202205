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
			if(datas.size() > 0) {
				result.put("one", datas.get(0));
				result.put("beanCode", 1);
			} else {
				long curTime = System.currentTimeMillis();
				java.sql.Date curDate = new java.sql.Date(curTime);
				result.put("one", new LectureBean(-1, "강의를 추가해주세요", "강의가 없습니다.\n강의를 추가해주세요.", "", "", "", "", null, curDate, "", "", "", "", null));
				result.put("beanCode", -1);
			}
		} else {
			result.put("resultCode", "ok");
			result.put("all", datas);
			if(datas.size() > 0) {
				result.put("one", datas.get(scat_idx));
				result.put("beanCode", 1);
			} else {
				long curTime = System.currentTimeMillis();
				java.sql.Date curDate = new java.sql.Date(curTime);
				result.put("one", new LectureBean(-1, "강의를 추가해주세요", "강의가 없습니다.\n강의를 추가해주세요.", "", "", "", "", null, curDate, "", "", "", "", null));
				result.put("beanCode", -1);
			}
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