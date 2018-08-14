package ru.geekbrains.adapter;

public class DebitCreditAdapter implements Creditable {
    private CreditAccount account;

    public DebitCreditAdapter(DebitAccount account) {
        this.account = new CreditAccount();
        this.account.setCredit(account.getCredit());
    }

    @Override
    public double getCredit(){
        return account.getCredit();
    }
}
