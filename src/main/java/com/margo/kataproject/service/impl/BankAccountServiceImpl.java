package com.margo.kataproject.service.impl;

import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.entity.OperationHistory;
import com.margo.kataproject.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * The type Bank account service.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {


    private static Map<Long, BankAccount> bankAccounts = new HashMap<>();
    /**
     * The Operation histories.
     */


    /**
     * Gets all bank accounts.
     *
     * @return the all bank accounts
     */
    public static List<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(bankAccounts.values());
    }

    /**
     * Gets bank account details.
     *
     * @param accountId the account id
     * @return the bank account details
     */
    public static BankAccount getBankAccountDetails(Long accountId) {
        if (bankAccounts.get(accountId) == null) {
            return new BankAccount();
        }
        return bankAccounts.get(accountId);
    }


    @Override
    public BankAccount createAccount(BankAccount in) {
        if (in.getAccountId() == null) {
            throw new IllegalArgumentException("Enter a valid account id");
        }
        bankAccounts.put(in.getAccountId(), in);
        return in;
    }

    @Override
    public void deposit(Long accountId, int amount) {

        BankAccount bankAccount = getBankAccountDetails(accountId);

        if (amount > 0) {
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            bankAccount.addOperationHistory(new OperationHistory("Deposit", new Date(), bankAccount.getBalance(), amount));
            System.out.println("Bank account balance after deposit :" + bankAccount.getBalance());
        } else throw new IllegalArgumentException("Enter a valid amount");
    }

    @Override
    public void withdraw(Long accountId, int amount) {
        BankAccount bankAccount = getBankAccountDetails(accountId);
        if (amount > 0 && amount <= bankAccount.getBalance()) {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            bankAccount.addOperationHistory(new OperationHistory("Withdraw", new Date(), bankAccount.getBalance(), amount));
            System.out.println("Bank account balance after withdraw :" + bankAccount.getBalance());
        } else throw new IllegalArgumentException("Insufficient balance");
    }

    @Override
    public List<OperationHistory> checkOperationsHistory(Long accountId) {
        return getBankAccountDetails(accountId).getOperationHistory();
    }

}
