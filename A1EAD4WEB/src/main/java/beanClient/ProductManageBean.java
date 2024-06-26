package beanClient;

import java.io.Serializable;
import java.util.*;

import beans.ProductSessionBeanLocal;
import entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named(value = "ProductManageBean")
@SessionScoped
public class ProductManageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Product> products;
	private Product product;
	
	@EJB
	private ProductSessionBeanLocal beanLocal;
	@PostConstruct
	public void initialize() {
		products = beanLocal.findAll();
	}
	
	public String showFormCreate() {
		product = new Product();
		return "create";
	}
	
	public String createProduct() {
		beanLocal.createProduct(product);
		products = beanLocal.findAll();
		return "list?faces-redirect=true";
	}

	//deleteProduct
	public String deleteProduct(int id) {
		beanLocal.deleteProduct(id);
		products = beanLocal.findAll();
		return "list?faces-redirect=true";
	}

	//show form edit product
	public String showFormEdit(int id) {
		product = beanLocal.findProduct(id);
		return "edit";
	}

	//update product
	public String updateProduct() {
		beanLocal.updateProduct(product);
		products = beanLocal.findAll();
		return "list?faces-redirect=true";
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
