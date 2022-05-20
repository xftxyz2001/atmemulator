package com.xftxyz.atm.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {
    public static final BigDecimal RARE = new BigDecimal("0.045");
    private BigDecimal balance = new BigDecimal("100");
    private LocalDateTime createdDate;
    private String id;
    private String password;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Account() {
    }

    public Account(String id) {
        this.id = id;
    }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Account(BigDecimal balance, String id, String password) {
        this.balance = balance;
        this.id = id;
        this.password = password;
    }

    public Account(BigDecimal balance, LocalDateTime createdDate, String id, String password) {
        this.balance = balance;
        this.createdDate = createdDate;
        this.id = id;
        this.password = password;
    }

    public static BigDecimal getRare() {
        return RARE;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        balance = balance.add(amount);
        transactions.add(new Transaction(amount, TransactionType.DEPOSIT, LocalDateTime.now(), this));
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (balance.compareTo(amount) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(amount, TransactionType.WITHDRAW, LocalDateTime.now(), this));
        return true;
    }

    // public void setBalance(BigDecimal balance) {
    // this.balance = balance;
    // }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    // public void setCreatedDate(LocalDateTime createdDate) {
    // this.createdDate = createdDate;
    // }

    public String getId() {
        return id;
    }

    // public void setId(String id) {
    // this.id = id;
    // }

    // public String getPassword() {
    // return password;
    // }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (checkPassword(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getPassword() {
        return password;
    }

}
