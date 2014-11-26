package Models;

/**
 * Address POJO
 * @author Preety
 *
 */
public class Follower {

	private String userName;
	private String following;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	public Follower(String userName, String following) {
		super();
		this.userName = userName;
		this.following = following;
	}
	public Follower() {
		super();
	}
	
	
}
