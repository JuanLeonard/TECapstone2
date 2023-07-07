package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    void createAccount(Long id);

    BigDecimal getAccountBalance(Long id);

    Account getAccountByUserId(Long userId);

    BigDecimal updateBalance();
    // money movement method called after status changed to approved and acceptable transfer =true in request or after send money and acceptable transfer = true
}
