package edu.miu.onlineshopping.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private User user;
    private Address shipping;
    private Cart cart;
    private List<CartItem> cartItems;
    private OrderDetail orderDetail;
    private double checkoutTotal;


}