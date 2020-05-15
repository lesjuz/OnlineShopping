package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class Address implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Please enter country")
    private String country;
    @NotBlank(message = "Please enter State!")
    private String state;
    @NotBlank(message = "Please enter City!")
    private String city;
    @NotBlank(message = "Please enter Postal Code!")
    private String zipCode;
    @NotBlank(message = "Please enter street!")
    private String street;
    @Column(name="is_shipping")
    private boolean shipping=false;
    @Column(name="is_billing")
    private boolean billing=false;

    @Column(name = "user_id")
    private Long userId;



}
