package ru.geekbrains.adapter;

public class BankAccount {
    private double money;

    public BankAccount(Creditable creditableAccount) {
        this.money = creditableAccount.getCredit();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "money=" + money +
                '}';
    }
}
