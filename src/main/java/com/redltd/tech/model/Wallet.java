package com.redltd.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    private long walletMSISDN;
    private int walletCode;
    private BigDecimal amount;
    private Date createdDate;
    private String createdBy;
    private int status;
    private long lastTransactionId;
    private BigDecimal lastTransactionAmount;
    private BigDecimal balanceBefore;
    private Date modifiedDate;
    private String modifiedBy;
    private long currentYearRewardPoint;
    private long lastYearRewardPoint;
    private long parent;

}
