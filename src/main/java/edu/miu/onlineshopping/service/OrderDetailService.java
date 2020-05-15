package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailService {

    OrderDetail save(OrderDetail orderDetail);
}
