package bookmall.vo;

public class OrderBookVo {
	private Long orderNo;
	private Long bookNo;
	private String bookTitle;
	private int qty;
	private int price;
	
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "주문상세 [orderNo=" + orderNo + ", 책번호=" + bookNo + ", 책제목=" + bookTitle + ", 주문수량=" + qty + ", 가격=" + price + "]";
	}
	
}
