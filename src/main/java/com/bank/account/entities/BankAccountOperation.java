package com.bank.account.entities;

public class BankAccountOperation {

    private String date;
    private String operation;
    private double amount;
    private double balance;

    public BankAccountOperation(String date,
                                String operation,
                                double amount,
                                double balance){
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public String getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

}
