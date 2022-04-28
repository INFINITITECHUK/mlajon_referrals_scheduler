package com.redltd.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private long transactionId;
    private String keyword;
    private String MsgBody;
    private int status;
    private long walletMsisdn;
    private Timestamp createdDate;
    private String requestOrigin;
    private String currency;
    private BigDecimal ExRate;

}
