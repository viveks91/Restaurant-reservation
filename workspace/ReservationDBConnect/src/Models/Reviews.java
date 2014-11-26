package Models;

/**
 * Address POJO
 * @author Preety
 *
 */

import java.sql.Date;

public class Reviews {

	private String userName;
	private int ratings;
	private String comments;
	private Date date;
	private int restaurantId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Reviews(String userName, int ratings, String comments, Date date,
			int restaurantId) {
		super();
		this.userName = userName;
		this.ratings = ratings;
		this.comments = comments;
		this.date = date;
		this.restaurantId = restaurantId;
	}
	public Reviews() {
		super();
	}
	
}
