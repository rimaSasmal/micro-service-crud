package com.infy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Account;
import com.infy.model.Customer;
import com.infy.service.AccountCustomerService;

@RestController
@RequestMapping("/")
public class AccountCustomerAPI {

	@Autowired
	AccountCustomerService accountCustomerService;

	@Autowired
	Environment environment;
	
	@GetMapping(value = "/{customerId}")
	public ResponseEntity<Customer> getCustomerWithCardDetails(@PathVariable Integer customerId) throws Exception {
		
		try {
		Integer totalBal=0;
		Customer customer = accountCustomerService.getCustomerDetails(customerId);
		System.out.println("******Customer Details*****");
		System.out.println("Customer ID :" + customer.getCustomerId());
		System.out.println("Name :" + customer.getName());
		System.out.println("Email ID :" + customer.getEmailId());
		System.out.println("******Card Details******");
		if (!customer.getAccounts().isEmpty()) {
			for (Account account : customer.getAccounts()) {
				System.out.println("Account Id :" + account.getAcId());
				System.out.println("Account Number:" + account.getAcNumber());
				System.out.println("Creation Date :" + account.getCreationDate() + "\n");
				System.out.println("Account balance:"+account.getBalance());
				totalBal+=account.getBalance();
			}
			System.out.println("total balance :"+totalBal);
		} else {
			System.out.println(environment.getProperty("UserInterface.NO_ACCOUNTS"));
		}
		ResponseEntity<Customer> response = new ResponseEntity<>(customer, HttpStatus.OK);
		return response;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
		
	
    @PostMapping(value = "/customer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws Exception  {
    	try {
    	accountCustomerService.addCustomer(customer);
		System.out.println("\n" + environment.getProperty("UserInterface.ACCOUNT_AND_CUSTOMER_ADDED"));
		String successMessage = "Customer added successfully";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
		return response;
    	}
    	catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(), e);
    	}
	}
    
    
    @PutMapping(value = "/{customerId}")
	public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId, @RequestBody Account account )  throws Exception {
    	try {
    	accountCustomerService.createAccountToExistingCustomer(customerId, account);
		System.out.println("\n" + environment.getProperty("UserInterface.ACCOUNT_ADDED"));
		String successMessage = environment.getProperty("UserInterface.ACCOUNT_ADDED");
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return response;
    	}
    	catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,	e.getMessage(), e);
    	}
	}
    
    
	@DeleteMapping(value = "/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws Exception  {
		try {
		accountCustomerService.deleteCustomer(customerId);
		System.out.println("\n" + environment.getProperty("UserInterface.CUSTOMER_DELETED"));
		String successMessage = environment.getProperty("UserInterface.CUSTOMER_DELETED");
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return response;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,	e.getMessage(), e);
		}
	}
}
