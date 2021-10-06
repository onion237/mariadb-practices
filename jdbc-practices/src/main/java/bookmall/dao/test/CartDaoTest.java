package bookmall.dao.test;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;
import util.SimpleConnectionProvider;

public class CartDaoTest {
	static CartDao cartDao;

	public static void main(String[] args) {
		cartDao = new CartDao(new SimpleConnectionProvider());

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
		CartVo cart = new CartVo();
		cart.setMemberNo(1L);
		cart.setBookNo(1L);
		cartDao.delete(cart);
	}


	private static void updateTest() {
		CartVo cart = new CartVo();
		cart.setMemberNo(1L);
		cart.setBookNo(2L);
		cart.setQty(3);
		cartDao.update(cart);
		
	}


	private static void findByMemberNoTest(long memberNo) {
		cartDao.findByMemberNo(memberNo).forEach(System.out::println);
	}

	private static void insertTest() {
		CartVo cart = null;
		cart = new CartVo();
		cart.setMemberNo(1L);
		cart.setBookNo(1L);
		cart.setQty(2);
		cartDao.insert(cart);

		cart = new CartVo();
		cart.setMemberNo(1L);
		cart.setBookNo(2L);
		cart.setQty(1);
		cartDao.insert(cart);

		cart = new CartVo();
		cart.setMemberNo(2L);
		cart.setBookNo(1L);
		cart.setQty(1);
		cartDao.insert(cart);
	}
}
