package dto;

public class Sales {
	private int salesId;
	private String salesDate;
	private double totalAmount;

	public Sales() {
	}

	public Sales(int salesId, String salesDate, double totalAmount) {
		super();
		this.salesId = salesId;
		this.salesDate = salesDate;
		this.totalAmount = totalAmount;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	};
	
	@Override
	public String toString() {
		return "주문일자 : "+ salesDate +" 총금액 : "+ totalAmount;
				
	}

}
