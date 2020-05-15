package edu.miu.onlineshopping.repository;


import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.domain.CartItem;
import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
 public interface UserRepository extends CrudRepository<User, Long> {
   User findByEmail(String email);


}