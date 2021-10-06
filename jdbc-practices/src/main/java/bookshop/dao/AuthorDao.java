package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;
import util.ConnectionProvider;

public class AuthorDao {
	private ConnectionProvider connectionProvider;

	public AuthorDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	
	public boolean insert(AuthorVo author) {
		boolean result = false;
		String sql = "insert into author(name) values(?)";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, author.getName());
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public List<AuthorVo> findAll() {
		List<AuthorVo> result = new ArrayList<>();
		
		String sql = "select no, name from author";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){

			AuthorVo author = null;
			while(rs.next()) {
				author = new AuthorVo();
				
				author.setNo(rs.getLong("no"));
				author.setName(rs.getString("name"));
				
				result.add(author);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
