package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Product;

import java.util.List;

public interface ProductService {
    public void save(Product product);
    public List<Product > findAll();
    public Product find(Long id);
    public void delete(Product p);
}
