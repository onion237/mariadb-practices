package bookmall.dao.test;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;
import util.SimpleConnectionProvider;

public class CategoryDaoTest {
	static CategoryDao categoryDao;
	public static void main(String[] args) {
		categoryDao = new CategoryDao(new SimpleConnectionProvider());
		
		insertTest();
		findAllTest();
	}
	private static void findAllTest() {
		categoryDao.findAll().forEach(System.out::println);;
	}
	private static void insertTest() {
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
