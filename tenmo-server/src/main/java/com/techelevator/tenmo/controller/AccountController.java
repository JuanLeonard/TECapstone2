package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.BalanceDTO;
import com.techelevator.tenmo.model.User;
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

    @GetMapping("/balance")
    public BalanceDTO getAccountBalance(Principal principal){

        User user= userDao.findByUsername(principal.getName());
        //Account account = accountDao.getAccountByUserId(user.getId());
        BalanceDTO balance = new BalanceDTO();
       //balance.setBalance(new BigDecimal(account.getBalance));
        return balance;
    }

}
