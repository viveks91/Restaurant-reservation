package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Person;
import models.User;

public class UserDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Restaurant_Reservation");
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
	
	public User updateByUserName(String userName, int phoneNo, String emailId, int addressId) {
		em.getTransaction().begin();
		User user = em.find(User.class, userName);
		user.setPhoneNo(phoneNo);
		user.setEmailId(emailId);
		user.setAddressId(addressId);
		em.merge(user);
		em.getTransaction().commit();
		return user;
	}
	
	public User findByUserName(String userName) {
		em.getTransaction().begin();
		User user = em.find(User.class, userName);
		em.getTransaction().commit();
		return user;
	}
	
	
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		//User u1 = new User("pre2718","1233","ksjdfh","miss","asdsda",123231,1);
		//dao.createUser(u1);
		//dao.deleteByUserName("pre271");
		//dao.updateByUserName("pre2718", 2876, "jdhgf", 1);
		User u1 = dao.findByUserName("pre2718");
		System.out.println(u1);
	}

}
