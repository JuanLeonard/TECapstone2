package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private TransferDao transferDao;
    private UserDao userDao;
    private AccountDao accountDao;

    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }



@GetMapping("/recipientList")//to show list of users to either request from or send to.
    public List<User> findAll(){
        List<User> recipientList= userDao.findAll();
                return recipientList;
}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sendTransfer")//once transfer is initiated before money movement
    //if toUser=principal then return exception and amount > fromUser.getBalance() return exception
    public Transfer verifiedTransfer(@RequestBody TransferDTO transferDTO, Principal principal){
        Transfer transfer=new Transfer();

        User user = userDao.findByUsername(principal.getName());
        BigDecimal amount=transferDTO.getAmount();
        BigDecimal balance= accountDao.getAccountByUserId(user.getId()).getBalance();
        if((transferDTO.getToUser()!= user.getId()) && (amount.compareTo(balance)==-1)){

        Account toUserAccount=accountDao.getAccountByUserId(transferDTO.getToUser());
        Account fromUserAccount=accountDao.getAccountByUserId(transferDTO.getFromUser());
        toUserAccount.setBalance(toUserAccount.getBalance().add(amount));
        fromUserAccount.setBalance(fromUserAccount.getBalance().subtract(amount));
        transfer=transferDao.sendMoney(toUserAccount, fromUserAccount, amount);
        }else{
            throw new DaoException("Transfer could not occur."); //add try catch so exception message shows if conditions aren't met
        }

            return transfer;
    }
    @GetMapping("/details/{transferId}")
    public Transfer getAllTransferDetails(@PathVariable Long transferId){
        Transfer transferDetails=transferDao.getAllTransferDetails(transferId);
        return transferDetails;
    }
    @GetMapping("/listMyTransfers")
    public List<Transfer> listMyTransfers(Principal principal, Long fromUserId, Long toUserId){
        User fromUser=userDao.findByUsername(principal.getName());
        User toUser=userDao.findByUsername(principal.getName());

        List<Transfer> myTransfersList=transferDao.listOfTransfersById(fromUser.getId(), toUser.getId());
        return myTransfersList;
    }



}


