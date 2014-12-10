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
import models.Reviews;


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
	
	public static void main(String[] args) {
		FavoritesDAO dao = new FavoritesDAO();
		List<Object[]> obj = dao.findByUserName("p0");
		int i= 0;
		 for ( i = 0; i < obj.size(); i++) {
			    Object[] obj1 = obj.get(i);
			    String name = (String) obj1[0];
			    int ratings = (Integer) obj1[1];
			    String aptNo = (String) obj1[2];
			    String street = (String) obj1[3];
			    String city = (String) obj1[4];
			    String state = (String) obj1[5];
			    String zip = (String) obj1[6];
			    int id = (Integer) obj1[7];
			    System.out.println(name);
			    System.out.println(ratings);
			    System.out.println(aptNo);
			    System.out.println(street);
			    System.out.println(city);
			    System.out.println(state);
			    System.out.println(id);
			    }
		//Favorites f1 = new Favorites("pre284", 1);
		//dao.create(f1);
	//	Favorites f2 = new Favorites("pre284", 2);
		//dao.create(f2);
	//	dao.deleteByUserNameAndFavorites("pre284", 1);
		//List<Favorites> f3 = dao.findByUserName("pre284");
		

	}

}
