package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import util.ConnectionProvider;

public class BookDao {
	private ConnectionProvider connectionProvider;
	public BookDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	public boolean insert(BookVo book) {
		boolean result = false;
		String sql = "insert into book(title,price,category_no) values(?,?,?)";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getPrice());
			pstmt.setLong(3, book.getCategoryNo());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		String sql = "select book.no, title, price, book.category_no, name"
					+ " from book join category on book.category_no = category.no";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rs = pstmt.executeQuery();){
				BookVo book = null;
				while(rs.next()) {
					book = new BookVo();
					book.setNo(rs.getLong("book.no"));
					book.setTitle(rs.getString("title"));
					book.setPrice(rs.getInt("price"));
					book.setCategoryNo(rs.getLong("book.category_no"));
					book.setCategory(rs.getString("name"));
				
					result.add(book);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	

}
