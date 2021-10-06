package bookmall.dao.test;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;
import util.SimpleConnectionProvider;

public class MemberDaoTest {
	static MemberDao memberDao;

	public static void main(String[] args) {
		memberDao = new MemberDao(new SimpleConnectionProvider());

		insertTest();
		findAllTest();
	}

	private static void findAllTest() {
		memberDao.findAll().forEach(System.out::println);
	}

	private static void insertTest() {
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
}
