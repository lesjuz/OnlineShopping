package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> listShippingAddresses(long id) {
        return addressRepository.listShippingAddresses(id);
    }

    @Override
    public Address getBillingAddress(long id) {
        return addressRepository.getBillingAddress(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address) ;
    }

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).get();
    }
}
