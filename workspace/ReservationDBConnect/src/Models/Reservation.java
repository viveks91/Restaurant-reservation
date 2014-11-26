package Models;

import java.sql.Date;

public class Reservation {
	private Integer id;
	private Integer people_count;
	private Date time;
	private Integer restaurantId;
	private String userName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPeople_count() {
		return people_count;
	}
	public void setPeople_count(Integer people_count) {
		this.people_count = people_count;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Reservation(Integer id, Integer people_count, Date time,
			Integer restaurantId, String userName) {
		super();
		this.id = id;
		this.people_count = people_count;
		this.time = time;
		this.restaurantId = restaurantId;
		this.userName = userName;
	}
	public Reservation(Integer people_count, Date time, Integer restaurantId,
			String userName) {
		super();
		this.people_count = people_count;
		this.time = time;
		this.restaurantId = restaurantId;
		this.userName = userName;
	}
	public Reservation() {
		super();
	}
	

}
