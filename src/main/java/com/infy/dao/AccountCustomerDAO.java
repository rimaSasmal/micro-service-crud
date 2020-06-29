package com.infy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.CustomerEntity;
import com.infy.model.Account;
import com.infy.model.Customer;

public interface AccountCustomerDAO extends JpaRepository<CustomerEntity, Integer> {

//	public Customer getCustomerDetails(Integer id) throws Exception;
//	public Integer addCustomer(Customer customer);
//	public void createAccountToExistingCustomer(Integer id, Account account);
//	
//	public void deleteCustomer(Integer id);
//	public void deleteAccountOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete);	
	
	
		
	
		
	
}
