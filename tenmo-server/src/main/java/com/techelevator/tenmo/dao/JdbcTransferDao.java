package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public BigDecimal sendMoney(BigDecimal amount){
        String sql = "INSERT INTO transfer (to_user, from_user, transfer_type, transfer_amount, transfer_status) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        //From user is principal in this case
        //You can't send money to yourself
        return null;
    }

    @Override
    public BigDecimal requestMoney(BigDecimal amount){
        return null;
    }

//    @Override
////    public List<Transfer> list(){
////
//////            List<User> users = new ArrayList<>();
//////            String sql = "SELECT user_id, username, password_hash FROM tenmo_user;";
//////            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//////            while(results.next()) {
//////                User user = mapRowToUser(results);
//////                users.add(user);
//////            }
//////            return users;
////
////    }

    @Override
    public List<Transfer> getAllTransferDetails(){
        return null;
    }

    @Override
    public List<Transfer> listByStatus(){
        return null;
    }



    private Transfer mapRowToUser(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setId(rs.getLong("transfer_id"));
        transfer.setAmount(rs.getBigDecimal("transfer_amount"));
        transfer.setStatus(rs.getString("transfer_status"));
        transfer.setType(rs.getString("transfer_type"));
        return transfer;
    }

}
