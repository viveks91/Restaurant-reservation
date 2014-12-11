package models;

import java.io.Serializable;
//import java.sql.Date;
//import java.sql.Time;
import java.util.Date;









import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Restaurant
 * @author Vivek
 *
 */

@Entity
@NamedQuery(
		name="Restaurant.findByAll", 
		query="select r from Restaurant r where r.name = :name and "
				+ "r.addressId = :addressId and r.type = :type and r.website =:website")
public class Restaurant implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String phoneNo;
	private String website;
	private String openingTime;
	private String closingTime;
	private Integer capacity;
	private String type;
	private Integer addressId;
	private String imageURL;
	private int priceLevel;
	private int rating;
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
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

	
	public int getPriceLevel() {
		return priceLevel;
	}
	public void setPriceLevel(int priceLevel) {
		this.priceLevel = priceLevel;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Restaurant(String name, String phoneNo, String website,
			String openingTime, String closingTime, Integer capacity, String type,
			Integer addressId, String imageURL, int priceLevel, int rating) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.website = website;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.capacity = capacity;
		this.type = type;
		this.addressId = addressId;
		this.imageURL = imageURL;
		this.priceLevel = priceLevel;
		this.rating = rating;
	}
	public Restaurant(Integer id, String name, String phoneNo, String website,
			String openingTime, String closingTime, Integer capacity, String type,
			Integer addressId, String imageURL, int priceLevel, int rating) {
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
		this.imageURL = imageURL;
		this.priceLevel = priceLevel;
		this.rating = rating;
	}
	public Restaurant() {
		super();
	}
	
	

}
