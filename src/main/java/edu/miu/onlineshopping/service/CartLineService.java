package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.CartLine;
import edu.miu.onlineshopping.domain.OrderDetail;

import java.util.List;

public interface CartLineService {
    public List<CartLine> list(int cartId);
    public CartLine get(int id);
    public boolean add(CartLine cartLine);
    public boolean update(CartLine cartLine);
    public boolean remove(CartLine cartLine);

    // fetch the CartLine based on cartId and productId
    public CartLine getByCartAndProduct(int cartId, int productId);

    // updating the cart
    boolean updateCart(Cart cart);

    // list of available cartLine
    public List<CartLine> listAvailable(int cartId);

    // adding order details
    boolean addOrderDetail(OrderDetail orderDetail);
}
