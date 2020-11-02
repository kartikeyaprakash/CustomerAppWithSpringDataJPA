package com.cg.customerapp.customermgmt.service;

import com.cg.customerapp.customermgmt.entities.Customer;

public interface ICustomerService {

    Customer register(Customer customer);
	
    Customer findById(Long id);
	
	void deleteById(Long id);

	Customer update(Customer customer);

}
