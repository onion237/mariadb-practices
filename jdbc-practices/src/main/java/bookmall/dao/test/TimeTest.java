package bookmall.dao.test;

import java.sql.Date;
import java.time.LocalDateTime;

public class TimeTest {
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
		System.out.println(Date.valueOf(dt.toLocalDate()));
		
	}
}
