/*package edu.miu.onlineshopping.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;*/

/*@Getter
@Setter
@Entity*/
/*
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private OrderItem orderItem;


    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User buyer;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;
    @Min(value = 1)
    @Max(value = 5)
    private int stars;
    @NotBlank(message = "Please enter your comment")
    private String comment;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date decision;
}*/
