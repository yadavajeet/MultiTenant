package com.ongraph.service.impl;

import com.ongraph.model.Customer;
import com.ongraph.dao.CustomerDAO;
import com.ongraph.response.Response;
import com.ongraph.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDAO customerDAO;

    public Customer findUser(Long id){
        return customerDAO.findCustomer(id);
    }

    @Override
    public void checkUserEmail(String email, String pass,Response response) {
        try{
            Customer customer = customerDAO.checkUserEmail(email);
            if(customer == null){
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setSuccess(false);
                response.setMessage("Email is not exist");
            }else if(!customer.getPassword().equals(pass)){
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setSuccess(false);
                response.setMessage("Password is not correct");
            }else {
                response.setData(customer);
                response.setMessage("User login successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(Customer customer, Response response) {
        try {
            Customer customer1 = customerDAO.findCustomer(customer.getId());
            if(null == customer1){
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setSuccess(false);
                response.setMessage("User not found");
            }else {
                customer1.setEmail(customer.getEmail());
                customer1.setFirstName(customer.getFirstName());
                customer1.setLastName(customer.getLastName());
                customer1.setPassword(customer.getPassword());
                customerDAO.updateUser(customer1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
