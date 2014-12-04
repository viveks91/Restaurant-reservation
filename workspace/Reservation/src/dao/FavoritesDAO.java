/**
 * 
 */
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Favorites;
import models.Reviews;


/**
 * @author preetymishra
 *
 */
public class FavoritesDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Restaurant_Reservation");
	EntityManager em = null;
	
	public FavoritesDAO()	{
		em = factory.createEntityManager();
	}
	public Favorites create(Favorites favorites) {
		em.getTransaction().begin();
		em.persist(favorites);
		em.getTransaction().commit();
		return favorites;
	}
	
	public void deleteByUserNameAndFavorites(String userName, int restaurantId) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Favorites.deleteByUserNameAndFavorites");
		q.setParameter("userName", userName);
		q.setParameter("restaurantId", restaurantId);
		q.executeUpdate();
		em.getTransaction().commit();
	}
	
	public List<Favorites> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Favorites.findByUserName");
		q.setParameter("userName", userName);
		List<Favorites> favorites = q.getResultList();
		em.getTransaction().commit();
		return favorites;
	}
	
	
	public static void main(String[] args) {
		FavoritesDAO dao = new FavoritesDAO();
		//Favorites f1 = new Favorites("pre284", 1);
		//dao.create(f1);
	//	Favorites f2 = new Favorites("pre284", 2);
		//dao.create(f2);
	//	dao.deleteByUserNameAndFavorites("pre284", 1);
		List<Favorites> f3 = dao.findByUserName("pre284");
		System.out.println(f3);

	}

}
