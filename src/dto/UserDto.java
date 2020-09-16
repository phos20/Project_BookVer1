package dto; 

public class UserDto {
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private int userPoint;
	private String regDate;
	private int userTotal;
	private String grade;
	  
	public UserDto() {}


	public UserDto(String userId, String userPwd, String userName, String userPhone) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
	}


	public UserDto(String userId, String userPwd, String userName, String userPhone, int userPoint, String regDate,
			int userTotal, String grade) {
		this(userId, userPwd, userName, userPhone);
		this.userPoint = userPoint;
		this.regDate = regDate;
		this.userTotal = userTotal;
		this.grade = grade;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}


	@Override
	public String toString() {
		return userId +" | " + userPwd +" | " + userName +" | " + userPhone + " | " + userPoint 
				+ " | " + regDate + " | " + userTotal + " | " + grade ;   
	}
}
