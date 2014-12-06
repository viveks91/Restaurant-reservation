package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.User;

public class UserDAO implements Serializable {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
	EntityManager em = null;
	
	public UserDAO()	{
		em = factory.createEntityManager();
	}
	
	public String createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user.getUserName();
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
	
	
	
/*	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//User u1 = new User("pre718","1233","ksjdfh","miss","asdsda",123231,1);
		//dao.createUser(u1);
		//dao.deleteByUserName("pre271");
		//dao.updateByUserName("pre2718", 2876, "jdhgf", 1);
		System.out.println(dao.findByUserName("vvek91"));
	}*/

}
