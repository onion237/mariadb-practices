package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;
import util.ConnectionProvider;

public class MemberDao {
	private ConnectionProvider connectionProvider;
	public MemberDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}
	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>();
		String sql = "select no, email, name, phone_no, password from member";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			try(ResultSet rs = pstmt.executeQuery()){
				MemberVo member = null;
				
				while(rs.next()) {
					member = new MemberVo();
					member.setNo(rs.getLong("no"));
					member.setEmail(rs.getString("email"));
					member.setName(rs.getString("name"));
					member.setPhoneNo(rs.getString("phone_no"));
					member.setPassword(rs.getString("password"));
					
					result.add(member);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public boolean insert(MemberVo member) {
		boolean result = false;
		String sql = "insert into member(email,password,name,phone_no) values(?,?,?,?)";
		
		try(Connection conn = connectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhoneNo());
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
}
