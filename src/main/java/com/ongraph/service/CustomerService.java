package com.ongraph.service;

import com.ongraph.model.Customer;
import com.ongraph.response.Response;

public interface CustomerService {
    Customer findUser(Long l);

    void checkUserEmail(String email,String pass,Response response);

    void updateUser(Customer customer,Response response);
}
