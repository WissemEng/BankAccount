package com.bank.account.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private LocalDate creationDate;
    private double balance;
    private List<BankAccountOperation> history;

    public BankAccount(){
        creationDate = LocalDate.now();
        history = new ArrayList<>();
    }

    public void setAmount(double balance) {
        this.balance = balance;
    }

    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }

    public void addToHistory(BankAccountOperation bankAccountOperation){
        history.add(bankAccountOperation);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public List<BankAccountOperation> getHistory() {
        return history;
    }

}
