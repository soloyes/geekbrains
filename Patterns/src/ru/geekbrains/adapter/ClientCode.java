package ru.geekbrains.adapter;

public class ClientCode {
    private static BankAccount bankAccount;

    public static void main(String[] args) {
        CreditAccount creditAccount = new CreditAccount();
        DebitAccount debitAccount = new DebitAccount();
        DebitCreditAdapter debitCreditAdapter = new DebitCreditAdapter(debitAccount);


        bankAccount = new BankAccount(creditAccount);
        System.out.println(bankAccount);
        bankAccount = new BankAccount(debitCreditAdapter);
        System.out.println(bankAccount);
    }
}
