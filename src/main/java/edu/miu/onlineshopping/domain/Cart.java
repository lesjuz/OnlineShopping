package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor

@Entity
public class Cart implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @Column(name = "grand_total")
        private double grandTotal;
        @Column(name = "cart_lines")
        private int cartLines;

        @OneToOne
        private User user;


    }

