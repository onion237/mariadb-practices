package bookmall.vo;

import java.util.List;

public class OrderVo {
	private Long no;
	private String orderNo;
	private Long memberNo;
	private Integer payAmount;
	private String shipAddr;
	private String orderDate;
	private String status;
	
	private List<OrderBookVo> orderBookList;
	
	
	public List<OrderBookVo> getOrderBookList() {
		return orderBookList;
	}
	public void setOrderBookList(List<OrderBookVo> orderBookList) {
		this.orderBookList = orderBookList;
	}
	
	private Integer calcPayAmount() {
		int sum = 0;
		for(OrderBookVo orderBook : orderBookList) {
			sum += orderBook.getPrice() * orderBook.getQty();
		}
		return sum;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getShipAddr() {
		return shipAddr;
	}
	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", orderNo=" + orderNo + ", memberNo=" + memberNo + ", payAmount=" + payAmount
				+ ", shipAddr=" + shipAddr + ", orderDate=" + orderDate + ", status=" + status + "]";
	}

	
}
