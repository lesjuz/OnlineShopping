package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter

public class UserModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private String role;
    private Cart cart;
}