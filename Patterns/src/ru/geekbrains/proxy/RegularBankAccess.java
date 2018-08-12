package ru.geekbrains.proxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class RegularBankAccess implements BankGetCreditAmount {
    @Override
    public Date tillDate() {
        return new GregorianCalendar(2018, 8, 12).getTime();
    }

    @Override
    public BigDecimal getCredit() {
        return BigDecimal.valueOf(500L);
    }
}
