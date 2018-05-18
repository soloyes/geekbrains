package ru.geekbrains;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainTestTask2 {

    private int m;
    private int r;
    private BigInteger expect;

    public MainTestTask2(int m, int r, BigInteger expect) {
        this.m = m;
        this.r = r;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {-1, 1, BigInteger.valueOf(-1)},
                {1, -1, BigInteger.valueOf(-1)},
                {0, 0, BigInteger.valueOf(1)},
                {1, 0, BigInteger.valueOf(1)},
                {0, 1, BigInteger.valueOf(-1)},
                {1, 1, BigInteger.valueOf(1)},
                {70, 10, BigInteger.valueOf(396704524216L)},
                {79, 19, BigInteger.valueOf(883829035553043580L)},
                {33, 32, BigInteger.valueOf(33L)},
                {27, 53, BigInteger.valueOf(-1)},
                {100, 95, BigInteger.valueOf(75287520L)}
        });
    }

    @Test
    public void method1() {
        assertEquals(Main.method2(m,r), expect);
    }
}