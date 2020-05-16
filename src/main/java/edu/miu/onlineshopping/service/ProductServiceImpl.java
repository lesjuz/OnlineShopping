package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.repository.ProductRepository;
import edu.miu.onlineshopping.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements  ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAllByActive(true);
    }

    @Override
    public Product save(Product product) {

        return productRepository.save(product);
    }



    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByUser(Long id) {
        return productRepository.findByUser(id);
    }

    @Override
    public Long findBySupplierID(Long id) {
        return productRepository.findBySupplierID(id);
    }


    @Override
    public Product updateProduct(Long id, Product product)  {
        Product prod = productRepository.findById(id).get();
        prod.setCategory(product.getCategory());
        prod.setName(product.getName());
        prod.setUnitPrice(product.getUnitPrice());
        prod.setUnitsInStock(product.getUnitsInStock());
        prod.setDescription(product.getDescription());
        return productRepository.save(prod);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        if(product.getUnitsInStock()>0){
            product.setActive(false);
            return productRepository.save(product);
        }
        return  null;

    }

    @Override
    public void updatedProductImage(long productId, String imgFileName) {
        Product product = productRepository.findById(productId).get();
        product.setImage(imgFileName);
        productRepository.save(product);
    }
}
