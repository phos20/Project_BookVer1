package dto;

public class CartDto {
	
	private int cartNo;
	private String userId;
	private String booksId;
	private int quantity;
	private String regDate;
	
	public CartDto() {}

	public CartDto(int cartNo, String userId, String booksId, int quantity, String regDate) {
		super();
		this.cartNo = cartNo;
		this.userId = userId;
		this.booksId = booksId;
		this.quantity = quantity;
		this.regDate = regDate;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBooksId() {
		return booksId;
	}

	public void setBooksId(String booksId) {
		this.booksId = booksId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	

}
