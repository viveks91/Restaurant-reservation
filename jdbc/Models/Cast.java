package vivek.S03.jdbc.Models;

/**
 * Cast -POJO
 * @author Vivek
 *
 */

public class Cast {
	private String charecterName;
	private Integer movie;
	private Integer actor;
	public Cast() {
		super();
	}
	public Cast(String charecterName, Integer movie, Integer actor) {
		super();
		this.charecterName = charecterName;
		this.movie = movie;
		this.actor = actor;
	}
	public String getCharecterName() {
		return charecterName;
	}
	public void setCharecterName(String charecterName) {
		this.charecterName = charecterName;
	}
	public Integer getMovie() {
		return movie;
	}
	public void setMovie(Integer movie) {
		this.movie = movie;
	}
	public Integer getActor() {
		return actor;
	}
	public void setActor(Integer actor) {
		this.actor = actor;
	}
}