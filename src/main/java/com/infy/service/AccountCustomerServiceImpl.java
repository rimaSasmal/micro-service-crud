package com.infy.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infy.dao.AccountCustomerDAO;
import com.infy.dao.AccountDAO;
import com.infy.entity.AccountEntity;
import com.infy.entity.CustomerEntity;
import com.infy.model.Account;
import com.infy.model.Customer;

@Service(value = "cardCustomerService")
@Transactional
public class AccountCustomerServiceImpl implements AccountCustomerService {

	@Autowired
	private AccountCustomerDAO accountCustomerDAO;
	
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Integer addCustomer(Customer customer) throws Exception {
		Integer id = null;
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customer.getName());
		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setDateOfBirth(customer.getDateOfBirth());
		List<Account> acsToAllocate = customer.getAccounts();
		List<AccountEntity> acs = new LinkedList<>();
		for (Account account : acsToAllocate) {
			AccountEntity newAc = new AccountEntity();
			newAc.setAcId(account.getAcId());
			newAc.setAcNumber(account.getAcNumber());
			newAc.setCreationDate(account.getCreationDate());
			newAc.setBalance(account.getBalance());
			acs.add(newAc);
		}
		customerEntity.setAccountEntities(acs);
		accountCustomerDAO.saveAndFlush(customerEntity);
		
		id = customerEntity.getCustomerId();
		return id;
	}

	@Override
	public void createAccountToExistingCustomer(Integer id, Account account) throws Exception {
		
		Optional<CustomerEntity> customer= accountCustomerDAO.findById(id);
		
		if (customer.isPresent()) {
			CustomerEntity customerEntity=customer.get();
			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setAcId(account.getAcId());
			accountEntity.setAcNumber(account.getAcNumber());
			accountEntity.setCreationDate(account.getCreationDate());
			accountEntity.setBalance(account.getBalance());
			List<AccountEntity> c = customerEntity.getAccountEntities();
			c.add(accountEntity);
			customerEntity.setAccountEntities(c);
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public void deleteCustomer(Integer id) throws Exception {
		Optional<CustomerEntity> customer=accountCustomerDAO.findById(id);
		if (customer.isPresent()) {
			accountCustomerDAO.delete(customer.get());
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}

	@Override
	public void deleteAccountOfExistingCustomer(Integer customerId, List<Integer> acIdsToDelete) throws Exception {
		Optional<CustomerEntity> customer=accountCustomerDAO.findById(customerId);
		
		if (customer.isPresent()) {
			
			CustomerEntity customerEntity=customer.get();
			for (Integer acId : acIdsToDelete) {
				Optional<AccountEntity> account = accountDAO.findById(acId);
				if(account.isPresent()) {
					AccountEntity accountEntity=account.get();
				customerEntity.getAccountEntities().remove(accountEntity);
				accountDAO.deleteById(acId);
				}
			}
			
		} else
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
	}


	@Override

	public Customer getCustomerDetails(Integer id) throws Exception {
		Optional<CustomerEntity> cust=accountCustomerDAO.findById(id);
		if (!cust.isPresent()) {
			throw new Exception("Service.CUSTOMER_NOT_FOUND");
		}
		CustomerEntity customerEntity=cust.get();
		List<Account> cardDetails = new LinkedList<>();
		Customer customer = new Customer();
		customer.setEmailId(customerEntity.getEmailId());
		customer.setName(customerEntity.getName());
		customer.setCustomerId(customerEntity.getCustomerId());
		customer.setDateOfBirth(customerEntity.getDateOfBirth());
		List<AccountEntity> accountEntities = customerEntity.getAccountEntities();
		if (!accountEntities.isEmpty()) {
			for (AccountEntity accountEntity : accountEntities) {
				Account account = new Account();
				account.setAcId(accountEntity.getAcId());
				account.setAcNumber(accountEntity.getAcNumber());
				account.setCreationDate(accountEntity.getCreationDate());
				account.setBalance(accountEntity.getBalance());
				cardDetails.add(account);
			}
		}
		customer.setAccounts(cardDetails);
		return customer;
	}
}
