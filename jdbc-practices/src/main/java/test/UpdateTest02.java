package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest02 {
	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(6L);
		vo.setName("전략기획");

		boolean result = update(vo);
		if (result) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}

	private static boolean update(DeptVo vo) {
		Connection conn = null;
		boolean result = false;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기 (protocol, url, database, user info...)
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결 성공");

			// 3. Statement 생성
			String sql = "update dept set name = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
			
			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count > 0;
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
		return result;

	}
}
