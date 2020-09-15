package dto;

public class UserDto {
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private int userPoint;
	private String regDate;
	private String grade;
	  
	public UserDto() {}
	
	public UserDto(String userId, String userPwd, String userName, String userPhone,
					int userPoint, String regDate, String grade) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userPoint = userPoint;
		this.regDate = regDate;
		this.grade = grade;
	}


	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return userId +" | " + userPwd +" | " + userName +" | " + regDate+" | "+ grade ;   
	}
	  
	
	  
}



