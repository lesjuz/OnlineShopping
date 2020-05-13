package edu.miu.onlineshopping.repository;


import edu.miu.onlineshopping.domain.Category;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAll();
    Product save(Product role);
    void deleteById(Long id);

    List<Product> findByCategory(Category category);
    @Query(value = "SELECT * FROM PRODUCT u WHERE u.SUPPLIER_USER_ID = ?1", nativeQuery = true)
    List<Product> findByUser(Long id);
}
