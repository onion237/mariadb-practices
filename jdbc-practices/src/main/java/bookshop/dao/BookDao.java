package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;
import util.ConnectionProvider;

public class BookDao {
	private ConnectionProvider connectionProvider;

	public BookDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public boolean insert(BookVo book) {
		boolean result = false;

		String sql = "insert into book(title, author_no) values(?, ?)";
		try (Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, book.getTitle());
			pstmt.setLong(2, book.getAuthorNo());

			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean isRentable(BookVo book) {
		boolean result = false;

		String sql = "select status from book where no = ?";
		try (Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, book.getNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				rs.next();
				result = "대여가능".equals(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateStatus(BookVo book) {
		boolean result = false;

		String sql = "update book set status=? where no=?";
		try (Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, book.getStatus());
			pstmt.setLong(2, book.getNo());

			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		String sql = "select book.no, title, status, book.author_no, author.name from book join author on book.author_no = author.no";
		try (Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				BookVo book = null;
				while (rs.next()) {
					book = new BookVo();
					book.setNo(rs.getLong("book.no"));
					book.setTitle(rs.getString("title"));
					book.setStatus(rs.getString("status"));
					book.setAuthorNo(rs.getLong("book.author_no"));
					book.setAuthorName(rs.getString("author.name"));

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
