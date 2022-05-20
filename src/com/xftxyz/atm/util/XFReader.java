package com.xftxyz.atm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.xftxyz.atm.domain.Account;
import com.xftxyz.atm.domain.Transaction;
import com.xftxyz.atm.domain.TransactionType;

public class XFReader {
    public static void write(ArrayList<Account> accounts) {
        PrintWriter w = null;
        try {
            w = new PrintWriter(new File("accounts.xfd"));
            w.println(accounts.size());
            for (Account account : accounts) {
                w.println(account.getBalance());
                w.println(account.getCreatedDate());
                w.println(account.getId());
                w.println(account.getPassword());
                w.println(account.getTransactions().size());
                ArrayList<Transaction> transactions = account.getTransactions();
                for (Transaction transaction : transactions) {
                    w.println(transaction.getDealAmount());
                    w.println(transaction.getTransactionType());
                    w.println(transaction.getDealTime());
                    w.println(transaction.getTo() == null ? "null" : transaction.getTo().getId());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (w != null) {
                w.close();
            }
        }

    }

    public static ArrayList<Account> read() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            File file = new File("accounts.xfd");
            if (!file.exists()) {
                return accounts;
            }
            Scanner sc = new Scanner(file);
            int size = sc.nextInt();
            for (int i = 0; i < size; i++) {
                BigDecimal balance = sc.nextBigDecimal();
                LocalDateTime createdDate = LocalDateTime.parse(sc.next());
                String id = sc.next();
                String password = sc.next();
                Account account = new Account(balance, createdDate, id, password);
                int transactionSize = sc.nextInt();
                for (int j = 0; j < transactionSize; j++) {
                    BigDecimal dealAmount = sc.nextBigDecimal();
                    TransactionType transactionType = TransactionType.valueOf(sc.next());
                    LocalDateTime dealTime = LocalDateTime.parse(sc.next());
                    String toId = sc.next();
                    Account to = toId.equals("null") ? null
                            : accounts.stream().filter(a -> a.getId().equals(toId)).findFirst().get();
                    Transaction transaction = new Transaction(dealAmount, transactionType, dealTime, to);
                    account.getTransactions().add(transaction);
                }
                accounts.add(account);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
