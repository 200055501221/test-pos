package com.test3.pos_test3.service.impl;

import com.test3.pos_test3.controller.CustomerController;
import com.test3.pos_test3.dto.CustomerDTO;
import com.test3.pos_test3.dto.request.CustomerUpdateDTO;
import com.test3.pos_test3.entity.Customer;

import java.util.List;

public interface CustomerService {
public String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);
    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    List<CustomerDTO> getAllCustomersByActiveState(boolean activeState);
}
