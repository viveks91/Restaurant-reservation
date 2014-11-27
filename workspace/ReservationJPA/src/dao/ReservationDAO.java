package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Address;
import models.Reservation;

public class ReservationDAO implements Serializable {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservationJPA");
	EntityManager em = null;
	
	public ReservationDAO()	{
		em = factory.createEntityManager();
	}
	
	public Reservation create(Reservation reservation) {
		em.getTransaction().begin();
		em.persist(reservation);
		em.getTransaction().commit();
		return reservation;
	}
	
	public void deleteById(int id) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		em.remove(reservation);
		em.getTransaction().commit();
	}
	
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
	
	public List<Reservation> findByRestaurantId(int rid) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationsByRestaurantId");
		q.setParameter("rId", rid);
		List<Reservation> found_reservations = q.getResultList();
		em.getTransaction().commit();
		return found_reservations;
	}
	
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
	
	public List<Reservation> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reservation.findReservationsByUserName");
		q.setParameter("rId", userName);
		List<Reservation> found_reservations = q.getResultList();
		em.getTransaction().commit();
		return found_reservations;
	}
	
	public int updateById(int id, int people_count, Date time,
			int restaurantId, String userName) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		reservation.setPeople_count(people_count);
		reservation.setRestaurantId(restaurantId);
		reservation.setTime(time);
		reservation.setUserName(userName);
		em.merge(reservation);
		em.getTransaction().commit();
		return id;
	}
	
	public Reservation findById(int id) {
		em.getTransaction().begin();
		Reservation reservation = em.find(Reservation.class, id);
		em.getTransaction().commit();
		return reservation;
	}
	
	

}
