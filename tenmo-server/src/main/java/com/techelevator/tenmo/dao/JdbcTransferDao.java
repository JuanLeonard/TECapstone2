package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransferDao {

    private JdbcTemplate jdbcTemplate;

    private Transfer mapRowToUser(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setId(rs.getLong("transfer_id"));
        transfer.setAmount(rs.getBigDecimal("transfer_amount"));
        transfer.setStatus(rs.getString("transfer_status"));
        transfer.setType(rs.getString("transfer_type"));
        return transfer;
    }

}
