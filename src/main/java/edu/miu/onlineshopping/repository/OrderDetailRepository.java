package edu.miu.onlineshopping.repository;

import edu.miu.onlineshopping.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long> {

}
