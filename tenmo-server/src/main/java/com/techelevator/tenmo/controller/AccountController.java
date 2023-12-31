package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.BalanceDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;


    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/balance")
    public BalanceDTO getAccountBalance(Principal principal){

        User user = userDao.findByUsername(principal.getName());
        Account account = accountDao.getAccountByUserId(user.getId());
        BalanceDTO balance = new BalanceDTO();
        balance.setBalance(account.getBalance());
        return balance;
    }

    @GetMapping("/{id}")
    public Account getAccountByUserId(Principal principal, Long userId){
        User user = userDao.findByUsername(principal.getName());
        //Account account = accountDao.getAccountByUserId(user.getId());
        Account account = accountDao.getAccountByUserId(userId);
        return account;
    }
//money movement method
}
