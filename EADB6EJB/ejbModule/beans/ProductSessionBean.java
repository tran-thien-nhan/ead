package beans;

import jakarta.ejb.*;
import jakarta.persistence.*;

import java.util.*;

import abstracts.AbstractFacade;
import entities.Product;

@Stateless(mappedName = "ProductSessionBean")
@LocalBean
public class ProductSessionBean extends AbstractFacade<Product> implements ProductSessionBeanLocal {

	@PersistenceContext(unitName = "ProductShopEjb")
	private EntityManager em;
    public ProductSessionBean() {
    	super(Product.class);
    }

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		super.create(product);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		super.update(product);
	}

	@Override
	public void deleteProduct(Product product) {
		super.remove(product);
	}

	@Override
	public Product findOne(Long id) {
		// TODO Auto-generated method stub
		return super.findOne(id);
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
