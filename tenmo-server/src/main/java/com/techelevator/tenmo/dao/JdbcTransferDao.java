package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
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
        return null;
    }

    @Override
    public BigDecimal requestMoney(BigDecimal amount){
        return null;
    }

    @Override
    public List<Transfer> list(){
        return null;
    }

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
