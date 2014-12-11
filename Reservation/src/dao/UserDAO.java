package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.User;

public class UserDAO implements Serializable {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
	EntityManager em = null;
	
	public UserDAO()	{
		em = factory.createEntityManager();
	}
	
	public User createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	public void deleteByUserName(String username) {
		em.getTransaction().begin();
		User user = em.find(User.class, username);
		em.remove(user);
		em.getTransaction().commit();
	}
	
	public String updateByUserName(String userName, String phoneNo, String emailId, int addressId) {
		em.getTransaction().begin();
		User user = em.find(User.class, userName);
		user.setPhoneNo(phoneNo);
		user.setEmailId(emailId);
		user.setAddressId(addressId);
		em.merge(user);
		em.getTransaction().commit();
		return user.getUserName();
	}
	
	public User findByUserName(String userName) {
		em.getTransaction().begin();
		User user = em.find(User.class, userName);
		em.getTransaction().commit();
		return user;
	}
	
/*	public List<Object[]> find(int aid){
		em.getTransaction().begin();
		Query q = em.createNamedQuery("User.testing");
		q.setParameter("aId", aid);
		List<Object[]> data = (List<Object[]>)q.getResultList();
		em.getTransaction().commit();
		return data;
	}*/

/*	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		User user = dao.findByUserName("asdda");
		System.out.println(user);
		
	}*/

}
