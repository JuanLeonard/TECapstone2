package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
//@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;
    private AccountDao accountDao;

    //private JdbcTemplate jdbcTemplate;

    public UserController(AccountDao accountDao, UserDao userDao){
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping("")
    public UserDTO get(String username){
        User user = userDao.findByUsername(username);
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

}
