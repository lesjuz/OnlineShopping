package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.CartItem;
import edu.miu.onlineshopping.domain.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
	List<CartItem> getCartItems();
	String manageCartItem(long cartLineId, int count);
	String addCartItem(long productId);
	String removeCartItem(int cartLineId);
	String validateCartItem();
	void saveCart(Cart cart);
}
