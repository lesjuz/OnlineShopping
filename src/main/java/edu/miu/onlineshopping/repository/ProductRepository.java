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
    Product deleteById(long id);

    List<Product> findByCategory(Category category);
    @Query(value = "SELECT * FROM PRODUCT WHERE SUPPLIER_USER_ID = ?1 AND is_active=true", nativeQuery = true)
    List<Product> findByUser(Long id);

    @Query(value = "SELECT u.SUPPLIER_USER_ID FROM PRODUCT u WHERE u.id = ?1", nativeQuery = true)
    Long findBySupplierID(Long id);

    List<Product> findAllByActive(Boolean active);


}
