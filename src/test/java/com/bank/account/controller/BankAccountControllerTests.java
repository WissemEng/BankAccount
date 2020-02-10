package com.bank.account.controller;

import com.bank.account.entities.BankAccount;
import com.bank.account.entities.BankAccountOperation;
import com.bank.account.entities.Operation;
import com.bank.account.services.BankAccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class BankAccountControllerTests {


    private MockMvc mockMvc;

    @InjectMocks
    private BankAccountController bankAccountController;

    @Mock
    private BankAccount bankAccount;

    @Mock
    private BankAccountService bankAccountService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController)
                .build();
    }

    @Test
    public void shoud_return_deposit_with_success_when_sending_a_correct_value() throws Exception {
        mockMvc.perform(get("/deposit")
                .param("amount", "20.3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deposit with success!"));
    }

    @Test
    public void shoud_return_deposit_with_failure_when_sending_a_non_numeric_value() throws Exception {
        mockMvc.perform(get("/deposit")
                .param("amount", "a string value"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed to deposit!"));
    }

    @Test
    public void shoud_return_withdraw_with_failure_when_sending_a_non_numeric_value() throws Exception {
        mockMvc.perform(get("/withdraw")
                .param("amount", "a string value"))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed to withdraw!"));
    }

    @Test
    public void shoud_return_withdraw_with_failure_when_sending_a_non_numeric_value_x() throws Exception {
        when(bankAccountService.bankAccountWithdraw(10)).thenReturn(true);
        mockMvc.perform(get("/withdraw")
                .param("amount", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Withdraw with success!"));
    }

    @Test
    public void shoud_return_withdraw_with_failure_when_account_has_insufficient_money() throws Exception {
        when(bankAccountService.bankAccountWithdraw(10)).thenReturn(false);
        mockMvc.perform(get("/withdraw")
                .param("amount", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account amount is insufficient!"));
    }

    @Test
    public void should_return_account_balance_when_calling_bank_account_balance_endpoint() throws Exception {
        when(bankAccountService.getBankAccountBalance()).thenReturn(100.0);
        mockMvc.perform(get("/bankAccountBalance"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void should_return_creation_date_when_calling_creationDate_endpoint() throws Exception {
        LocalDate localDate = LocalDate.now();
        when(bankAccountService.getBankAccountCreationDate()).thenReturn(LocalDate.now());
        mockMvc.perform(get("/creationDate"))
                .andExpect(status().isOk())
                .andExpect(content().string(localDate.toString()));
    }

    @Test
    public void should_return_bank_account_statements_when_calling_statements_endpoint() throws Exception {
        String expectedString = "[Date : 2020-02-10 19:33:48 | Operation : Deposit | Amount : 120.0 | Balance : 200.0]";
        BankAccountOperation bankAccountOperation = new BankAccountOperation("2020-02-10 19:33:48"
                , Operation.DEPOSIT.getOperation()
                , 120
                , 200);
        when(bankAccountService.getBankAccountStatements()).thenReturn(Arrays.asList(bankAccountOperation));
        mockMvc.perform(get("/statements"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));
    }

}
