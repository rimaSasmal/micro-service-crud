package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infy.dao.AccountCustomerDAO;
import com.infy.model.Account;
import com.infy.model.Customer;

@Service(value = "cardCustomerService")
@Transactional
public class AccountCustomerServiceImpl implements AccountCustomerService {

	@Autowired
	private AccountCustomerDAO accountCustomerDAO;

	@Override
	public Integer addCustomer(Customer customer) throws Exception {
		Integer id = null;
		id = accountCustomerDAO.addCustomer(customer);
		return id;
	}

	@Override
	public void createAccountToExistingCustomer(Integer id, Account account) throws Exception {

		if (accountCustomerDAO.getCustomerDetails(id) != null) {
			accountCustomerDAO.createAccountToExistingCustomer(id, account);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public void deleteCustomer(Integer id) throws Exception {
		if (accountCustomerDAO.getCustomerDetails(id) != null) {
			accountCustomerDAO.deleteCustomer(id);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public void deleteAccountOfExistingCustomer(Integer customerId, List<Integer> acIdsToDelete) throws Exception {
		if (accountCustomerDAO.getCustomerDetails(customerId) != null) {
			accountCustomerDAO.deleteAccountOfExistingCustomer(customerId, acIdsToDelete);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	// Fetch customer details
	@Override

	public Customer getCustomerDetails(Integer id) throws Exception {

		Customer customer = null;
		customer = accountCustomerDAO.getCustomerDetails(id);
		if (customer == null) {
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
		}
		return customer;
	}
}
