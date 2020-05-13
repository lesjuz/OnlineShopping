package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.CartItem;
import edu.miu.onlineshopping.domain.OrderDetail;
import edu.miu.onlineshopping.domain.Product;
import edu.miu.onlineshopping.repository.CartItemRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem getByCartAndProduct(long cartId, long productId) {
       return cartItemRepository.getByCartAndProduct(cartId,productId);
    }

    @Override
    public CartItem add(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
       cartItemRepository.delete(cartItem);
    }


    @Override
    public List<CartItem> list(long cartId) {
        return cartItemRepository.list(cartId);
    }

    @Override
    public CartItem get(long id) {
        return cartItemRepository.findById(id).get();
    }


    @Override
    public List<CartItem> listAvailable(long cartId) {
        return cartItemRepository.listAvailable(cartId);
    }


}

