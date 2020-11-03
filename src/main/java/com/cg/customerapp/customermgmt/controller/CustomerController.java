package com.cg.customerapp.customermgmt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.customerapp.customermgmt.dto.CreateCustomerRequest;
import com.cg.customerapp.customermgmt.dto.CustomerDetails;
import com.cg.customerapp.customermgmt.dto.UpdateCustomerRequest;
import com.cg.customerapp.customermgmt.entities.Customer;
import com.cg.customerapp.customermgmt.service.ICustomerService;
import com.cg.customerapp.customermgmt.util.CustomerUtil;

@Validated
@RequestMapping("/customer")
@RestController
public class CustomerController {

	@Autowired
	private ICustomerService service;

	@Autowired
	private CustomerUtil customerUtil;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public CustomerDetails add(@RequestBody  @Valid CreateCustomerRequest requestData)
	{
		Customer customer = service.register(new Customer(requestData.getFirstName(), requestData.getLastName()));
		return customerUtil.toDetails(customer);
	}
	
	@PutMapping("/update")
	public CustomerDetails update(@RequestBody UpdateCustomerRequest requestData)
	{
		Customer customer = new Customer(requestData.getFirstName(), requestData.getLastName());
		customer.setId(requestData.getId());
		customer = service.update(customer);
		return customerUtil.toDetails(customer);
	}
	
	@GetMapping("/get/id/{id}")
	public CustomerDetails fetchCustomer(@PathVariable("id") Long id)
	{
		Customer customer = service.findById(id);
		return customerUtil.toDetails(customer);
	}
	
	@DeleteMapping("/remove/{id}")
	public String removeCustomer(@PathVariable("id") Long id)
	{
		service.deleteById(id);
        return ("removed customer with id=" + id);

	}
	
	

}
