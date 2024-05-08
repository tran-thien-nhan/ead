package beanClient;

import java.io.Serializable;
import java.util.List;

import beans.CategorySessionBeanLocal;
import entities.Category;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named(value = "CategoryManageBean")
@SessionScoped
public class CategoryManageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Category> categories;
	private Category category;
	
	@EJB
	private CategorySessionBeanLocal cateBeanLocal;
	
	@PostConstruct
	public void initialize() {
		categories = cateBeanLocal.findAll();
	}
	public String showFormCreate() {
		category = new Category();
		return "create?faces-redirect=true";
	}
	public String createCategory() {
		cateBeanLocal.addCategory(category);
		categories = cateBeanLocal.findAll();
		return "list?faces-redirect=true";
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}