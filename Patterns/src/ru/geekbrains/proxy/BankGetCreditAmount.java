package ru.geekbrains.proxy;

import java.math.BigDecimal;
import java.util.Date;

public interface BankGetCreditAmount {
    BigDecimal getCredit();

    Date tillDate();
}
