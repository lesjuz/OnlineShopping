package edu.miu.onlineshopping.repository;

import edu.miu.onlineshopping.domain.CartItem;
import edu.miu.onlineshopping.domain.OrderDetail;
import edu.miu.onlineshopping.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {


    OrderDetail findByUserAndId(User user,long id);
    List<OrderDetail> findAllByUser(User user);

}
