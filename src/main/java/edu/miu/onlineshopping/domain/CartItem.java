package edu.miu.onlineshopping.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Product product;
    @Column(name = "cart_id")
    private Long cartId;
    @Column(name = "product_count")
    private int productCount;
    private double total;
    @Column(name = "buying_price")
    private double buyingPrice;
    @Column(name = "is_available")
    private boolean available = true;
}