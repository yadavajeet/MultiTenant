package com.ongraph.config;

import com.ongraph.dao.CustomerDAO;
import com.ongraph.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component(value = "userDetailService")
public class UserService implements UserDetailsService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Inside UserDetailService "+email);
        Customer user = customerDAO.checkUserEmail(email);
        if (email == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getFirstName(),
                user.getPassword(),mapRolesToAuthorities(new ArrayList<Customer>()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Customer> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getFirstName()))
                .collect(Collectors.toList());
    }
}
