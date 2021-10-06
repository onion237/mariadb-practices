package bookshop.example;

public class Book {
	private int bookNo;
	private String title;
	private String author;
	private int stateCode;
	
	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void rent() {
		if(rentable()) {
			stateCode = 0;
			System.out.println(title + "이(가) 대여되었습니다.");
		}else {
			System.out.println(title + "을(를) 대여할 수 없습니다.");
		}
	}
	public boolean rentable() {
		boolean result = true;
		
		//책을 빌릴 수 없는 조건들 나열
		if(stateCode != 1)
			result = false;
		
		//////
		
		return result;
	}

	public void print() {
		System.out.println("책 번호: " + bookNo + ", 책 제목:" + title + ". 작가:" + author + ". 대여 유무:" + (stateCode == 1? "재고있음" : "대여중")); 
		
	}
}
