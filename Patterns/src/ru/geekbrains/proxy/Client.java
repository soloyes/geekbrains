package ru.geekbrains.proxy;

public class Client {
    public static void main(String... args){
        BankAccessor bankAccessor = new BankAccessor(new RegularBankAccess());
        BankAccessor bankProxyAccessor = new BankAccessor(new ProxyBankAccess(new RegularBankAccess()));

        System.out.println(bankAccessor.toString());
        System.out.println(bankProxyAccessor.toString());
    }
}
