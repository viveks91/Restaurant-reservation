/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Favorites;


/**
 * @author preetymishra
 *
 */
public class FavoritesDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
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
	
	/*public List<Favorites> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Favorites.findByUserName");//FavoritesRestaurants.findByUserName
		q.setParameter("userName", userName);
		List<Favorites> favorites = q.getResultList();
		em.getTransaction().commit();
		return favorites;
	}*/
	
	public List<Object[]> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("FavoritesRestaurants.findByUserName");
		q.setParameter("userName", userName);
		List<Object[]> favorites = q.getResultList();
	//	favorites.add(q.getFirstResult());
		em.getTransaction().commit();
		return favorites;
	}
	
	public boolean isFav(String userName, int rId) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("FavoritesRestaurants.checkByUserName");
		q.setParameter("userName", userName);
		q.setParameter("rId", rId);
		List<Favorites> favorites = q.getResultList();
		em.getTransaction().commit();
		if (favorites.isEmpty()) return false;
		else return true;
	}
	
/*	public static void main(String[] args) {
		FavoritesDAO dao = new FavoritesDAO();
		List<Integer> fav = dao.findByUserName("vivek");
		System.out.println(fav.size());

		//Favorites f1 = new Favorites("pre284", 1);
		//dao.create(f1);
	//	Favorites f2 = new Favorites("pre284", 2);
		//dao.create(f2);
	//	dao.deleteByUserNameAndFavorites("pre284", 1);
		//List<Favorites> f3 = dao.findByUserName("pre284");
	}*/

}
