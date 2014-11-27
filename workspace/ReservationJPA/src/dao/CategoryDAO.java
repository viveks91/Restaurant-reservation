package dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Category;

public class CategoryDAO implements Serializable {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ReservationJPA");
	EntityManager em = null;
	
	public CategoryDAO()	{
		em = factory.createEntityManager();
	}
	
	public Category create(Category type) {
		em.getTransaction().begin();
		em.persist(type);
		em.getTransaction().commit();
		return type;
	}
	
	public void deleteByType(String type) {
		em.getTransaction().begin();
		Category found_type = em.find(Category.class, type);
		em.remove(found_type);
		em.getTransaction().commit();
	}
	
	public Category findByType(String type) {
		em.getTransaction().begin();
		Category found_type = em.find(Category.class, type);
		em.getTransaction().commit();
		return found_type;
	}
}
