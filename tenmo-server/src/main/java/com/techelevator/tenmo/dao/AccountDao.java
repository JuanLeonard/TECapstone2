package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    void createAccount(Long id);

    BigDecimal getAccountBalance(Long id);

    Account getAccountByUserId(Long userId);

    BigDecimal updateBalance();

}
