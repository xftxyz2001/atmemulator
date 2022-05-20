package com.xftxyz.atm.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private BigDecimal dealAmount;
    private TransactionType transactionType;
    private LocalDateTime dealTime;
    private Account to;

    public Transaction() {
    }

    public Transaction(BigDecimal dealAmount, TransactionType transactionType, LocalDateTime dealTime, Account to) {
        this.dealAmount = dealAmount;
        this.transactionType = transactionType;
        this.dealTime = dealTime;
        this.to = to;
    }

    public Transaction(BigDecimal dealAmount, TransactionType transactionType, LocalDateTime dealTime) {
        this.dealAmount = dealAmount;
        this.transactionType = transactionType;
        this.dealTime = dealTime;
        this.to = null;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getDealTime() {
        return dealTime;
    }

    public Account getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "交易类型：" + transactionType + "，交易金额：" + dealAmount + "，交易时间：" + dealTime + "，交易对象：" + to.getId();
    }

}
