package ru.geekbrains.proxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProxyBankAccess implements BankGetCreditAmount {
    @Override
    public BigDecimal getCredit() {
        return BigDecimal.valueOf(Long.MAX_VALUE);
    }

    @Override
    public Date tillDate() {
        return new GregorianCalendar(2019, 8, 12).getTime();
    }
}
