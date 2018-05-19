package csi;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PriceJoinerTest2 {

    private PriceJoiner priceJoiner;
    private Date beginDate1;
    private Date beginDate2;
    private Date beginDate3;
    private Date beginDate4;
    private Date beginDate5;
    private Date beginDate6;
    private Date endDate1;
    private Date endDate2;
    private Date endDate3;
    private Date endDate4;
    private Date endDate5;
    private Date endDate6;

    @Before
    public void init() {
        priceJoiner = new PriceJoiner();

        String bd1 = "01.01.2013 00:00:00";
        String ed1 = "31.01.2013 23:59:59";
        String bd2 = "10.01.2013 00:00:00";
        String ed2 = "20.01.2013 23:59:59";

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            beginDate1 = dateFormat.parse(bd1);
            beginDate2 = dateFormat.parse(bd2);
            endDate1 = dateFormat.parse(ed1);
            endDate2 = dateFormat.parse(ed2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws CloneNotSupportedException {

        List<Price> oldPriceList = new LinkedList<>(Arrays.asList(
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("1")
                        .number(1)
                        .depart(1)
                        .begin(beginDate1)
                        .end(endDate1)
                        .value(11000)
                        .build()
        ));

        List<Price> newPriceList = new LinkedList<>(Arrays.asList(
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("1")
                        .number(1)
                        .depart(1)
                        .begin(beginDate2)
                        .end(endDate2)
                        .value(12000)
                        .build()
        ));

        print(priceJoiner.join(oldPriceList, newPriceList));
    }

    private void print(List<Price> linkedList) {
        for (Price p : linkedList) {
            System.out.println(p);
        }
    }
}