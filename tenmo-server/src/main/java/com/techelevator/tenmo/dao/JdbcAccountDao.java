package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
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

    //Account account = new Account();
    //BigDecimal balance = account.getBalance();

    @Override
    public Account createAccount(Long id){
        String sql = "INSERT INTO account (user_id, balance) VALUES (?, ?) RETURNING account_id;";
        Account newAccount = new Account();
        try{
            newAccount = jdbcTemplate.queryForObject(sql, Account.class, id, newAccount.getBalance());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return newAccount;
    }

    @Override
    public BigDecimal getAccountBalance(Long id){
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        //DAO is the correct exception, we just need to import it.
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if(result.next()){
                balance = mapRowToAccount(result).getBalance();
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database");
        }


        return balance;
    }

    @Override
    public BigDecimal updateBalance(){
        return null;
    }

    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setId(rs.getLong("account_id"));
        account.setId(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }


}
