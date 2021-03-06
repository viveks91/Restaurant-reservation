package models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@IdClass(value=FavoritesPK.class)


@NamedQueries({
	@NamedQuery(
		name="Favorites.deleteByUserNameAndFavorites",
		query="delete from Favorites f where f.userName = :userName and f.restaurantId = :restaurantId"
	),
	@NamedQuery(
			name="Favorites.findByUserName",
			query="select f from Favorites f where f.userName = :userName"
		),
		@NamedQuery(
			name="FavoritesRestaurants.findByUserName",
			query="select r.name, rev.ratings, ad.apt_No, ad.street,ad.city,ad.state,ad.zip,r.id,r.imageURL from Favorites f, Restaurant r, Reviews rev, Address ad where "
					+ "f.userName = :userName and r.id = f.restaurantId and rev.userName = f.userName "
					+ "and rev.restaurantId = f.restaurantId and r.addressId = ad.id"
					
		),

})


public class Favorites implements Serializable{

	@Id
	private String userName;
	@Id
	private int restaurantId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Favorites(String userName, int restaurantId) {
		super();
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	public Favorites() {
		super();
	}
	
}
