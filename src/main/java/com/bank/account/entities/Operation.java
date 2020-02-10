package com.bank.account.entities;

public enum Operation {

    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw");

    private String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
