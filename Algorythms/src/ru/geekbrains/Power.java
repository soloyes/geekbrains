package ru.geekbrains;

import java.math.BigDecimal;

public class Power {
    public BigDecimal power(double a, double n) {
        if (n == 0) return BigDecimal.valueOf(1);
        if (n % 2 == 1) return power(a, n - 1).multiply(BigDecimal.valueOf(a));
        else {
            BigDecimal b = power(a, n / 2);
            return b.multiply(b);
        }
    }
}
