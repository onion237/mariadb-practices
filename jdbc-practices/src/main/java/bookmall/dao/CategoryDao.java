package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;
import util.ConnectionProvider;

public class CategoryDao {
	private ConnectionProvider connectionProvider;
	
	public CategoryDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	
	public boolean insert(CategoryVo category) {
		boolean result = false;
		String sql = "insert into category(name, description) values(?,?)";
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql )){
			pstmt.setString(1, category.getName());
			pstmt.setString(2, category.getDescription());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public boolean insertAll(List<CategoryVo> categoryList) {		
		if(categoryList == null || categoryList.size() == 0) return false;
		
		boolean result = false;
		String sql = "insert into category(name, description) values(?,?)";
		for(int i = 1; i < categoryList.size(); i++) {
			sql += ", (?,?)";
		}
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql )){
			
			int idx = 1;
			for(CategoryVo category : categoryList) {
				pstmt.setString(idx++, category.getName());
				pstmt.setString(idx++, category.getDescription());				
			}
			
			result = pstmt.executeUpdate() >= categoryList.size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public List<CategoryVo> findAll(){
		List<CategoryVo> result = new ArrayList<>();
		
		String sql = "select no, name, description from category";
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				CategoryVo category = null;
				
				while(rs.next()) {
					category = new CategoryVo();
					category.setNo(rs.getLong("no"));
					category.setName(rs.getString("name"));
					category.setDescription(rs.getString("description"));
					
					result.add(category);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
