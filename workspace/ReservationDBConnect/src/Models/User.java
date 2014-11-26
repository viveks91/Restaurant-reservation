package Models;

/**
 * Address POJO
 * @author Preety
 *
 */


public class User {

	private String userName;
	private String emailId;
	private int phoneNo;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public User(String userName, String emailId, int phoneNo) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
	}
	public User() {
		super();
	}
	
}
