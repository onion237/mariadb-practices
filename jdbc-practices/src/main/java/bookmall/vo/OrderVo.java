package bookmall.vo;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class OrderVo {
	private Long no;
	private String orderNo;
	private String status;
	private Integer payAmount;
	
	private Long memberNo;
	private String shipAddr;
	
	private LocalDateTime orderDate;
	private Date sqlDate;
	private String formattedOrderDate;
	
	public String getFormattedOrderDate() {
		return formattedOrderDate;
	}
	public void setFormattedOrderDate(String formattedOrderDate) {
		this.formattedOrderDate = formattedOrderDate;
	}
	private List<OrderBookVo> orderBookList;
	
	public List<OrderBookVo> getOrderBookList() {
		return orderBookList;
	}
	public void setOrderBookList(List<OrderBookVo> orderBookList) {
		this.orderBookList = orderBookList;
	}
	
	public Date getSqlDate() {
		if(sqlDate == null) {
			sqlDate = Date.valueOf(orderDate.toLocalDate());
		}
		return sqlDate;
	}
	public void setSqlDate(Date sqlDate) {
		this.sqlDate = sqlDate;
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
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
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
		return "주문 [no=" + no + ", orderNo=" + orderNo + ", status=" + status + ", payAmount=" + payAmount
				+ ", memberNo=" + memberNo + ", shipAddr=" + shipAddr + ", formattedOrderDate=" + formattedOrderDate
				+ "\n orderBookList=" + orderBookList + "]";
	}
	
}
