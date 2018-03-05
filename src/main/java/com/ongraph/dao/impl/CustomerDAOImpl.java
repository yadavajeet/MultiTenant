package com.ongraph.dao.impl;

import com.ongraph.dao.CustomerDAO;
import com.ongraph.model.Customer;
import com.ongraph.repositories.ICustomerRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl extends AbstractDao<Customer> implements CustomerDAO {
    @Autowired
    ICustomerRepository customerRepository;

    public CustomerDAOImpl(){super(Customer.class);}

    public Customer findCustomer(Long id) {
        Customer customer1 = customerRepository.findOne(id);
        return customer1;
    }

    @Override
    public Customer checkUserEmail(String email) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("email",email));
        return (Customer)criteria.uniqueResult();
    }

    @Override
    public void updateUser(Customer customer) {
        getSession().merge(customer);
    }

}
