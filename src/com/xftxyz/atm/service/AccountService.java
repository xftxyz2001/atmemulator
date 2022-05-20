package com.xftxyz.atm.service;

import java.util.ArrayList;
import com.xftxyz.atm.domain.Account;
import com.xftxyz.atm.util.XFReader;

public class AccountService {
    ArrayList<Account> accounts = new ArrayList<Account>();

    public AccountService() {
        this.accounts = XFReader.read();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Account findAccount(String id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public void saveAccounts() {
        XFReader.write(accounts);
    }

}
