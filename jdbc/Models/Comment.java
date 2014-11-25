package vivek.S03.jdbc.Models;

/**
 * Comment -POJO
 * @author Vivek
 *
 */

public class Comment {
	private String comment;
	private String date;
	private String user;
	private Integer movie;
	public Comment(String comment, String date, String user, Integer movie) {
		super();
		this.comment = comment;
		this.date = date;
		this.user = user;
		this.movie = movie;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getMovie() {
		return movie;
	}
	public void setMovie(Integer movie) {
		this.movie = movie;
	}
	public Comment() {
		super();
	}
}