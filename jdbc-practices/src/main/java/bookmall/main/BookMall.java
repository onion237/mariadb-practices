package bookmall.main;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;
import util.SimpleConnectionProvider;

public class BookMall {
	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDao(new SimpleConnectionProvider());
		
		CategoryVo category = null;
		category = new CategoryVo();
		category.setName("IT");
		categoryDao.insert(category);

		category = new CategoryVo();
		category.setName("사회과학");
		categoryDao.insert(category);
		
		category = new CategoryVo();
		category.setName("문학");
		categoryDao.insert(category);
		
	}
}
