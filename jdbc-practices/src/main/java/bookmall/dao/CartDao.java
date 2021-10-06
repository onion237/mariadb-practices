package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
import util.ConnectionProvider;

public class CartDao {
	private ConnectionProvider connectionProvider;
	public CartDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	
	//테스트용
	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		String sql = "select member_no, book_no, qty, book.title from cart join book on cart.book_no = book.no";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				CartVo cart = null;
				while(rs.next()) {
					cart = new CartVo();
					cart.setBookNo(rs.getLong("book_no"));
					cart.setMemberNo(rs.getLong("member_no"));
					cart.setQty(rs.getInt("qty"));
					
					result.add(cart);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<CartVo> findByMemberNo(long memberNo) {
		List<CartVo> result = new ArrayList<>();		
		String sql = "select member_no, book_no, qty, book.title from cart join book on cart.book_no = book.no where member_no = ?";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, memberNo);
			try(ResultSet rs = pstmt.executeQuery()){
				CartVo cart = null;
				while(rs.next()) {
					cart = new CartVo();
					cart.setBookNo(rs.getLong("book_no"));
					cart.setMemberNo(rs.getLong("member_no"));
					cart.setQty(rs.getInt("qty"));
					
					result.add(cart);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean insert(CartVo cart) {
		boolean result = false;
		
		String sql = "insert into cart(member_no, book_no, qty) values(?,?,?)";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setLong(1, cart.getMemberNo());
			pstmt.setLong(2, cart.getBookNo());
			pstmt.setInt(3, cart.getQty());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean delete(CartVo cart) {
		boolean result = false;
		
		String sql = "delete from cart where member_no = ? and book_no = ?";
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setLong(1, cart.getMemberNo());
			pstmt.setLong(2, cart.getBookNo());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean update(CartVo cart) {
		boolean result = false;
		
		String sql = "update cart set qty=? where member_no = ? and book_no = ?";
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setLong(1, cart.getQty());
			pstmt.setLong(2, cart.getMemberNo());
			pstmt.setLong(3, cart.getBookNo());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}

}
