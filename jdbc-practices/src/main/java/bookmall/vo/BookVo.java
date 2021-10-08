package bookmall.vo;

import java.util.Objects;

public class BookVo {
	private Long no;
	private String title;
	private Integer price;
	private Long categoryNo;
	
	private String category;
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	@Override
	public String toString() {
		return "도서 [no=" + no + ", title=" + title + ", price=" + price + ", categoryNo=" + categoryNo
				+ ", category=" + category + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(no);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVo other = (BookVo) obj;
		return Objects.equals(no, other.no);
	}
}
