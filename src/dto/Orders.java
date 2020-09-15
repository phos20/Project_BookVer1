package dto; 

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private int orderId; 
	private String orderDate;
	private String userId;
	private String grade;
	private String address;
	private int totalAmount;
  
	private List<OrderLine> orderLineList = new ArrayList<OrderLine>();
  
    public Orders() {}
	public Orders(int orderId, String orderDate, String userId, String grade, String address, int totalAmount) {
	super();
	this.orderId = orderId;
	this.orderDate = orderDate;
	this.userId = userId;
	this.grade = grade;
	this.address = address;
	this.totalAmount = totalAmount;
}


	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
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
	
	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}
	
	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}
  
   @Override
	public String toString() {
		return orderId +" | "+ orderDate +" | "+ userId +" | " + grade + " | " + address +" | "+ totalAmount;
				
	}
}
