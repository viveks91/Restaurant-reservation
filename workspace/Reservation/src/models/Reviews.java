package models;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@NamedQueries({
	@NamedQuery(
		name="Reviews.deleteReviewByUserName",
		query="delete from Reviews r where r.userName = :userName"
	),
	@NamedQuery(
		name="Reviews.updateCommentByUserName",
        query="update Reviews r set r.comments = :comments where r.userName = :userName"
	),
	@NamedQuery(
			name="Reviews.findAllReviewsByUserName",
	        query="select r from Reviews r where r.userName = :userName"
		),
	@NamedQuery(
			name="Reviews.findAllReviewsByRestaurantId",
	        query="select r from Reviews r where r.restaurantId = :restaurantId"
		),
	@NamedQuery(
			name="Reviews.findAllReviewsWithRestaurantName",
			query="select r.name, rev.comments from Reviews rev, Restaurant r where rev.userName = :userName and rev.restaurantId = r.id"
		),
})

public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String userName;
	private int ratings;
	private String comments;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	private int restaurantId;
	
	public String toString()
	{
		return id + " " + userName + " " + comments;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public Reviews(int id, String userName, int ratings, String comments,
			Date date, int restaurantId) {
		super();
		this.id = id;
		this.userName = userName;
		this.ratings = ratings;
		this.comments = comments;
		this.date = date;
		this.restaurantId = restaurantId;
	}
	public Reviews() {
		super();
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
	
}
