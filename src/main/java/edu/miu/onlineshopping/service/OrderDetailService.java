package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.OrderDetail;
import edu.miu.onlineshopping.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailService {

    OrderDetail save(OrderDetail orderDetail);
    OrderDetail getOrderDetailByUser(User user,long id);

    List<OrderDetail> getMyOrders(User buyer);
}
