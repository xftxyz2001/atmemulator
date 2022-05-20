package com.xftxyz.atm.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.xftxyz.atm.domain.Account;
import com.xftxyz.atm.domain.Transaction;
import com.xftxyz.atm.domain.TransactionType;
import com.xftxyz.atm.service.AccountService;

/**
 * ATMView
 * 模拟进行了一些修改
 */
public class ATMView {

    private AccountService as = new AccountService();
    private Scanner scanner = new Scanner(System.in);
    private Account account = null;

    public static void main(String[] args) {
        ATMView atmView = new ATMView();
        atmView.enterMainMenu();
        atmView.as.saveAccounts();
        System.out.println("Bye...");
        atmView.scanner.close();
    }

    private void enterMainMenu() {
        list();
        int selected = scanner.nextInt();
        while (selected != 0) {
            switch (selected) {
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                getBalance();
                break;
            case 4:
                listTransaction();
                break;
            case 5:
                login();
                break;
            case 6:
                transfer();
                break;
            case 7:
                register();
                break;
            default:
                System.out.println("Invalid selection");
                break;
            }
            list();
            selected = scanner.nextInt();
        }
    }

    private void register() {
        System.out.println("Enter your id: ");
        String id = scanner.next();
        if (as.findAccount(id) != null) {
            System.out.println("Account already exists");
            return;
        }
        while (true) {
            System.out.println("Enter your password: ");
            String password = scanner.next();
            System.out.println("Enter your password again: ");
            String password2 = scanner.next();
            if (!password.equals(password2)) {
                System.out.println("Password not match");
                continue;
            }
            as.addAccount(new Account(new BigDecimal("100"), LocalDateTime.now(), id, password));
            break;
        }
    }

    private void transfer() {
        System.out.println("Enter account id to transfer to: ");
        String toAccountId = scanner.next();
        Account to = as.findAccount(toAccountId);
        if (to == null) {
            System.out.println("Account not found");
            return;
        }
        System.out.println("Enter amount to transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        if (account.withdraw(amount)) {
            to.deposit(amount);
            System.out.println("Transfer success");
        } else {
            System.out.println("Insufficient balance");
        }
        account.addTransaction(new Transaction(amount, TransactionType.TRANSFER, LocalDateTime.now(), to));
    }

    private void login() {
        System.out.println("Enter your id: ");
        String id = scanner.next();
        Account findAccount = as.findAccount(id);
        if (findAccount == null) {
            System.out.println("账户不存在");
            return;
        }
        System.out.println("Enter your password: ");
        String password = scanner.next();
        if (findAccount.checkPassword(password)) {
            System.out.println("登录成功");
            this.account = findAccount;
        }
    }

    private void listTransaction() {
        if (account == null) {
            System.out.println("请先登录");
            return;
        }
        System.out.println("交易记录");
        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }

    }

    private void getBalance() {
        if (account == null) {
            System.out.println("请先登录");
            return;
        }
        System.out.println("余额为：" + account.getBalance());
    }

    private void withdraw() {
        if (account == null) {
            System.out.println("请先登录");
            return;
        }
        System.out.println("Enter amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        if (account.withdraw(amount)) {
            System.out.println("取款成功");
            getBalance();
        } else {
            System.out.println("取款失败");
            getBalance();
        }
    }

    private void deposit() {
        if (account == null) {
            System.out.println("请先登录");
            return;
        }
        System.out.println("Enter amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        if (account.deposit(amount)) {
            System.out.println("存款成功");
            getBalance();
        } else {
            System.out.println("存款失败");
            getBalance();
        }
    }

    private void list() {
        System.out.println("================================欢迎使用================================");
        System.out.println("1. 存款");
        System.out.println("2. 取款");
        System.out.println("3. 查询余额");
        System.out.println("4. 查询交易记录");
        System.out.println("5. 登录");
        System.out.println("6. 转账");
        System.out.println("7. 注册");
        System.out.println("0. 退出");
    }

}