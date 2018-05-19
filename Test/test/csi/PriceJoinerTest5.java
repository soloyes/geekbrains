package csi;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PriceJoinerTest5 {

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
        String bd2 = "01.02.2013 00:00:00";
        String ed2 = "20.02.2013 23:59:59";
        String bd3 = "21.02.2013 00:00:00";
        String ed3 = "31.03.2013 00:00:00";
        String bd4 = "15.01.2013 00:00:00";
        String ed4 = "15.02.2013 23:59:59";
        String bd5 = "16.02.2013 00:00:00";
        String ed5 = "15.03.2013 23:59:59";

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            beginDate1 = dateFormat.parse(bd1);
            beginDate2 = dateFormat.parse(bd2);
            beginDate3 = dateFormat.parse(bd3);
            beginDate4 = dateFormat.parse(bd4);
            beginDate5 = dateFormat.parse(bd5);
            endDate1 = dateFormat.parse(ed1);
            endDate2 = dateFormat.parse(ed2);
            endDate3 = dateFormat.parse(ed3);
            endDate4 = dateFormat.parse(ed4);
            endDate5 = dateFormat.parse(ed5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws CloneNotSupportedException {

        List<Price> oldPriceList = new LinkedList<>(Arrays.asList(
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate1)
                        .end(endDate1)
                        .value(11000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate2)
                        .end(endDate2)
                        .value(99000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate3)
                        .end(endDate3)
                        .value(5000)
                        .build()
        ));

        List<Price> newPriceList = new LinkedList<>(Arrays.asList(
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate4)
                        .end(endDate4)
                        .value(11000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate5)
                        .end(endDate5)
                        .value(92000)
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