package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Address;
import models.Restaurant;

/**
 * 
 * @author Vivek
 *
 */
public class RestaurantDAO implements Serializable {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservationJPA");
	EntityManager em = null;
	
	public RestaurantDAO()	{
		em = factory.createEntityManager();
	}
	
	public Restaurant create(Restaurant restaurant) {
		em.getTransaction().begin();
		em.persist(restaurant);
		em.getTransaction().commit();
		return restaurant;
	}
	
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
	
	public Restaurant findById(int id) {
		em.getTransaction().begin();
		Restaurant restaurant = em.find(Restaurant.class, id);
		em.getTransaction().commit();
		return restaurant;
	}
	
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
	
	public void deleteById(int id) {
		em.getTransaction().begin();
		Restaurant restaurant = em.find(Restaurant.class, id);
		em.remove(restaurant);
		em.getTransaction().commit();
	}
		
}
