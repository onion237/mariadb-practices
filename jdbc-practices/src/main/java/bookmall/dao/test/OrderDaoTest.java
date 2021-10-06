package bookmall.dao.test;

import java.util.ArrayList;
import java.util.List;

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
		findByMemberNoTest(1L);
		System.out.println("---------------------------------------------");
		findByMemberNoTest(2L);
		
		System.out.println("======================update test======================");
		updateTest();
		findByMemberNoTest(1L);
		System.out.println("---------------------------------------------");
		findByMemberNoTest(2L);
		
		System.out.println("======================delete test======================");
		deleteTest();
		findByMemberNoTest(1L);
		System.out.println("---------------------------------------------");
		findByMemberNoTest(2L);
	}

	private static void deleteTest() {
		OrderVo cart = new OrderVo();
		cart.setMemberNo(1L);
		cart.setBookNo(1L);
		orderDao.delete(cart);
	}


	private static void updateTest() {
		OrderVo cart = new OrderVo();
		cart.setMemberNo(1L);
		cart.setBookNo(2L);
		cart.setQty(3);
		orderDao.update(cart);
		
	}

	
	private static void findByMemberNoTest(long memberNo) {
		orderDao.findByMemberNo(memberNo).forEach(System.out::println);
	}

	private static void insertTest() {
		OrderVo order = null;
		OrderBookVo orderBook = null; 
		
		List<OrderBookVo> list = new ArrayList<>();

		orderBook = new OrderBookVo();
		orderBook.setBookNo(1L);
		orderBook.setQty(2);
		list.add(orderBook);
		
		orderBook = new OrderBookVo();
		orderBook.setBookNo(2L);
		orderBook.setQty(1);
		list.add(orderBook);
		
		order = new OrderVo();
		order.setMemberNo(1L);
		order.setOrderBookList(list);
		order.setShipAddr("test addr");
		orderDao.insert(order);

		order = new OrderVo();
		order.setMemberNo(1L);
		order.setBookNo(2L);
		order.setQty(1);
		orderDao.insert(order);

		order = new OrderVo();
		order.setMemberNo(2L);
		order.setBookNo(1L);
		order.setQty(1);
		orderDao.insert(order);
	}
}
