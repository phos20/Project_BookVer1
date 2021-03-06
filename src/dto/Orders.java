package dto; 

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private int orderId; 
	private String orderDate;
	private String userId;
	private String address;
	private double totalAmount;
  
	private List<OrderLine> orderLineList = new ArrayList<OrderLine>();
  
    public Orders() {}
	public Orders(int orderId, String orderDate, String userId, String address, double totalAmount) {
	super();
	this.orderId = orderId;
	this.orderDate = orderDate;
	this.userId = userId;
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
	
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
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
		return "주문번호 : "+orderId +"주문일자 : "+ orderDate +"배송지 : " + address +"총금액 : "+ totalAmount;
				
	}
}
