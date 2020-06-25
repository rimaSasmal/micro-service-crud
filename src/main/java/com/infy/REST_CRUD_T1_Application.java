package com.infy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.model.Account;
import com.infy.model.Customer;
import com.infy.service.AccountCustomerService;

@SpringBootApplication
public class REST_CRUD_T1_Application {

	@Autowired
	AccountCustomerService accountCustomerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(REST_CRUD_T1_Application.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {

		 //addCustomer(); //post

		//addNewAccountToExistingCustomer(); //put
		//getCustomerWithCardDetails(); //get
		//deleteAccountOfExistingCustomer(); //delete
		// deleteCustomer(); //delete
	}
	
	public void addCustomer() {
		try {

			Customer customer = new Customer();
			customer.setName("Tom Rosley");
			customer.setEmailId("Tom@infy.com");
			customer.setDateOfBirth(LocalDate.of(1992, 1, 10));

			Account acDetails1 = new Account();
			acDetails1.setAcId(12352);
			acDetails1.setAcNumber("6642160005012199");
			acDetails1.setCreationDate(LocalDate.of(2015, 02, 27));
			acDetails1.setBalance(30000);

			Account acDetails2 = new Account();
			acDetails2.setAcId(12353);
			acDetails2.setAcNumber("6642160005012200");
			acDetails2.setCreationDate(LocalDate.of(2018, 10, 15));
			acDetails2.setBalance(20000);

			List<Account> accounts = new LinkedList<>();
			accounts.add(acDetails1);
			accounts.add(acDetails2);

			customer.setAccounts(accounts);

			accountCustomerService.addCustomer(customer);
			System.out.println("\n" + environment.getProperty("UserInterface.ACCOUNT_AND_CUSTOMER_ADDED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println(message);
		}

	}


	public void getCustomerWithCardDetails() {

		try {
			Integer totalBal=0;
			Integer customerId = 1001;

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

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println(message);
		}

	}

	public void addNewAccountToExistingCustomer() throws Exception {

		Integer customerId = 1006;

		Account acDetails = new Account();
		acDetails.setAcId(12354);
		acDetails.setAcNumber("6642160055012200");
		acDetails.setCreationDate(LocalDate.of(2019, 03, 07));
		try {

			accountCustomerService.createAccountToExistingCustomer(customerId, acDetails);
			System.out.println("\n" + environment.getProperty("UserInterface.ACCOUNT_ADDED"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println(message);
		}

	}

	
	public void deleteAccountOfExistingCustomer() {
		try {

			Integer customerId = 1001;

			List<Integer> acIdsToDelete = new ArrayList<>();
			acIdsToDelete.add(12346);
			acIdsToDelete.add(12347);

			accountCustomerService.deleteAccountOfExistingCustomer(customerId, acIdsToDelete);
			System.out.println("\n" + environment.getProperty("UserInterface.ACCOUNT_DEACTIVATED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println(message);
		}

	}

	public void deleteCustomer() {
		try {

			Integer customerId = 1001;

			accountCustomerService.deleteCustomer(customerId);
			System.out.println("\n" + environment.getProperty("UserInterface.CUSTOMER_DELETED"));

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");

			System.out.println(message);
		}

	}
*/
}
