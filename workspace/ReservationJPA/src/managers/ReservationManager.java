package managers;

import java.io.Serializable;
import java.util.List;

import models.Reservation;
import dao.ReservationDAO;

public class ReservationManager implements Serializable{
	
	private ReservationDAO rsvtn_dao = new ReservationDAO();
	
	public int createReservation (Reservation reservation) {
		rsvtn_dao.create(reservation);
		return reservation.getId();
	}
	
	public Reservation findReservationById (int id) {
		Reservation reservation = rsvtn_dao.findById(id);
		return reservation;
	}
	
	public List<Reservation> findReservationByRestaurantId (int rid) {
		List<Reservation> reservation = rsvtn_dao.findByRestaurantId(rid);
		return reservation;
	}
	
	public List<Reservation> findReservationByUserName (String userName) {
		List<Reservation> reservation = rsvtn_dao.findByUserName(userName);
		return reservation;
	}
	
	public void removeReservationById (int id) {
		if(findReservationById(id) != null) rsvtn_dao.deleteById(id);
	}

}
