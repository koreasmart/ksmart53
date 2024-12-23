package thymeleaf.exam.dto;

public class User {

	private String userId;
	private String userPw;
	private String userName;
	private String userGrade;
	private String userEmail;
	private String userMobile;
	
	public User() {}
	
	public User(String userId, String userPw, String userName, String userGrade, String userEmail, String userMobile) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userGrade = userGrade;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", userPw=");
		builder.append(userPw);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userGrade=");
		builder.append(userGrade);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", userMobile=");
		builder.append(userMobile);
		builder.append("]");
		return builder.toString();
	}
	
		
	
}
