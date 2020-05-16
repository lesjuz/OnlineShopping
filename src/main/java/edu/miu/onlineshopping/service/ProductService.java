package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Product findById(Long id);
    Product findByName(String name);
    List<Product> findAll();
    Product save(Product role);

    List<Product> findByCategory(Category category);
    List<Product> findByUser(Long id);
    Long findBySupplierID(Long id);
    Product updateProduct(Long id, Product product);
    Product deleteProduct(Long id);
    void updatedProductImage(long productId, String imgFileName);
}
