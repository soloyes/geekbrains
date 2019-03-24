package ru.geekbrains.proxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProxyBankAccess implements BankGetCreditAmount {
    private BankGetCreditAmount bankGetCreditAmount;
    private long limit = 600L;

    public ProxyBankAccess(BankGetCreditAmount bankGetCreditAmount) {
        this.bankGetCreditAmount = bankGetCreditAmount;
    }

    @Override
    public BigDecimal getCredit() {
        if (bankGetCreditAmount.getCredit().compareTo(BigDecimal.valueOf(limit)) > 0) {
            return BigDecimal.valueOf(Long.MAX_VALUE);
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public Date tillDate() {
        return new GregorianCalendar(2019, 8, 12).getTime();
    }
}
