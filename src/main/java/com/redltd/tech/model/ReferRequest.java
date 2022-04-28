package com.redltd.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferRequest {

    private long id;
    private long msisdn;
    private String message;
    private long friendMsisdn;
    private String createdBy;
    private Timestamp createdDate;
    private int otp;
    private int otpUsed;
    private long pointGivenSource;
    private int isFirstTransactionDone;
    private long pointGivenDestination;
    private long noOfTransactionFriend;

}
