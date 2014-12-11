package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Person;
import models.Reviews;

public class ReviewsDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Reservation");
	EntityManager em = null;
	
	public ReviewsDAO()	{
		em = factory.createEntityManager();
	}
	
	public Reviews createReviews(Reviews reviews) {
		em.getTransaction().begin();
		em.persist(reviews);
		em.getTransaction().commit();
		return reviews;
	}
	
	public void deleteByReviewId(int id) {
		em.getTransaction().begin();
		Reviews reviews = em.find(Reviews.class, id);
		em.remove(reviews);
		em.getTransaction().commit();
	}
	
	public List<Reviews> deleteByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.deleteReviewByUserName");
		q.setParameter("userName", userName);
		List<Reviews> reviews = q.getResultList();
		em.getTransaction().commit();
		return reviews;
	}
	
	/*public Reviews updateByReviewId(int id, String newComment) {
		em.getTransaction().begin();
		Reviews reviews = em.find(Reviews.class, id);
		reviews.setComments(newComment);
		em.merge(reviews);
		em.getTransaction().commit();
		return reviews;
	}
	
	public List<Reviews> updateByUserName(String userName, String newComment) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.updateCommentByUserName");
		q.setParameter("userName", userName);
		q.setParameter("comments", newComment);
		q.executeUpdate();
		em.getTransaction().commit();
		return null;
	}*/
	
	
	public Reviews findByReviewId(int id) {
		em.getTransaction().begin();
		Reviews reviews = em.find(Reviews.class, id);
		em.getTransaction().commit();
		return reviews;
	}
	
	public List<Reviews> findByUserName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.findAllReviewsByUserName");
		q.setParameter("userName", userName);
		List<Reviews> reviews = q.getResultList();
		em.getTransaction().commit();
		return reviews;
	}
	
	public List<Object[]> findAllReviewsWithRestaurantName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.findAllReviewsWithRestaurantName");
		q.setParameter("userName", userName);
		List<Object[]> reviews = q.getResultList();
		em.getTransaction().commit();
		return reviews;
	}
	
	public List<Object[]> findFriendsReviewsWithRestaurantName(String userName) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.findFriendsReviewsWithRestaurantName");
		q.setParameter("username", userName);
		List<Object[]> reviews = q.getResultList();
		em.getTransaction().commit();
		return reviews;
	}
	
	public List<Reviews> findByRestaurantId(int restaurantId) {
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Reviews.findAllReviewsByRestaurantId");
		q.setParameter("restaurantId", restaurantId);
		List<Reviews> reviews = q.getResultList();
		em.getTransaction().commit();
		return reviews;
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		ReviewsDAO dao = new ReviewsDAO();
		List<Object[]> revs = dao.findAllReviewsWithRestaurantName("p0");
		for(int i=0;i< revs.size();i++)
		{
		Object[] obj = revs.get(i);
		String name = (String) obj[0];
		String reviews = (String) obj[1];
		System.out.println(name);
		System.out.println(reviews);
		}
		/*Reviews r1 = new Reviews ("pre27361",2, "dsfsfd", new Date(), 1);
		Reviews r2 = new Reviews ("pre2736", 6, "cvxcv", new Date(), 1);
		
		dao.createReviews(r1);
		dao.createReviews(r2);*/
		//dao.updateByUserName("pre27", "very nice");
		//List<Reviews> r5 = dao.findByUserName("pre2736");
		
	}

}
