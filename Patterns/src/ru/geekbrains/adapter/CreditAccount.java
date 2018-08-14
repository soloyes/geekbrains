package ru.geekbrains.adapter;

import java.util.Random;

public class CreditAccount implements Creditable{
    private double credit;

    private Random random = new Random();
    public CreditAccount() {
        this.credit = random.nextDouble();
    }

    @Override
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
