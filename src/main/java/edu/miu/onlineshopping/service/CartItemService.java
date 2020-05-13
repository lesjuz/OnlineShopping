package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.CartItem;
import edu.miu.onlineshopping.domain.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    List<CartItem> list(long cartId);
    CartItem get(long id);
    CartItem add(CartItem CartItem);
    void remove(CartItem cartItem);

    // fetch the CartItem based on cartId and productId
    public CartItem getByCartAndProduct(long cartId, long productId);


    // list of available CartItem
    List<CartItem> listAvailable(long cartId);

}
