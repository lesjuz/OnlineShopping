package edu.miu.onlineshopping.service;


import edu.miu.onlineshopping.domain.*;
import edu.miu.onlineshopping.repository.CartItemRepository;
import edu.miu.onlineshopping.repository.CartRepository;
import edu.miu.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	private HttpSession session;


	public void saveCart(Cart cart){
		 cartRepository.save(cart);
	}

	public List<CartItem> getCartItems() {

		return cartItemService.list(getCart().getId());

	}

	/* to update the cart count */
	public String manageCartItem(long cartLineId, int count) {

		CartItem cartLine = cartItemService.get(cartLineId);

		double oldTotal = cartLine.getTotal();


		Product product = cartLine.getProduct();

		// check if that much quantity is available or not
		if(product.getUnitsInStock() < count) {
			return "result=unavailable";
		}

		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartItemService.add(cartLine);


		// update the cart
		Cart cart = this.getCart();
		cart.setTotalPrice(cart.getTotalPrice() - oldTotal + cartLine.getTotal());
		this.saveCart(cart);

		return "result=updated";
	}


@Override
	public String addCartItem(long productId) {
		Cart cart = this.getCart();
		String response = null;
		CartItem cartLine = cartItemService.getByCartAndProduct(cart.getId(), productId);
		if(cartLine==null) {
			// add a new cartLine if a new product is getting added

			cartLine = new CartItem();
			Product product = productService.findById(productId).get();
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice());

			// insert a new cartLine
			cartItemService.add(cartLine);

			// update the cart
			cart.setTotalPrice(cart.getTotalPrice() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			this.saveCart(cart);

			response = "added";
		}
		else {
			// check if the cartLine has been already reached to maximum count
			if(cartLine.getProductCount() < 3) {
				// call the manageCartItem method to increase the count
				response = this.manageCartItem(cartLine.getId(), cartLine.getProductCount() + 1);
			}
			else {
				response = "maximum";
			}
		}
		return response;
	}


	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}

	@Override
	public String removeCartItem(int cartLineId) {

		CartItem cartLine = cartItemService.get(cartLineId);
		// deduct the cart
		// update the cart
		Cart cart = this.getCart();
		cart.setTotalPrice(cart.getTotalPrice() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		this.saveCart(cart);

		// remove the cartLine
		cartItemService.remove(cartLine);

		return "deleted";
	}

	@Override
	public String validateCartItem() {
		Cart cart = this.getCart();
		List<CartItem> cartLines = cartItemService.list(cart.getId());
		double grandTotal = 0.0;
		int lineCount = 0;
		String response = "success";
		boolean changed = false;
		Product product = null;
		for(CartItem cartLine : cartLines) {
			product = cartLine.getProduct();
			changed = false;
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if((!product.isActive() && product.getUnitsInStock() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity available
			if((product.isActive() && product.getUnitsInStock() > 0) && !(cartLine.isAvailable())) {
				cartLine.setAvailable(true);
				changed = true;
			}

			// check if the buying price of product has been changed
			if(cartLine.getBuyingPrice() != product.getUnitPrice()) {
				// set the buying price to the new price
				cartLine.setBuyingPrice(product.getUnitPrice());
				// calculate and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
			}

			// check if that much quantity of product is available or not
			if(cartLine.getProductCount() > product.getUnitsInStock()) {
				cartLine.setProductCount(product.getUnitsInStock());
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;

			}

			// changes has happened
			if(changed) {
				//update the cartLine
				cartItemService.add(cartLine);
				// set the result as modified
				response = "modified";
			}

			grandTotal += cartLine.getTotal();
			lineCount++;
		}

		cart.setCartLines(lineCount++);
		cart.setTotalPrice(grandTotal);
		this.saveCart(cart);

		return response;
	}


}