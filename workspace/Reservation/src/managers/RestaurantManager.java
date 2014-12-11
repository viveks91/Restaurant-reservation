package managers;

import java.io.Serializable;
import java.util.Date;

import models.Restaurant;
import dao.RestaurantDAO;
import dao.AddressDAO;
import dao.CategoryDAO;

public class RestaurantManager implements Serializable{
	
	private RestaurantDAO restrnt_dao = new RestaurantDAO();
	private AddressDAO addr_dao = new AddressDAO();
	private CategoryDAO catgry_dao = new CategoryDAO();
	
	/**
	 * Creates the given Restaurant entity
	 * @param restaurant
	 * @return id
	 */
	public int createRestaurant (Restaurant restaurant) {
		// Create only if the restaurant does not already exists
		if(restrnt_dao.findByRestaurant(restaurant).isEmpty()) 
			restrnt_dao.create(restaurant);
		return restaurant.getId();
	}
	
	/**
	 * Finds the restaurants with given id
	 * @param id
	 * @return Restaurant
	 */
	public Restaurant findRestaurantById (int id) {
		Restaurant restaurant = restrnt_dao.findById(id);
		return restaurant;
	}
	
	/**
	 * Updates the restaurants with given id to the given values
	 * @param id
	 * @param name
	 * @param phoneNo
	 * @param website
	 * @param openingTime
	 * @param closingTime
	 * @param capacity
	 * @param type
	 * @param addressId
	 * @return id
	 */
	public int updateRestaurantById (Integer id, String name, String phoneNo, 
			String website, String openingTime, String closingTime, Integer capacity, 
			String type, Integer addressId, String imageURL, int priceLevel, int rating) {
		int updated_id = id;
		if((findRestaurantById(id) != null) && (addr_dao.findById(addressId) != null)
				&& (catgry_dao.findByType(type) != null))
			updated_id = restrnt_dao.updateById(id, name, phoneNo, website, openingTime,
					closingTime, capacity, type, addressId,imageURL,priceLevel,rating);
		return updated_id;
	}
	
	/**
	 * Removes all the restaurants with the given id
	 * @param id
	 */
	public void removeRestaurantById (int id) {
		Restaurant rstnt = null;
		// If there is not record of the given id then do nothing
		if((rstnt = findRestaurantById(id)) == null) return;
		addr_dao.deleteById(rstnt.getAddressId());
		restrnt_dao.deleteById(id);
	}
	
/*	public static void main(String[] args)
	{
		Restaurant a1 = new Restaurant("esddr", 23,"www.com", new Date(), new Date(), 10, "Indian",4);
		RestaurantManager manager = new RestaurantManager();
		manager.updateRestaurantById (3, "qwerty", 23,"www.com", new Date(), new Date(), 10, "Indian",5);
	}*/
}
