package com.margo.kataproject.service;

import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.entity.OperationHistory;
import com.margo.kataproject.service.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Bank account service tests.
 */
@SpringBootTest
public class BankAccountServiceTests {

    /**
     * The Bank account service.
     */
    @MockBean
    BankAccountService bankAccountService;

    /**
     * Deposit success test.
     */
    @Test
    public void depositSuccessTest() {

        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        assertEquals(bankAccount.getBalance(), 500.0);
        assertNotNull(bankAccount.getAccountId(), "account id must not be null");
        assertNull(bankAccount.getOperationHistory());
    }

    /**
     * Deposit fail test.
     */
    @Test
    public void depositFailTest() {

        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        Mockito.doThrow(new IllegalArgumentException()).when(bankAccountService).deposit(bankAccount.getAccountId(), -100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(bankAccount.getAccountId(), -100));
    }

    /**
     * Withdraw success test.
     */
    @Test
    public void withdrawSuccessTest() {

        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, Arrays.asList());
        assertEquals(bankAccount.getBalance(), 500.0);
        assertEquals(bankAccount.getOperationHistory().size(), 0);
    }

    /**
     * Withdraw fail test.
     */
    @Test
    public void withdrawFailTest() {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        assertEquals(bankAccount.getBalance(), 500.0);
        Mockito.doThrow(new IllegalArgumentException()).when(bankAccountService).withdraw(bankAccount.getAccountId(), 600);
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdraw(bankAccount.getAccountId(), 600));

    }

    /**
     * Check operations history success test.
     */
    @Test
    public void checkOperationsHistorySuccessTest() {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, Arrays.asList());
        assertEquals(bankAccount.getOperationHistory().size(), 0);
        bankAccount.setOperationHistory(Arrays.asList(new OperationHistory(5L, "Deposit", new Date(), 1000.0, 50)));
        assertEquals(bankAccount.getOperationHistory().size(), 1);
    }


}
