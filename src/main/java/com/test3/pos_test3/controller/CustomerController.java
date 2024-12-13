package com.test3.pos_test3.controller;

import com.test3.pos_test3.dto.CustomerDTO;
import com.test3.pos_test3.dto.request.CustomerUpdateDTO;
import com.test3.pos_test3.repo.CustomerRepo;
import com.test3.pos_test3.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
       // CustomerServiceIMPL customerServiceIMPL = new CustomerServiceIMPL();
       // customerServiceIMPL.saveCustomer(customerDTO);
        customerService.saveCustomer(customerDTO);
        return "saved";
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String  msg=customerService.updateCustomer(customerUpdateDTO);
        return msg;
    }

    @GetMapping(
            path="/get-by-id",
            params = "id"

    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {
        CustomerDTO customerDTO=customerService.getCustomerById(customerId);
        return customerDTO ;
    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
}
