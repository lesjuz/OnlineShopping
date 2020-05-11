package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.repository.ProductRepository;
import edu.miu.onlineshopping.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public void save(Product product) {
        productRepository.save(product);

    }
    @Override
    public Product find(Long id) {
       return  productRepository.findById(id).get();
    }
    @Override
    public void delete(Product p) {
        //System.out.println("################"+id);
        productRepository.delete(p);
    }

    @Override
    public List<Product> findAll() {
        return  Util.iterableToCollection(productRepository.findAll());
    }
}
