package com.ongraph.controller;

import com.ongraph.model.Customer;
import com.ongraph.request.LoginRequest;
import com.ongraph.response.Response;
import com.ongraph.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/login/{id}")
    public Customer login(@PathVariable Long id){
        Customer customer = customerService.findUser(id);
        return customer;
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        Response response = new Response();
        customerService.checkUserEmail(loginRequest.getEmail(),loginRequest.getPassword(),response);
        httpServletResponse.setStatus(response.getStatus().value());
        return response;
    }
    @PutMapping(value = "/userUpdate",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response userUpdate(@RequestBody Customer customer,HttpServletResponse httpServletResponse){
        Response response = new Response();
        customerService.updateUser(customer,response);
        httpServletResponse.setStatus(response.getStatus().value());
        return response;
    }
}
