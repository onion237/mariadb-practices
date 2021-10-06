package hr;

import java.util.List;
import java.util.Scanner;

import util.SimpleConnectionProvider;

public class HRMain01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력해주세요 : ");
		String name = sc.nextLine();
		
		EmployeeDao dao = new EmployeeDao(new SimpleConnectionProvider("jdbc:mysql://127.0.0.1:3306/employees?charset=utf8","hr","hr"));
		List<EmployeeVo> empList = dao.findByName(name);
		empList.forEach(System.out::println);
		
		sc.close();
	}
}
