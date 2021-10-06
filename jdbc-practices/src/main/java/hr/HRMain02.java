package hr;

import java.util.List;
import java.util.Scanner;

import driver.MyConnection;
import util.SimpleConnectionProvider;

public class HRMain02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("salary 범위 입력 [min max] : ");
		int minSalary = sc.nextInt();
		int maxSalary = sc.nextInt();
		
		EmployeeDao dao = new EmployeeDao(new SimpleConnectionProvider("jdbc:mysql://127.0.0.1:3306/employees?charset=utf8","hr","hr"));
		
		List<EmployeeVo> empList = dao.findBySalary(minSalary, maxSalary);
		
		empList.forEach((emp) -> {
			System.out.println("emp_no : " + emp.getEmpNo() + " | name : " + emp.getFirstName() + " "
					+ emp.getLastName() + " | salary : " + emp.getSalary());
		});

		sc.close();
	}
}
