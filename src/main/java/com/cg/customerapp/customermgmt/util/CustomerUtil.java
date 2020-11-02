package com.cg.customerapp.customermgmt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.customerapp.customermgmt.dto.CustomerDetails;
import com.cg.customerapp.customermgmt.entities.Customer;

@Component
public class CustomerUtil {

	public List<CustomerDetails> toDetails(Collection<Customer> customers) {
		List<CustomerDetails> desired = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDetails details = toDetails(customer);
			desired.add(details);
		}
		return desired;
	}

	public CustomerDetails toDetails(Customer customer) {
		return new CustomerDetails(customer.getId(), customer.getFirstName(), customer.getLastName());
	}

}
