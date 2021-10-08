package bookmall.vo;

public class CartVo {
	private Long memberNo;
	private Long bookNo;
	private Integer qty;
	
	private String bookTitle;
	
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
		return "장바구니 [memberNo=" + memberNo + ", bookNo=" + bookNo + ", qty=" + qty + ", bookTitle=" + bookTitle
				+ "]";
	}
	
	
}
