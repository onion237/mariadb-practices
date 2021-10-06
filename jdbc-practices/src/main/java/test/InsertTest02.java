package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest02 {
	public static void main(String[] args) {
		insert("영업");
		insert("개발");
		insert("기획");
	}

	private static boolean insert(String name) {
		Connection conn = null;
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기 (protocol, url, database, user info...)
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결 성공");

			// 3. SQL문 준비
			String sql = "insert into dept(name) values(?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 파라미터 바인딩
			pstmt.setString(1, name);
			
			// 5. SQL 실행
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패 : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
				
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result > 0;
	}
}
