package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.OrderItem;
import edu.miu.onlineshopping.domain.User;

import java.util.List;

public interface OrderItemService {
    void setStatus(long seller, long orderId, String orderStatus) ;
    void cancelOrder(long buyer, long orderId);
    OrderItem save(OrderItem orderItem);
    List<OrderItem> findBySeller(long id);
}
