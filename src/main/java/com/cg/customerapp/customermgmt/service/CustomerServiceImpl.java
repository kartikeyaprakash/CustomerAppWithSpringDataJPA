package com.cg.customerapp.customermgmt.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.customerapp.customermgmt.dao.ICustomerDao;
import com.cg.customerapp.customermgmt.entities.Customer;
import com.cg.customerapp.customermgmt.exceptions.CustomerAlreadyExistsException;
import com.cg.customerapp.customermgmt.exceptions.CustomerNotFoundException;


@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDao dao;

	@Override
	public Customer register(Customer customer) {
        boolean exists=customer.getId()!=null && dao.existsById(customer.getId());
        if(exists){
            throw new CustomerAlreadyExistsException("customer already exists for id="+customer.getId());
        }
        customer = dao.save(customer);
		return customer;
	}

	@Override
	public Customer findById(Long id) {
		Optional<Customer> optional = dao.findById(id);
		if(!optional.isPresent())
			throw new CustomerNotFoundException("Can't find, customer not found for id="+id);
		return optional.get();
	}

	@Override
	public void deleteById(Long id) {
		Customer customer = findById(id);
		dao.delete(customer);
		
	}

	@Override
	public Customer update(Customer customer) {
		boolean exists=customer.getId()!=null && dao.existsById(customer.getId());
        if(!exists){
         throw new CustomerNotFoundException("Can't update, customer not found for id="+customer.getId());
        }
		return dao.save(customer);
	}

}
