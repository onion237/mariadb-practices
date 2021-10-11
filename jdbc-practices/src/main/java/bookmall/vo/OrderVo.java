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
	private String memberName;
	private String memberEmail;
	private String shipAddr;
	
	private LocalDateTime orderDate;
	private Date sqlDate;
	private String formattedOrderDate;
	private List<OrderBookVo> orderBookList;
	
	public String getFormattedOrderDate() {
		return formattedOrderDate;
	}
	public void setFormattedOrderDate(String formattedOrderDate) {
		this.formattedOrderDate = formattedOrderDate;
	}
	
	
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

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
		return "주문 [no=" + no + ", 주문번호=" + orderNo + ", 결제금액=" + payAmount + ", 멤버번호=" + memberNo
				+ ", 멤버이름=" + memberName + ", 멤버이메일=" + memberEmail + ", 배송지주소=" + shipAddr + ", 주문일자=" + formattedOrderDate
				+ ", \n주문책목록=" + orderBookList + "]";
	}
	
	
}
