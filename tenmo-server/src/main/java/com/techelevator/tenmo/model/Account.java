package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private BigDecimal balance;
    private Long accountId;
    private Long userId;

   

    public Account () {}

    public Account(BigDecimal balance, Long accountId, Long userId){
        this.balance = balance;
        this.accountId = accountId;
        this.userId=userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return balance.equals(account.balance) && accountId.equals(account.accountId) && userId.equals(account.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, accountId, userId);
    }
}
