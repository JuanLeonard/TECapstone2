package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    BigDecimal sendMoney(BigDecimal amount);

    BigDecimal requestMoney(BigDecimal amount);

    List<Transfer> list();

    List<Transfer> getAllTransferDetails();

    List<Transfer> listByStatus();

}
