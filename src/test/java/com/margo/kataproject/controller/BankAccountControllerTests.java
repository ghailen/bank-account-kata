package com.margo.kataproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margo.kataproject.dto.TransactionOperation;
import com.margo.kataproject.entity.BankAccount;
import com.margo.kataproject.entity.OperationHistory;
import com.margo.kataproject.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Bank account controller tests.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTests {

    /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Bank account service.
     */
    @MockBean
    BankAccountService bankAccountService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Deposit success.
     *
     * @throws Exception the exception
     */
    @Test
    public void depositSuccess() throws Exception {
        Mockito.doNothing().when(bankAccountService).deposit(1L, 200);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/accounting/deposit").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TransactionOperation(1L, 300)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

    }

    /**
     * Deposit fail.
     *
     * @throws Exception the exception
     */
    @Test
    public void depositFail() throws Exception {
        Mockito.doThrow(new IllegalArgumentException()).when(bankAccountService).deposit(1L, -200);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/accounting/deposit").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TransactionOperation(1L, -200)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }


    /**
     * Withdraw success.
     *
     * @throws Exception the exception
     */
    @Test
    public void withdrawSuccess() throws Exception {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        Mockito.doNothing().when(bankAccountService).withdraw(bankAccount.getAccountId(), 300);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounting/withdraw").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TransactionOperation(1L, 300)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

    }

    /**
     * Withdraw fail.
     *
     * @throws Exception the exception
     */
    @Test
    public void withdrawFail() throws Exception {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        Mockito.doThrow(new IllegalArgumentException()).when(bankAccountService).withdraw(bankAccount.getAccountId(), 600);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounting/withdraw").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TransactionOperation(1L, 600)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }

    /**
     * Check operations history success.
     *
     * @throws Exception the exception
     */
    @Test
    public void checkOperationsHistorySuccess() throws Exception {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 500.0, null);
        OperationHistory operationHistory = new OperationHistory(5L, "Deposit", new Date(), 1000.0, 50);
        List<OperationHistory> operationsHistory = Arrays.asList(operationHistory);
        bankAccount.setOperationHistory(operationsHistory);
        Mockito.when(bankAccountService.CheckOperationsHistory(1L)).thenReturn(bankAccount.getOperationHistory());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounting/operations-history/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("1000.0")));
        ;

    }

    /**
     * Check operations history empty.
     *
     * @throws Exception the exception
     */
    @Test
    public void checkOperationsHistoryEmpty() throws Exception {
        BankAccount bankAccount = new BankAccount(1L, "09828077", "Ghailene", 200.0, null);
        List<OperationHistory> operationsHistory = new ArrayList<>();
        bankAccount.setOperationHistory(operationsHistory);
        Mockito.when(bankAccountService.CheckOperationsHistory(1L)).thenReturn(bankAccount.getOperationHistory());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounting/operations-history/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("")))
                .andExpect(MockMvcResultMatchers.status().isAccepted());


    }


}
