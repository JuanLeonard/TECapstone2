package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer sendMoney(Account toUserAccount, Account fromUserAccount, BigDecimal amount) {
        Transfer transfer = new Transfer();
        String status = "approved";
        String transferType = "send";
        String sql = "INSERT INTO transfer (to_user, from_user, transfer_type, transfer_amount, transfer_status) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        String sqlQuery = "SELECT transfer_id,to_user, from_user, transfer_type, transfer_amount, transfer_status FROM transfer WHERE transfer_id=?";
        try {
            Long transferId = jdbcTemplate.queryForObject(sql, long.class, toUserAccount.getUserId(), fromUserAccount.getUserId(), transferType, amount, status);


            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, transferId);
            if (rowSet.next()) {
                transfer = mapRowToTransfer(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

            return transfer;


           
           


        }



    @Override
    public BigDecimal requestMoney(BigDecimal amount){
        return null;
    }

    @Override//create objects for inputs
    public List<Transfer> listOfTransfersById(Long fromUserId, Long toUserId){

            List<Transfer> transfers = new ArrayList<>();
            String sql = "SELECT transfer_id, to_user, from_user, transfer_type, transfer_amount, transfer_status FROM transfer WHERE to_user = ? OR from_user = ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, toUserId, fromUserId);
            while(results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
           return transfers;

    }


    @Override
    public Transfer getAllTransferDetails(Long transferId){
        Transfer transfers= new Transfer();
        String sql = "SELECT transfer_id, to_user, from_user, transfer_type, transfer_amount, transfer_status FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if(results.next()) {
           transfers= mapRowToTransfer(results);

        }
        return transfers;

            }

    @Override
    public List<Transfer> listByStatus(Transfer status){
            List<Transfer> transfers = new ArrayList<>();
            String sql = "SELECT transfer_id, to_user, from_user, transfer_type, transfer_amount, transfer_status FROM transfer WHERE status = ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
            return transfers;

    }



    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setToUser(rs.getLong("to_user"));
        transfer.setFromUser(rs.getLong("from_user"));
        transfer.setAmount(rs.getBigDecimal("transfer_amount"));
        transfer.setStatus(rs.getString("transfer_status"));
        transfer.setType(rs.getString("transfer_type"));
        return transfer;
    }

}
