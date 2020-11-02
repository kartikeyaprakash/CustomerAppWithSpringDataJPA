package com.cg.customerapp.customermgmt.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {

	public CustomerAlreadyExistsException() {

	}

	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}
}
