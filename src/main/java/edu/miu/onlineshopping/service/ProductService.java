package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAll();
    Product save(Product role);
    void deleteById(Long id);

    List<Product> findByCategory(Category category);
    List<Product> findByUser(Long id);
}
