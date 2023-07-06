package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Account account = new Account();
    BigDecimal balance = account.getBalance();

    @Override
    boolean createAccount(Long userId, BigDecimal balance){

        String sql = "INSERT INTO account (user_id, balance) VALUES (?, ?) RETURNING account_id;";
        Integer newAccountId;
        try{
            newAccountId = jdbcTemplate.queryForObject(sql, Integer.class, userId, balance);
        }catch(DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    BigDecimal getAccountBalance(Long id){
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        //Need correct exception
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if(result.next()){
                balance = mapRowToAccount(result).getBalance();
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("Unable to connect to server or database");
        }


        return balance;
    }

    @Override
    BigDecimal updateBalance(){

    }

    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setId(rs.getLong("account_id"));
        account.setId(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }


}
