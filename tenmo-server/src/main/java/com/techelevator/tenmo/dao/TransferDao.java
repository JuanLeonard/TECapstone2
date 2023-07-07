package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    Transfer sendMoney(Account toUserAccount, Account fromUserAccount, BigDecimal amount);

    BigDecimal requestMoney(BigDecimal amount);

    List<Transfer> listOfTransfersById();

    Transfer getAllTransferDetails(Long transferId);

    List<Transfer> listByStatus(Transfer status);

    //change status method.
   

}
