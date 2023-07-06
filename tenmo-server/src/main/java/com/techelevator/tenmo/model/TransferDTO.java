package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

    private Long id;
    private String type;

    private BigDecimal amount;

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

    public Long getId() {
        return id;
    }

}
