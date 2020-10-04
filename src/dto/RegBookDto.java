package dto; 

public class RegBookDto {
	private int regNo;
	private String regName;
	private String regWriter;
	private String regPublisher;
	private String userId;
	private String regDate;

	public RegBookDto() {}

	public RegBookDto(int regNo, String regName, String regWriter, String regPublisher, String userId, String regDate) {
		super();
		this.regNo = regNo;
		this.regName = regName;
		this.regWriter = regWriter;
		this.regPublisher = regPublisher;
		this.userId = userId;
		this.regDate = regDate;
	}

	
	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegWriter() {
		return regWriter;
	}

	public void setRegWriter(String regWriter) {
		this.regWriter = regWriter;
	}

	public String getRegPublisher() {
		return regPublisher;
	}

	public void setRegPublisher(String regPublisher) {
		this.regPublisher = regPublisher;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return regNo + " | " + regName + " | " + regWriter + " | " + regPublisher + " | " + userId + " | " + regDate;
	}
}
