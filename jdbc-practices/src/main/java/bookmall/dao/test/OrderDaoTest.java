package bookmall.dao.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;
import util.SimpleConnectionProvider;

public class OrderDaoTest {
	static OrderDao orderDao;

	public static void main(String[] args) {
		orderDao = new OrderDao(new SimpleConnectionProvider());

		System.out.println("======================insert test======================");
		insertTest();
//		findByMemberNoTest(1L);
//		System.out.println("---------------------------------------------");
//		findByMemberNoTest(2L);
//		
//		System.out.println("======================update test======================");
//		updateTest();
//		findByMemberNoTest(1L);
//		System.out.println("---------------------------------------------");
//		findByMemberNoTest(2L);
//		
//		System.out.println("======================delete test======================");
//		deleteTest();
//		findByMemberNoTest(1L);
//		System.out.println("---------------------------------------------");
//		findByMemberNoTest(2L);
	}

	private static void deleteTest() {
		OrderVo cart = new OrderVo();
		cart.setMemberNo(1L);
//		cart.setBookNo(1L);
//		orderDao.delete(cart);
	}

//
//	private static void updateTest() {
//		OrderVo cart = new OrderVo();
//		cart.setMemberNo(1L);
//		cart.setBookNo(2L);
//		cart.setQty(3);
//		orderDao.update(cart);
//		
//	}
//
//	
//	private static void findByMemberNoTest(long memberNo) {
//		orderDao.findByMemberNo(memberNo).forEach(System.out::println);
//	}

	private static void insertTest() {
		OrderBookVo orderBook = null; 
		OrderVo order = null;
		
		List<OrderBookVo> list = new ArrayList<>();
		
		//orderBook 생성
		orderBook = new OrderBookVo();
		orderBook.setBookNo(1L);
		orderBook.setQty(2);
		list.add(orderBook);
		
		orderBook = new OrderBookVo();
		orderBook.setBookNo(2L);
		orderBook.setQty(1);
		list.add(orderBook);
		
		//주문할 책들의 현재가 조회
		Map<Long, Integer> bookPrices = orderDao.getCurrentBookPrice(list);
		Integer price;
		
		for(OrderBookVo ob : list) {
			price = bookPrices.get(ob.getBookNo());
			if(price != null)
				ob.setPrice(price);
		}
		
		
		order = new OrderVo();
		order.setMemberNo(1L);
		order.setOrderBookList(list);
		order.setShipAddr("test addr");
		order.setOrderDate(LocalDateTime.now());
		orderDao.insert(order);

	}
}
