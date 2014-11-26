package Models;

import java.sql.Time;

public class Restaurant {
	private Integer id;
	private String name;
	private Integer phoneNo;
	private String website;
	private Time openingTime;
	private Time closingTime;
	private Integer capacity;
	private String type;
	private Integer addressId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Time getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}
	public Time getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Restaurant(Integer id, String name, Integer phoneNo, String website,
			Time openingTime, Time closingTime, Integer capacity, String type,
			Integer addressId) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.website = website;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.capacity = capacity;
		this.type = type;
		this.addressId = addressId;
	}
	public Restaurant(String name, Integer phoneNo, String website,
			Time openingTime, Time closingTime, Integer capacity, String type,
			Integer addressId) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.website = website;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.capacity = capacity;
		this.type = type;
		this.addressId = addressId;
	}
	public Restaurant() {
		super();
	}
	

}
