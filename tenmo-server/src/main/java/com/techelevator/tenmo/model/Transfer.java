package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Transfer {

    private Long transferId;
    private String type;
    @Positive(message = "Cannot be zero or negative.")
    @NotEmpty
    private BigDecimal amount;
    private long toUser;
    private long fromUser;
    private String status;

    public Transfer() {}

    public Transfer(Long transferId, String type, BigDecimal amount,long toUser, long fromUser, String status){
        this.transferId = transferId;
        this.type = type;
        this.amount = amount;
        this.toUser=toUser;
        this.fromUser=fromUser;
        this.status = status;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getToUser() {
        return toUser;
    }

    public void setToUser(long toUser) {
        this.toUser = toUser;
    }

    public long getFromUser() {
        return fromUser;
    }

    public void setFromUser(long fromUser) {
        this.fromUser = fromUser;
    }
}
