package beans;

import java.util.*;
import entities.Product;
import jakarta.ejb.Local;

@Local
public interface ProductSessionBeanLocal {
	List<Product> findAll();
	void createProduct(Product product);
	//deleteProduct
	void deleteProduct(int id);
	//find one product
	Product findProduct(int id);
	//update one product
	void updateProduct(Product product);
}
