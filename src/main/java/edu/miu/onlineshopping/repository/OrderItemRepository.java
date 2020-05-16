package edu.miu.onlineshopping.repository;


import edu.miu.onlineshopping.domain.OrderItem;
import edu.miu.onlineshopping.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
    OrderItem findByBuyerAndId(long buyer, long id);
    OrderItem findBySellerAndId(long seller,long id);
    List<OrderItem> findBySeller(long id);
    /*select o.id from order_item o left join product p on o.product_id=o.id where p.user_id=?*/
}
