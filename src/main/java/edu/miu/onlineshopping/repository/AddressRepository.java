package edu.miu.onlineshopping.repository;

import edu.miu.onlineshopping.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value="SELECT * FROM Address WHERE user_id =?1 AND is_shipping = true ORDER BY id DESC",nativeQuery=true)
    List<Address> listShippingAddresses(long id);

    @Query(value="SELECT * FROM Address WHERE user_id =?1  AND is_billing = true",nativeQuery=true)
    Address getBillingAddress(long id);
}
