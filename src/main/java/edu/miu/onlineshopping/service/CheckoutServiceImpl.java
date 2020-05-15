package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpSession session;


    public CheckoutModel init(String name) throws Exception{

        User user = userService.findUserByEmail(name);
        CheckoutModel checkoutModel = null;

        if(user!=null) {
            checkoutModel = new CheckoutModel();
            checkoutModel.setUser(user);
            checkoutModel.setCart(user.getCart());

            double checkoutTotal = 0.0;
            List<CartItem> cartLines = cartItemService.listAvailable(user.getCart().getId());

            if(cartLines.size() == 0 ) {
                throw new Exception("There are no products available for checkout now!");
            }

            for(CartItem cartLine: cartLines) {
                checkoutTotal += cartLine.getTotal();
            }

            checkoutModel.setCartItems(cartLines);
            checkoutModel.setCheckoutTotal(checkoutTotal);
        }

        return checkoutModel;
    }


    public List<Address> getShippingAddresses(CheckoutModel model) {

        List<Address> addresses = addressService.listShippingAddresses(model.getUser().getId());

        if(addresses.size() == 0) {
            addresses = new ArrayList<Address>();
        }

        addresses.add(addresses.size(),addressService.getBillingAddress(model.getUser().getId()));

        return addresses;

    }

    public String saveAddressSelection(CheckoutModel checkoutModel, int shippingId) {

        String transitionValue = "success";

        //logger.info(String.valueOf(shippingId));

        Address shipping = addressService.findById(shippingId);

        checkoutModel.setShipping(shipping);

        return transitionValue;

    }


    public void saveAddress(CheckoutModel checkoutModel, Address shipping) {

        String transitionValue = "success";

        // set the user id
        // set shipping as true
        shipping.setUserId(checkoutModel.getUser().getId());
        shipping.setShipping(true);
        Address newAddress=addressService.saveAddress(shipping);

        checkoutModel.setShipping(newAddress);




    }


    public CheckoutModel saveOrder(CheckoutModel checkoutModel,Address shipping) {
        String transitionValue = "success";

        // create a new order object
        OrderDetail orderDetail = new OrderDetail();

        // attach the user
        orderDetail.setUser(checkoutModel.getUser());

        // attach the shipping address
        checkoutModel.setShipping(shipping);
        orderDetail.setShipping(shipping);


        // fetch the billing address
        Address billing = addressService.getBillingAddress(checkoutModel.getUser().getId());
        orderDetail.setBilling(billing);

        List<CartItem> cartLines = checkoutModel.getCartItems();
        OrderItem orderItem = null;

        double orderTotal = 0.0;
        int orderCount = 0;
        Product product = null;

        for(CartItem cartLine : cartLines) {

            orderItem = new OrderItem();

            orderItem.setBuyingPrice(cartLine.getBuyingPrice());
            orderItem.setProduct(cartLine.getProduct());
            orderItem.setProductCount(cartLine.getProductCount());
            orderItem.setTotal(cartLine.getTotal());

            orderItem.setOrderDetail(orderDetail);
            orderDetail.getOrderItems().add(orderItem);

            orderTotal += orderItem.getTotal();
            orderCount++;

            // update the product
            // reduce the quantity of product
            product = cartLine.getProduct();
            product.setUnitsInStock(product.getUnitsInStock() - cartLine.getProductCount());
            product.setPurchases(product.getPurchases() + cartLine.getProductCount());
            productService.save(product);

            // delete the cartLine
            cartItemService.remove(cartLine);



        }

        orderDetail.setOrderTotal(orderTotal);
        orderDetail.setOrderCount(orderCount);
        orderDetail.setOrderDate(new Date());

        // save the order
        orderDetailService.save(orderDetail);

        // set it to the orderDetails of checkoutModel
        checkoutModel.setOrderDetail(orderDetail);


        // update the cart
        Cart cart = checkoutModel.getCart();
        cart.setTotalPrice(cart.getTotalPrice() - orderTotal);
        cart.setCartLines(cart.getCartLines() - orderCount);
        cartService.saveCart(cart);

        // update the cart if its in the session
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        if(userModel!=null) {
            userModel.setCart(cart);
        }

        return checkoutModel;
    }


    public OrderDetail getOrderDetail(CheckoutModel checkoutModel) {
        return checkoutModel.getOrderDetail();
    }



}


