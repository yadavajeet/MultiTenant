package com.ongraph.dao;

import com.ongraph.model.Customer;

public interface CustomerDAO {
    Customer findCustomer(Long id);

    Customer checkUserEmail(String email);

    void updateUser(Customer customer);
}
