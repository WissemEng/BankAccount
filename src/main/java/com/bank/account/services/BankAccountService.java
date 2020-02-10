package com.bank.account.services;

import com.bank.account.entities.BankAccount;
import com.bank.account.entities.BankAccountOperation;
import com.bank.account.entities.Operation;
import com.bank.account.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccount bankAccount;

    public void bankAccountDeposit(double amount){
        bankAccount.setAmount(bankAccount.getBalance() + amount);
        addOperationToHistory(Operation.DEPOSIT.getOperation(), amount);
    }

    public boolean bankAccountWithdraw(double amount){
        if(bankAccount.getBalance() < amount){
            return false;
        }
        bankAccount.setAmount(bankAccount.getBalance() - amount);
        addOperationToHistory(Operation.WITHDRAW.getOperation(), amount);
        return true;
    }

    public List<BankAccountOperation> getBankAccountStatements(){
        return bankAccount.getHistory();
    }

    public double getBankAccountBalance(){
        return bankAccount.getBalance();
    }

    public LocalDate getBankAccountCreationDate(){
        return bankAccount.getCreationDate();
    }

    private void addOperationToHistory(String operation, double amount){
        BankAccountOperation bankAccountOperation =
                new BankAccountOperation(Utils.getCurrentDate(), operation, amount, bankAccount.getBalance());
        bankAccount.addToHistory(bankAccountOperation);
    }
}
