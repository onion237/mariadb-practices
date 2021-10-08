package bookmall.vo;

public class OrderBookVo {
	private Long orderNo;
	private Long bookNo;
	private int qty;
	private int price;
	
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
		return "주문상세 [orderNo=" + orderNo + ", bookNo=" + bookNo + ", qty=" + qty + ", price=" + price + "]";
	}
	
}
