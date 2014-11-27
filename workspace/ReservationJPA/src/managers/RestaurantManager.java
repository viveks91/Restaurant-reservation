package managers;

import java.io.Serializable;
import java.util.Date;

import models.Restaurant;
import dao.RestaurantDAO;
import dao.AddressDAO;

public class RestaurantManager implements Serializable{
	
	private RestaurantDAO restrnt_dao = new RestaurantDAO();
	private AddressDAO addr_dao = new AddressDAO();
	
	public Restaurant createRestaurant (Restaurant restaurant) {
		// Create only if the restaurant does not already exists
		if(restrnt_dao.findByRestaurant(restaurant).isEmpty()) 
			restrnt_dao.create(restaurant);
		return restaurant;
	}
	
	public Restaurant findRestaurantById (int id) {
		Restaurant restaurant = restrnt_dao.findById(id);
		return restaurant;
	}
	
	public int updateRestaurantById (Integer id, String name, Integer phoneNo, 
			String website, Date openingTime, Date closingTime, Integer capacity, 
			String type, Integer addressId) {
		int updated_id = id;
		if(findRestaurantById(id) != null)
			updated_id = restrnt_dao.updateById(id, name, phoneNo, website, openingTime,
					closingTime, capacity, type, addressId);
		return updated_id;
	}
	
	public void removeRestaurantById (int id) {
		Restaurant rstnt = null;
		// If there is not record of the given id then do nothing
		if((rstnt = findRestaurantById(id)) == null) return;
		addr_dao.deleteById(rstnt.getAddressId());
		restrnt_dao.deleteById(id);
	}
	
	public static void main(String[] args)
	{
//		Restaurant a1 = new Restaurant("esddr", 23,"www.com", new Date(), new Date(), 10, "Indian",4);
		RestaurantManager manager = new RestaurantManager();
		manager.updateRestaurantById (3, "qwerty", 23,"www.com", new Date(), new Date(), 10, "Indian",5);
	}
}
