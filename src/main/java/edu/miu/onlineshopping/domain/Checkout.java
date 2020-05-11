package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Checkout implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private User user;
    private Address shipping;
    private Cart cart;
    private List<CartLine> cartLines;
    private OrderDetail orderDetail;
    private double checkoutTotal;


}