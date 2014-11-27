package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Reservation;

public class ReservationDAO implements Serializable {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservationJPA");
	EntityManager em = null;
	
	public ReservationDAO()	{
		em = factory.createEntityManager();
	}
	
	/**
	 * Creates the reservation entity
	 * @param reservation
	 * @return id
	 */
	public int create(Reservation reservation) {
		em.getTransaction().begin();
		em.persist(reservation);
		em.getTransaction().commit();
		return reservation.getId();
	}
	
	/**
	 * Deletes the reservation with the given id
	 * @param id
	 */
	public void deleteById(int id) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		em.remove(reservation);
		em.getTransaction().commit();
	}
	
	/**
	 * deletes the reservations with the given restaurant id
	 * @param rid
	 */
	public void deleteByRestaurantId(int rid) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationIdByRestaurantId");
		q.setParameter("rId", rid);
		List<Integer> found_reservations = q.getResultList();
		Integer id;
		for(int i=0;i< found_reservations.size();i++) {
			id = found_reservations.get(i);
			deleteById(id);
		}
		em.getTransaction().commit();
	}
	
	/**
	 * Finds all the reservations with the given restaurant id
	 * @param rid
	 * @return List<Reservation>
	 */
	public List<Reservation> findByRestaurantId(int rid) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationsByRestaurantId");
		q.setParameter("rId", rid);
		List<Reservation> found_reservations = q.getResultList();
		em.getTransaction().commit();
		return found_reservations;
	}
	
	/**
	 * deletes the reservations with the given username
	 * @param userName
	 */
	public void deleteByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationIdByUserName");
		q.setParameter("username", userName);
		List<Integer> found_reservations = q.getResultList();
		Integer id;
		for(int i=0;i< found_reservations.size();i++) {
			id = found_reservations.get(i);
			deleteById(id);
		}
		em.getTransaction().commit();
	}
	
	/**
	 * Finds all the reservations with the given username
	 * @param userName
	 * @return List<Reservation>
	 */
	public List<Reservation> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationsByUserName");
		q.setParameter("username", userName);
		List<Reservation> found_reservations = q.getResultList();
		em.getTransaction().commit();
		return found_reservations;
	}
	
	/**
	 * Updates the reservation with the given id to given values
	 * @param id
	 * @param people_count
	 * @param time
	 * @return id
	 */
	public int updateById(int id, int people_count, Date time) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		reservation.setPeople_count(people_count);
		reservation.setTime(time);
		em.merge(reservation);
		em.getTransaction().commit();
		return id;
	}
	
	/**
	 * Finds the reservation with the given id
	 * @param id
	 * @return Reservation
	 */
	public Reservation findById(int id) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		em.getTransaction().commit();
		return reservation;
	}
	
	

}
