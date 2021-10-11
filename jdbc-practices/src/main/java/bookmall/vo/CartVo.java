package bookmall.vo;

public class CartVo {
	private Long memberNo;
	private Long bookNo;
	private Integer qty;
	
	private String bookTitle;
	private Integer bookPrice;
	
	
	public Integer getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Integer bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "장바구니 [멤버번호=" + memberNo + ", 책번호=" + bookNo + ", 책제목=" + bookTitle + 
				", 수량=" + qty + ", 가격=" + bookPrice + "]";
	}
	
	
}
