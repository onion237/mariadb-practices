package bookmall.main;

import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bookmall.dao.BookDao;
import util.ConnectionProvider;
import util.SimpleConnectionProvider;

public class BookMall {
	private static ConnectionProvider connectionProvider;
	private static MemberDao memberDao;
	private static CategoryDao categoryDao;
	private static BookDao bookDao;
	private static CartDao cartDao;
	private static OrderDao orderDao;

	static {
		connectionProvider = new SimpleConnectionProvider();
		memberDao = new MemberDao(connectionProvider);
		categoryDao = new CategoryDao(connectionProvider);
		bookDao = new BookDao(connectionProvider);
		cartDao = new CartDao(connectionProvider);
		orderDao = new OrderDao(connectionProvider);

	}

	public static void main(String[] args) {
		// 회원리스트 2명 삽입 및 조회
		System.out.println("회원 레코드 2개 insert...");
		insertMember();
		System.out.println("===================멤버 목록 출력===================");
		findAllMember();
		System.out.println("================================================");

		// 카테고리 리스트 3개 삽입 및 조회
		System.out.println("카테고리 3개 insert...");
		insertCategory();
		System.out.println("===================카테고리 출력====================");
		findAllCategory();
		System.out.println("================================================");

		// 상품리스트 3개 삽입 및 조회
		System.out.println("상품정보 3개 insert...");
		insertBook();
		System.out.println("===================상품리스트 출력==================");
		findAllBook();
		System.out.println("================================================");

		// cart 2개 삽입 및 출력
		System.out.println("장바구니 정보 2개 insert...");
		insertCart();
		System.out.println("===================장바구니 출력===================");
		findAllCart();
		System.out.println("================================================");

		// 도서 2개 주문 후 출력
		System.out.println("주문 및 주문상세 insert...");
		orderBooks();
		System.out.println("===================주문정보 출력===================");
		findAllOrder();
		System.out.println("================================================");

	}

	private static void findAllOrder() {
		orderDao.findAll().forEach(System.out::println);;
	}

	private static void orderBooks() {
		OrderBookVo orderBook = null;
		OrderVo order = null;

		List<OrderBookVo> list = new ArrayList<>();
		List<BookVo> bookList = bookDao.findAll();
		int bookIdx = 0;
		
		// orderBook 생성
		orderBook = new OrderBookVo();
		orderBook.setBookNo(bookList.get(bookIdx++ % bookList.size()).getNo());
		orderBook.setQty(2);
		list.add(orderBook);

		orderBook = new OrderBookVo();
		orderBook.setBookNo(bookList.get(bookIdx++ % bookList.size()).getNo());
		orderBook.setQty(1);
		list.add(orderBook);

		// 주문할 책들의 현재가 조회
		Map<Long, Integer> bookPrices = orderDao.getCurrentBookPrice(list);
		Integer price;

		for (OrderBookVo ob : list) {
			price = bookPrices.get(ob.getBookNo());
			if (price != null)
				ob.setPrice(price);
		}

		order = new OrderVo();
		order.setMemberNo(memberDao.findAll().get(0).getNo());
		order.setOrderBookList(list);
		order.setShipAddr("부산시 남구.....");
		order.setOrderDate(LocalDateTime.now());
		orderDao.insert(order);
	}

	private static void findAllCart() {
		cartDao.findAll().forEach(System.out::println);
	}

	private static void insertCart() {
		List<MemberVo> memList = memberDao.findAll();
		List<BookVo> bookList = bookDao.findAll();
		int memIdx = 0;
		int bookIdx = 0;
		
		CartVo cart = null;
		cart = new CartVo();
		cart.setMemberNo(memList.get(memIdx++ % memList.size()).getNo());
		cart.setBookNo(bookList.get(bookIdx++ % bookList.size()).getNo());
		cart.setQty(2);
		cartDao.insert(cart);

		cart = new CartVo();
		cart.setMemberNo(memList.get(memIdx++ % memList.size()).getNo());
		cart.setBookNo(bookList.get(bookIdx++ % bookList.size()).getNo());
		cart.setQty(1);
		cartDao.insert(cart);

		cart = new CartVo();
		cart.setMemberNo(memList.get(memIdx++ % memList.size()).getNo());
		cart.setBookNo(bookList.get(bookIdx++ % bookList.size()).getNo());
		cart.setQty(1);
		cartDao.insert(cart);
	}

	private static void findAllBook() {
		bookDao.findAll().forEach(System.out::println);
	}

	private static void insertBook() {
		List<CategoryVo> categoryList = categoryDao.findAll();
		int idx = 0;
		
		BookVo book = null;
		book = new BookVo();
		book.setTitle("토비의 스프링");
		book.setCategoryNo(categoryList.get(idx++ % categoryList.size()).getNo());
		book.setPrice(38000);
		bookDao.insert(book);

		book = new BookVo();
		book.setTitle("정의란 무엇인가");
		book.setCategoryNo(categoryList.get(idx++ % categoryList.size()).getNo());
		book.setPrice(24000);
		bookDao.insert(book);

		book = new BookVo();
		book.setTitle("트와일라잇");
		book.setCategoryNo(categoryList.get(idx++ % categoryList.size()).getNo());
		book.setPrice(14000);
		bookDao.insert(book);
	}

	private static void findAllCategory() {
		categoryDao.findAll().forEach(System.out::println);
	}

	private static void findAllMember() {
		memberDao.findAll().forEach(System.out::println);
	}

	private static void insertMember() {
		MemberVo member = null;
		member = new MemberVo();
		member.setEmail("dooly@test.com");
		member.setPassword("12341234");
		member.setName("둘리");
		member.setPhoneNo("123-2222-3333");
		memberDao.insert(member);

		member = new MemberVo();
		member.setEmail("ddochi@test.com");
		member.setPassword("11111111");
		member.setName("또치");
		member.setPhoneNo("111-2222-3333");
		memberDao.insert(member);
	}

	private static void insertCategory() {
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
