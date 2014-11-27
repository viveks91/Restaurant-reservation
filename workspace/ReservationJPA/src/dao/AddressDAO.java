package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Address;

public class AddressDAO implements Serializable {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservationJPA");
	EntityManager em = null;
	
	public AddressDAO()	{
		em = factory.createEntityManager();
	}
	
	/**
	 * Creates the Address entity
	 * @param address
	 * @return id
	 */
	public int create(Address address) {
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
		return address.getId();
	}
	
	/**
	 * Updates the address with the given id to the given values
	 * @param id
	 * @param street
	 * @param apt_no
	 * @param city
	 * @param state
	 * @param zip
	 * @return id
	 */
	public int updateById(int id, String street, String apt_no, String city, 
			String state, String zip) {
		em.getTransaction().begin();
		Address address = em.find(Address.class, id);
		address.setApt_no(apt_no);
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZip(zip);
		em.merge(address);
		em.getTransaction().commit();
		return id;
	}
	
	/**
	 * Deletes the Address with the given id
	 * @param id
	 */
	public void deleteById(int id) {
		em.getTransaction().begin();
		Address address = em.find(Address.class, id);
		em.remove(address);
		em.getTransaction().commit();
	}
	
	/**
	 * Finds the address equal to the given address
	 * @param address
	 * @return List<Address>
	 */
	public List<Address> findByAddress(Address address) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Address.findByAll");
		q.setParameter("street", address.getStreet());
		q.setParameter("apt_no", address.getApt_no());
		q.setParameter("city", address.getCity());
		q.setParameter("state", address.getState());
		q.setParameter("zip", address.getZip());
		List<Address> found_address = q.getResultList();
		em.getTransaction().commit();
		return found_address;
	}
	
	/**
	 * Finds the address with the given id
	 * @param id
	 * @return Address
	 */
	public Address findById(int id) {
		em.getTransaction().begin();
		Address address = em.find(Address.class, id);
		em.getTransaction().commit();
		return address;
	}
	
}
