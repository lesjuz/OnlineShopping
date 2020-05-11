package edu.miu.onlineshopping.repository;


import edu.miu.onlineshopping.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    /*@Query(value = "Update PRODUCT set  WHERE e.F_NAME = ?1", nativeQuery = true)
    public Product updateProduct(Long id);*/
}
