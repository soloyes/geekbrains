package ru.geekbrains.proxy;

import java.math.BigDecimal;
import java.util.Date;

public class BankAccessor {
    private BigDecimal credit;
    private Date tillDate;

    public BankAccessor(BankGetCreditAmount getCreditAmount) {
        this.credit = getCreditAmount.getCredit();
        this.tillDate = getCreditAmount.tillDate();
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public Date getTillDate() {
        return tillDate;
    }

    @Override
    public String toString() {
        return "BankAccessor{" +
                "credit=" + credit +
                ", tillDate=" + tillDate +
                '}';
    }
}
