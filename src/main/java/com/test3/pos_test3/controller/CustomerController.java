package com.test3.pos_test3.controller;

import com.test3.pos_test3.dto.CustomerDTO;
import com.test3.pos_test3.dto.request.CustomerUpdateDTO;
import com.test3.pos_test3.repo.CustomerRepo;
import com.test3.pos_test3.service.impl.CustomerService;
import com.test3.pos_test3.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping(
//            path = "/get-all-customers"
//    )
//    public List<CustomerDTO> getAllCustomers() {
//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return allCustomers;
//    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",allCustomers), HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int customerId) {
        String deleted=customerService.deleteCustomer(customerId);
        return deleted;
    }

    @GetMapping(
            path = "/get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value ="status")boolean activeState) {
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }

}
