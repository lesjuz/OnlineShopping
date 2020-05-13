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

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    private int quantity;
    private BigDecimal totalItemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Cart cart;

    public BigDecimal calculateItemCost(){
        product.setUnitsInStock(product.getUnitsInStock() - quantity);
        totalItemPrice = product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        return totalItemPrice;
    }*/
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