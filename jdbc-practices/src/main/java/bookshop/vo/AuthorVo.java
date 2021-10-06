package bookshop.vo;

public class AuthorVo {
	private Long no;
	private String name;
	
		
	public AuthorVo(String name) {
		this.name = name;
	}
	public AuthorVo() {
		// TODO Auto-generated constructor stub
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AuthorVo [no=" + no + ", name=" + name + "]";
	}
}
