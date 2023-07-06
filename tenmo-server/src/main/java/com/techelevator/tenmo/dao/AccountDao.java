package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface AccountDao {

    boolean createAccount(Long userId, BigDecimal balance);

     BigDecimal getAccountBalance(Long id);

     BigDecimal updateBalance();

}
