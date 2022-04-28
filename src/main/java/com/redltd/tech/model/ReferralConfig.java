package com.redltd.tech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferralConfig {

    private long id;
    private long uniqueReferCount;
    private long TotalAllowReferel;
    private int PointSource;
    private double ExchangeRate;
    private int FirstTransactionPeriod;
    private String ServiceAllowed;
    private long PointDestination;
    private int MinimumNoOfRefer;
    private int MinimumNoOfUniqueTransaction;

}
