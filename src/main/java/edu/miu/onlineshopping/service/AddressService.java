package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Address;

import java.util.List;

public interface AddressService {

    List<Address> listShippingAddresses(long id);
    Address getBillingAddress(long id);
    Address saveAddress(Address address);
    Address findById(long id);
}
