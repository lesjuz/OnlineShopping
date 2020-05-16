package edu.miu.onlineshopping.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "not empty")
    private String name;
    @NotEmpty(message = "cannot be null")
    private String description;

    private String image;

    @Transient
    private MultipartFile imageFile;


    @ManyToOne
    @JoinColumn
    @NotNull
    private Category category;

    @NotNull(message = "not null")
    @Column(name = "price")
    @Min(value=1)
    private double unitPrice;
    @NotNull(message = "Please Select quantity")
    @Min(value = 1)
    private int unitsInStock;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private CartItem cartItem;

    @Column(name = "is_active")
    private boolean active=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User supplier;

    private int purchases=0;

    public boolean isActive(){
        return this.active;
    }
}

