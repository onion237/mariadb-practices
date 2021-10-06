package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest02 {
	public static void main(String[] args) {
		search("pat");
	}
	public static void search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기 (protocol, url, database, user info...)
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결 성공");

			// 3. Statement 생성
			String sql = "select emp_no, first_name, last_name from employees where first_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long empNo = rs.getLong("emp_no");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				System.out.println("empNo : " + empNo + ", name : " + firstName + " " + lastName);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(rs != null && !rs.isClosed())
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();

				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
