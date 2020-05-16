package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;


    @Column (name = "buying_price")
    private double buyingPrice;

    @Column (name = "product_count")
    private int productCount;

    private double total;

    private String status;


    private Long seller;

    private Long buyer;

    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date orderDate;

    public boolean isStatusCreated(){
        return status.equals("CREATED");
    }
    public boolean isStatusDelivered(){
        return status.equals("DELIVERED");
    }

    public boolean isStatusShipped(){
        return status.equals("SHIPPED");
    }


}
