package beans;

import java.util.List;

import abstracts.AbstractFacade;
import entities.Product;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class ProductSessionBean
 */
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
		return super.findAll();
	}



	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	public void createProduct(Product product) {
		super.create(product);	
	}

	//find one product
	@Override
	public Product findProduct(int id) {
		// TODO Auto-generated method stub
		return super.find(id);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		super.remove(super.find(id));
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		super.update(product);
	}

}
