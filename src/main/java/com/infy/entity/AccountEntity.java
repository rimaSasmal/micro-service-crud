package com.infy.entity;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="Account") 
public class AccountEntity {
	
	@Id
	
	private Integer acId;
	
	private String acNumber;
	
	private LocalDate creationDate;
	
	private Integer balance;
	
	
	public Integer getAcId() {
		return acId;
	}


	public void setAcId(Integer acId) {
		this.acId = acId;
	}


	public String getAcNumber() {
		return acNumber;
	}


	public void setAcNumber(String acNumber) {
		this.acNumber = acNumber;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public Integer getBalance() {
		return balance;
	}


	public void setBalance(Integer balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "AccountEntity [AccountId=" + acId + "]";
	}
	
	
	
}
