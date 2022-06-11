package section01;

import java.util.*;

public class MenusService {
	private MenusDAO dao;
	public MenusService() {
		dao = new MenusDAO();
	}
	
	public HashMap<String, Vector<MenusBean>> getSplitedCategory() {
		HashMap<String, Vector<MenusBean>> result = new HashMap<String, Vector<MenusBean>>();
		result.put("cat1001", dao.getCategoryMenus(1001));
		result.put("cat1002", dao.getCategoryMenus(1002));
		result.put("cat1003", dao.getCategoryMenus(1003));
		result.put("cat1004", dao.getCategoryMenus(1004));
		result.put("cat1005", dao.getCategoryMenus(1005));
		result.put("cat1006", dao.getCategoryMenus(1006));
		return result;
	}
	
	public String getCategoryName(int bcat) {
		MenusBean target = dao.getOneCategory(bcat);
		if(target != null) return target.getMenu_name();
		else return null;
	}
}
