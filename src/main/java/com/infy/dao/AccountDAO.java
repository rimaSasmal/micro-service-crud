package com.infy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.AccountEntity;

public interface AccountDAO extends JpaRepository<AccountEntity, Integer> {

}
