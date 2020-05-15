package edu.miu.onlineshopping.service;

import edu.miu.onlineshopping.domain.Address;
import edu.miu.onlineshopping.domain.CheckoutModel;
import edu.miu.onlineshopping.domain.OrderDetail;

import java.util.List;

public interface CheckoutService {
    public CheckoutModel init(String name) throws Exception;
    List<Address> getShippingAddresses(CheckoutModel model);
    String saveAddressSelection(CheckoutModel checkoutModel, int shippingId);
    void saveAddress(CheckoutModel checkoutModel, Address shipping);
    CheckoutModel saveOrder(CheckoutModel checkoutModel,Address shipping);
    OrderDetail getOrderDetail(CheckoutModel checkoutModel);
}
