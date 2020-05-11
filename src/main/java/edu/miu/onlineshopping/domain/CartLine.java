package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor

//@Entity
//@Table(name = "cart_line")
public class CartLine implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@OneToOne
    private Product product;
    //@Column(name = "cart_id")
    private int cartId;
    //@Column(name = "product_count")
    private int productCount;
    private double total;
    //@Column(name = "buying_price")
    private double buyingPrice;

    //@Column(name = "is_available")
    private boolean available = true;



}
