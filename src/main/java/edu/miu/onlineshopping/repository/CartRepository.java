package edu.miu.onlineshopping.repository;


import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {


}
