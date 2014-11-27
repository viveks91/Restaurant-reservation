package managers;

import java.io.Serializable;

import models.Category;
import dao.CategoryDAO;

public class CategoryManager implements Serializable {
	
	private CategoryDAO catgry_dao = new CategoryDAO();
	
	public Category createCategory (Category category) {
		if(this.findCategoryByType(category.getType()) == null)
			catgry_dao.create(category);
		return category;
	}
	
	public Category findCategoryByType (String type) {
		Category category = catgry_dao.findByType(type);
		return category;
	}
	
	public void removeCategoryByType (String type) {
		if(findCategoryByType(type) == null) return;
		catgry_dao.deleteByType(type);
	}
	
	public static void main(String[] args)
	{

		CategoryManager mgr = new CategoryManager();
//		Category ctr = new Category("Indian");
//		mgr.removeCategoryByType("Chinese");

	}

}
