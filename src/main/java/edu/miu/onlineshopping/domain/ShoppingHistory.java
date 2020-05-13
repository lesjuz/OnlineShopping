package edu.miu.onlineshopping.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "shopping_history")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shopping_details;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User owner;
}