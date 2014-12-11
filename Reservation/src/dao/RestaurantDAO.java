package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Restaurant;

/**
 * 
 * @author Vivek
 *
 */
public class RestaurantDAO implements Serializable {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
	EntityManager em = null;
	
	public RestaurantDAO()	{
		em = factory.createEntityManager();
	}
	
	/**
	 * Creates the restaurant entity
	 * @param restaurant
	 * @return id
	 */
	public int create(Restaurant restaurant) {
		em.getTransaction().begin();
		em.persist(restaurant);
		em.getTransaction().commit();
		return restaurant.getId();
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
	public int updateById(Integer id, String name, Integer phoneNo, String website,
			Date openingTime, Date closingTime, Integer capacity, String type,
			Integer addressId) {
		em.getTransaction().begin();
		Restaurant restaurant = em.find(Restaurant.class, id);
		restaurant.setAddressId(addressId);
		restaurant.setCapacity(capacity);
		restaurant.setClosingTime(closingTime);
		restaurant.setName(name);
		restaurant.setOpeningTime(openingTime);
		restaurant.setPhoneNo(phoneNo);
		restaurant.setType(type);
		restaurant.setWebsite(website);
		em.merge(restaurant);
		em.getTransaction().commit();
		return id;
	}
	
	/**
	 * Finds the restaurant with given id
	 * @param id
	 * @return Restaurant
	 */
	public Restaurant findById(int id) {
		em.getTransaction().begin();
		Restaurant restaurant = em.find(Restaurant.class, id);
		em.getTransaction().commit();
		return restaurant;
	}
	
	/**
	 * Finds the restaurants equal to the given restaurant
	 * @param restaurant
	 * @return List<Restaurant>
	 */
	public List<Restaurant> findByRestaurant(Restaurant restaurant) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Restaurant.findByAll");
		q.setParameter("name", restaurant.getName());
		q.setParameter("addressId", restaurant.getAddressId());
		q.setParameter("type", restaurant.getType());
		q.setParameter("website", restaurant.getWebsite());
		List<Restaurant> found_restaurant = q.getResultList();
		em.getTransaction().commit();
		return found_restaurant;
	}
	
	/**
	 * Deletes restaurant with given id
	 * @param id
	 */
	public void deleteById(int id) {
		em.getTransaction().begin();
		Restaurant restaurant = em.find(Restaurant.class, id);
		em.remove(restaurant);
		em.getTransaction().commit();
	}
		
}
