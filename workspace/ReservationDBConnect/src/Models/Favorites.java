package Models;

/**
 * Address POJO
 * @author Preety
 *
 */

public class Favorites {

	private String userName;
	private String restaurantId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Favorites(String userName, String restaurantId) {
		super();
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	public Favorites() {
		super();
	}
	
}
