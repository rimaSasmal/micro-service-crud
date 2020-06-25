package com.infy.service;

import java.util.List;

import com.infy.model.Account;
import com.infy.model.Customer;


public interface AccountCustomerService {
	public Customer getCustomerDetails(Integer id) throws Exception;
	public Integer addCustomer(Customer customer) throws Exception;
	public void createAccountToExistingCustomer(Integer id, Account account) throws Exception;
	
	public void deleteCustomer(Integer id) throws Exception;
	public void deleteAccountOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws Exception;	
		
}
