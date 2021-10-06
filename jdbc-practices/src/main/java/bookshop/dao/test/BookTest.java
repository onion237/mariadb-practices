package bookshop.dao.test;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;
import util.SimpleConnectionProvider;

public class BookTest {
	public static void main(String[] args) {
		BookDao bookDao = new BookDao(new SimpleConnectionProvider());
		
//		insertTest(bookDao);
		findAllTest(bookDao);
		System.out.println("-------------------------------");
		
		rentTest(bookDao);
		findAllTest(bookDao);
	}

	private static void rentTest(BookDao bookDao) {
		BookVo book = new BookVo();
		book.setNo(1L);
		book.setStatus("대여중");
		bookDao.updateStatus(book);
		
		book = new BookVo();
		book.setNo(3L);
		book.setStatus("대여중");
		bookDao.updateStatus(book);
	}

	private static void findAllTest(BookDao bookDao) {
		bookDao.findAll().forEach(System.out::println);
	}

	private static void insertTest(BookDao bookDao) {
		bookDao.insert(new BookVo("트와일라잇", 1L));
		bookDao.insert(new BookVo("뉴문", 1L));
		bookDao.insert(new BookVo("이클립스", 1L));
		bookDao.insert(new BookVo("브레이킹던", 1L));
		bookDao.insert(new BookVo("아리랑", 2L));
		bookDao.insert(new BookVo("젊은그들", 3L));
		bookDao.insert(new BookVo("아프니깐 청춘이다", 4L));
		bookDao.insert(new BookVo("귀천", 5L));
		bookDao.insert(new BookVo("태백산맥", 2L));
		bookDao.insert(new BookVo("풀하우스", 6L));
	}
}
