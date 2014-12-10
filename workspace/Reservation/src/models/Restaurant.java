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
	private Integer phoneNo;
	private String website;
	
	@Temporal(TemporalType.TIME)
	private Date openingTime;
	@Temporal(TemporalType.TIME)
	private Date closingTime;
	private Integer capacity;
	private String type;
	private Integer addressId;
	private String imageURL;
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
	public Date getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
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
	public Restaurant(String name, Integer phoneNo, String website,
			Date openingTime, Date closingTime, Integer capacity, String type,
			Integer addressId, String imageURL) {
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
	}
	public Restaurant(Integer id, String name, Integer phoneNo, String website,
			Date openingTime, Date closingTime, Integer capacity, String type,
			Integer addressId, String imageURL) {
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
	}
	public Restaurant() {
		super();
	}
	
	

}
