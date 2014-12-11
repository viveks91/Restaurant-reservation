/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Following;
import models.Reviews;


/**
 * @author preetymishra
 *
 */
public class FollowingDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
	EntityManager em = null;
	
	public FollowingDAO()	{
		em = factory.createEntityManager();
	}
	public Following create(Following following) {
		em.getTransaction().begin();
		em.persist(following);
		em.getTransaction().commit();
		return following;
	}
	
	public void deleteByUserNameAndFollowing(String userName, String following) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Following.deleteByUserNameAndFollowing");
		q.setParameter("userName", userName);
		q.setParameter("following", following);
		q.executeUpdate();
		em.getTransaction().commit();
	}
	
	public List<Following> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Following.findByUserName");
		q.setParameter("userName", userName);
		List<Following> following = q.getResultList();
		em.getTransaction().commit();
		return following;
	}
	
	public boolean findBy2UserName(String userName1, String userName2) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Following.findBy2UserName");
		q.setParameter("userName1", userName1);
		q.setParameter("userName2", userName2);
		List<Following> following = (List<Following>)q.getResultList();
		em.getTransaction().commit();
		boolean a;
		if (following.isEmpty()) a=false;
		else a=true;
		return a;
	}
	
	
/*	public static void main(String[] args) {
		FollowingDAO dao = new FollowingDAO();
		Following f1 = new Following("pre284", "pre2718");
		dao.create(f1);
		Following f2 = new Following("pre284", "pre2712");
		dao.create(f2);
		//dao.deleteByUserNameAndFollowing("pre284", "pre2718");
		boolean f3 = dao.findBy2UserName("vivek","vidhya");
		System.out.println(f3);

	}*/

}
