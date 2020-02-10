package com.bank.account.controller;

import com.bank.account.services.BankAccountService;
import com.bank.account.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping(value="/deposit")
    public String deposit(@RequestParam(name = "amount") String depositAmount) {
        if(Utils.isNumeric(depositAmount)){
            bankAccountService.bankAccountDeposit(Double.parseDouble(depositAmount));
            return "Deposit with success!";
        }
        return "Failed to deposit!";
    }

    @GetMapping(value="/withdraw")
    public String withdraw(@RequestParam(name = "amount") String depositAmount) {
        if(Utils.isNumeric(depositAmount)){
            boolean isWithdrawWithSuccess = bankAccountService.bankAccountWithdraw(Long.parseLong(depositAmount));
            if(!isWithdrawWithSuccess){
                return "Account amount is insufficient!";
            }
            return "Withdraw with success!";
        }
        return "Failed to withdraw!";
    }

    @GetMapping(value="/bankAccountBalance")
    public String bankAccountBalance() {
        return String.valueOf(bankAccountService.getBankAccountBalance());
    }

    @GetMapping(value="/creationDate")
    public String bankAccountCreationDate() {
        return String.valueOf(bankAccountService.getBankAccountCreationDate());
    }

    @GetMapping(value="/statements")
    public String getStatement() {

        return bankAccountService.getBankAccountStatements().stream()
                .map(n -> "Date : "
                        + String.valueOf(n.getDate())
                        + " | Operation : "
                        + String.valueOf(n.getOperation())
                        + " | Amount : "
                        + String.valueOf(n.getAmount())
                        + " | Balance : "
                        + String.valueOf(n.getBalance()))
                .collect(Collectors.joining(" ***** ", "[", "]"));
    }

}