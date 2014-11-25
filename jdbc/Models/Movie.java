package vivek.S03.jdbc.Models;

/**
 * Movie -POJO
 * @author Vivek
 *
 */

public class Movie {
	private Integer id;
	private String title;
	private String posterImage;
	private String releaseDate;
	public Movie() {
		super();
	}
	public Movie(String title, String posterImage, String releaseDate) {
		super();
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}
	public Movie(Integer id, String title, String posterImage,
			String releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
}