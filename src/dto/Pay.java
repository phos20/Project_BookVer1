package dto;

public class Pay {
	private int payNo;
	private String userId;
	private String payDate;
	private String address;
	private int totalAmount;
	
	public Pay() {};
	public Pay(int payNo, String userId, String payDate, String address, int totalAmount) {
		super();
		this.payNo = payNo;
		this.userId = userId;
		this.payDate = payDate;
		this.address = address;
		this.totalAmount = totalAmount;
	}
	public int getPayNo() {
		return payNo;
	}
	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	

}
