package edu.miu.onlineshopping.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private double totalPrice;

        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private User user;

        @Column(name = "cart_lines")
        private int cartLines;

}