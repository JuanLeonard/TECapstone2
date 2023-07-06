package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account createAccount(Long id);

    BigDecimal getAccountBalance(Long id);

    BigDecimal updateBalance();

}
