package bookmall.dao.test;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;
import util.SimpleConnectionProvider;

public class BookDaoTest {
	static BookDao bookDao;
	public static void main(String[] args) {
		bookDao = new BookDao(new SimpleConnectionProvider());
		
		insertTest();
		findAllTest();
	}
	private static void findAllTest() {
		bookDao.findAll().forEach(System.out::println);;
	}
	private static void insertTest() {
		BookVo book = null;
		book = new BookVo();
		book.setTitle("토비의 스프링");
		book.setCategoryNo(1L);
		book.setPrice(38000);
		bookDao.insert(book);
		
		book = new BookVo();
		book.setTitle("정의란 무엇인가");
		book.setCategoryNo(2L);
		book.setPrice(24000);
		bookDao.insert(book);
		
		book = new BookVo();
		book.setTitle("트와일라잇");
		book.setCategoryNo(3L);
		book.setPrice(14000);
		bookDao.insert(book);
	}
}
