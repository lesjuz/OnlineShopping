package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.OrderItem;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductService productService;
    @Override
    public void setStatus(long sellerId, long orderId, String orderStatus) {
        OrderItem order = orderItemRepository.findBySellerAndId(sellerId, orderId);
        if(orderStatus.equals("CANCELED")){
            cancelOrder(sellerId,orderId);
        }
        else{
            order.setStatus(orderStatus.toUpperCase());
            save(order);
        }

    }

    @Override
    public void cancelOrder(long  buyerId, long orderId) {
        OrderItem order = orderItemRepository.findByBuyerAndId(buyerId, orderId);
        Long productId=order.getProduct().getId();
        Product product= productService.findById(productId);
        int newStock=product.getUnitsInStock()+order.getProductCount();
        order.setStatus("CANCELED");
        save(order);
        product.setUnitsInStock(newStock);
        productService.save(product);
    }

    public List<OrderItem> findBySeller(long id){
        return orderItemRepository.findBySeller(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
