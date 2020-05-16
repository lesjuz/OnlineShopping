package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.OrderDetail;
import edu.miu.onlineshopping.domain.User;
import edu.miu.onlineshopping.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

     @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailByUser(User user, long id) {
        return orderDetailRepository.findByUserAndId(user,id);
    }


    @Override
    public List<OrderDetail> getMyOrders(User user) {
        return orderDetailRepository.findAllByUser(user);
    }
}
