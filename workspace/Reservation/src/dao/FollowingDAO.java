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
	
	
	public static void main(String[] args) {
		FollowingDAO dao = new FollowingDAO();
		/*Following f1 = new Following("pre284", "pre2718");
		dao.create(f1);
		Following f2 = new Following("pre284", "pre2712");
		dao.create(f2);
		//dao.deleteByUserNameAndFollowing("pre284", "pre2718");*/
		List<Following> f3 = dao.findByUserName("pre284");
		System.out.println(f3);

	}

}
