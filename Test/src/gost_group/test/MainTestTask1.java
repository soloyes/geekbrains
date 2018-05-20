package gost_group.test;

import java.util.Arrays;
import java.util.Collection;

import gost_group.src.Main;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainTestTask1 {

    private Integer value;
    private String expect;

    public MainTestTask1(Integer value, String expect) {
        this.value = value;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {0, "TwoSeven"},
                {2, "Two"},
                {6, "Two"},
                {7, "Seven"},
                {14, "TwoSeven"},
                {15, "15"},
                {98, "TwoSeven"},
                {27, "27"}
        });
    }

    @Test
    public void method1() {
        Assert.assertEquals(Main.method1(value), expect);
    }
}