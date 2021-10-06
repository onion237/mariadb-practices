package bookshop.dao.test;


import bookshop.dao.AuthorDao;
import bookshop.vo.AuthorVo;
import util.ConnectionProvider;
import util.SimpleConnectionProvider;

public class AuthorTest {
	public static void main(String[] args) {
		ConnectionProvider connectionProvider = new SimpleConnectionProvider();
		AuthorDao authorDao = new AuthorDao(connectionProvider);
//		insertTest(authorDao);
		findAllTest(authorDao);
	}


	private static void findAllTest(AuthorDao authorDao) {
		authorDao.findAll().forEach(System.out::println);		
	}

	private static void insertTest(AuthorDao authorDao) {
		// TODO Auto-generated method stub
		authorDao.insert(new AuthorVo("스테파니메이어"));
		authorDao.insert(new AuthorVo("조정래"));
		authorDao.insert(new AuthorVo("김동인"));
		authorDao.insert(new AuthorVo("김난도"));
		authorDao.insert(new AuthorVo("천상병"));
		authorDao.insert(new AuthorVo("원수연"));
	}
}
