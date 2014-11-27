package models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Address POJO
 * @author Preety
 *
 */

@Entity
public class User extends Person{

	private String emailId;
	private int phoneNo;
	private int addressId;
	
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
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
	
	public User(String userName, String password, String firstName,
			String lastName, String emailId, int phoneNo, int addressId) {
		super(userName, password, firstName, lastName);
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.addressId = addressId;
	}
	public User() {
		super();
	}
	
}
