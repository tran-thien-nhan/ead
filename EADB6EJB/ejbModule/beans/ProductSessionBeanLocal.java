package beans;

import jakarta.ejb.*;
import java.util.*;

import entities.Product;

@Local
public interface ProductSessionBeanLocal {
    List<Product> findAll();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product findOne(Long id);
}
