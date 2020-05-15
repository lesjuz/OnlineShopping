package edu.miu.onlineshopping.repository;

import edu.miu.onlineshopping.domain.Cart;
import edu.miu.onlineshopping.domain.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {


    @Query(value ="SELECT * FROM CART_ITEMS c WHERE c.cart_Id =?1 AND c.product_Id = ?2",nativeQuery = true)
    CartItem getByCartAndProduct(long cartId, long productId);

    @Query(value="SELECT * FROM CART_ITEMS WHERE cart_Id =?1",nativeQuery=true)
    List<CartItem> listAvailable(long cartId);

    @Query(value="SELECT * FROM CART_ITEMS WHERE cart_Id =?1 AND is_available = true",nativeQuery=true)
    List<CartItem> list(long cartId);


}
