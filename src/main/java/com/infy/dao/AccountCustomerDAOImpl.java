package com.infy.dao;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.entity.AccountEntity;
import com.infy.entity.CustomerEntity;
import com.infy.model.Account;
import com.infy.model.Customer;

@Repository(value = "cardCustomerDao")
public class AccountCustomerDAOImpl implements AccountCustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// adds a new card and  new customer
	@Override
	public Integer addCustomer(Customer customer) {
		Integer customerId = null;
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
		entityManager.persist(customerEntity);
		customerId = customerEntity.getCustomerId();
		return customerId;
	}

	// adds a new card to an existing customer
	@Override
	public void createAccountToExistingCustomer(Integer id, Account account) {
		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setAcId(account.getAcId());
		accountEntity.setAcNumber(account.getAcNumber());
		accountEntity.setCreationDate(account.getCreationDate());
		accountEntity.setBalance(account.getBalance());
		List<AccountEntity> c = customer.getAccountEntities();
		c.add(accountEntity);
		customer.setAccountEntities(c);
	}

	// fetches the details of a particular customer
	@Override
	public Customer getCustomerDetails(Integer id) throws Exception {
		Customer customer = null;
		List<Account> cardDetails = new LinkedList<>();

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);
		System.out.println(customerEntity);
		if (customerEntity != null) {
			customer = new Customer();
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
		}
		return customer;
	}

	// deletes particular customer
	@Override
	public void deleteCustomer(Integer id) {
		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);
		entityManager.remove(customer);
	}

	// deletes card of exiting customer
	@Override
	public void deleteAccountOfExistingCustomer(Integer customerId, List<Integer> acIdsToDelete) {
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		for (Integer acId : acIdsToDelete) {
			AccountEntity accountEntity = entityManager.find(AccountEntity.class, acId);
			customerEntity.getAccountEntities().remove(accountEntity);
			entityManager.remove(accountEntity);
		}
	}
}
