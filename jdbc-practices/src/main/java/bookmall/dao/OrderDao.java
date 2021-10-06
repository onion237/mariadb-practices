package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bookmall.vo.OrderVo;
import util.ConnectionProvider;

public class OrderDao {
	private ConnectionProvider connectionProvider;
	public OrderDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	
	public boolean insert(OrderVo order) {
		boolean result = false;
		PreparedStatement pstmt = null;
		
		try(Connection conn = connectionProvider.getConnection()){
			conn.setAutoCommit(false);
			
			
			String sql = "insert into ";
			pstmt = 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

}
