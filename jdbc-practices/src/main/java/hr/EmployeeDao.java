package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionProvider;

public class EmployeeDao {
	
	private final ConnectionProvider connectionProvider;
	

	public EmployeeDao(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public List<EmployeeVo> findByName(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeVo> list = new ArrayList<>();
		try {
			conn = connectionProvider.getConnection();
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d') as hireDate from employees "
					+ "where first_name like ? or last_name like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			EmployeeVo emp = null;
			while(rs.next()) {
				emp = new EmployeeVo();
				emp.setEmpNo(rs.getLong("emp_no"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setHireDate(rs.getString("hireDate"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return list;
	}


	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<>();
		
		String sql = "select e.emp_no, first_name, last_name, salary "
					+ "from employees e join salaries s on e.emp_no = s.emp_no "
					+ "where to_date = '9999-01-01' and salary between ? and ?";
		
		try(Connection conn = connectionProvider.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			try(ResultSet rs = pstmt.executeQuery()){
				EmployeeVo emp = null;
				while(rs.next()) {
					emp = new EmployeeVo();
					emp.setEmpNo(rs.getLong("e.emp_no"));
					emp.setFirstName(rs.getString("first_name"));
					emp.setLastName(rs.getString("last_name"));
					emp.setSalary(rs.getInt("salary"));
					
					result.add(emp);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
