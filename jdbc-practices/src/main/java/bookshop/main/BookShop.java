package bookshop.main;

import java.util.Scanner;

import bookshop.dao.AuthorDao;
import bookshop.dao.BookDao;
import bookshop.vo.BookVo;
import util.ConnectionProvider;
import util.SimpleConnectionProvider;

public class BookShop {
	static BookDao bookDao;
	static AuthorDao authorDao;
	public static void main(String[] args) {
		ConnectionProvider cp = new SimpleConnectionProvider();
		bookDao = new BookDao(cp);
		authorDao = new AuthorDao(cp);
		
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여하고 싶은 책의 번호를 입력하세요 : ");
		Long no = scanner.nextLong();
		scanner.close();
		
		BookVo book = new BookVo();
		book.setNo(no);
		if(bookDao.isRentable(book)) {
			book.setStatus("대여중");
			bookDao.updateStatus(book);			
			displayBookInfo();
		}else {
			System.out.println("이미 대여중인 책입니다.");
		}
	}
	public static void displayBookInfo() {
		bookDao.findAll().forEach(System.out::println);
	}
}
