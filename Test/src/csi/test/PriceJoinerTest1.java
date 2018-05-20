package csi.test;

import csi.src.Price;
import csi.src.PriceJoiner;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class PriceJoinerTest1 {

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

    private LinkedList<Price> result;

    @Before
    public void init() {
        priceJoiner = new PriceJoiner();

        String bd1 = "01.01.2013 00:00:00";
        String ed1 = "31.01.2013 23:59:59";
        String bd2 = "10.01.2013 00:00:00";
        String ed2 = "20.01.2013 23:59:59";
        String bd3 = "01.01.2013 00:00:00";
        String ed3 = "31.01.2013 00:00:00";
        String bd4 = "20.01.2013 00:00:00";
        String ed4 = "20.02.2013 23:59:59";
        String bd5 = "15.01.2013 00:00:00";
        String ed5 = "25.01.2013 23:59:59";
        String bd6 = "12.01.2013 00:00:00";
        String ed6 = "13.01.2013 00:00:00";

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            beginDate1 = dateFormat.parse(bd1);
            beginDate2 = dateFormat.parse(bd2);
            beginDate3 = dateFormat.parse(bd3);
            beginDate4 = dateFormat.parse(bd4);
            beginDate5 = dateFormat.parse(bd5);
            beginDate6 = dateFormat.parse(bd6);
            endDate1 = dateFormat.parse(ed1);
            endDate2 = dateFormat.parse(ed2);
            endDate3 = dateFormat.parse(ed3);
            endDate4 = dateFormat.parse(ed4);
            endDate5 = dateFormat.parse(ed5);
            endDate6 = dateFormat.parse(ed6);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        result = new LinkedList<>(Arrays.asList(
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(1)
                        .depart(1)
                        .begin(beginDate1)
                        .end(endDate4)
                        .value(11000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(2)
                        .depart(1)
                        .begin(beginDate2)
                        .end(beginDate5)
                        .value(99000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("122856")
                        .number(2)
                        .depart(1)
                        .begin(beginDate5)
                        .end(endDate5)
                        .value(92000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(2)
                        .begin(beginDate3)
                        .end(beginDate6)
                        .value(5000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(2)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(2)
                        .begin(endDate6)
                        .end(endDate3)
                        .value(5000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6655")
                        .number(1)
                        .depart(2)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(4)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build()
        ));
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
                        .number(2)
                        .depart(1)
                        .begin(beginDate2)
                        .end(endDate2)
                        .value(99000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(2)
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
                        .number(2)
                        .depart(1)
                        .begin(beginDate5)
                        .end(endDate5)
                        .value(92000)
                        .build(),
                Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(2)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build()
                , Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6654")
                        .number(1)
                        .depart(4)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build()
                , Price.builder()
                        .id(new Random().nextInt())
                        .product_code("6655")
                        .number(1)
                        .depart(2)
                        .begin(beginDate6)
                        .end(endDate6)
                        .value(4000)
                        .build()
        ));

        assertEquals(print(priceJoiner.join(oldPriceList, newPriceList)), print(result));
    }

    private String print(List<Price> linkedList) {
        StringBuilder builder = new StringBuilder();
        for (Price p : linkedList) {
            builder.append(p.getProduct_code());
            builder.append(p.getDepart());
            builder.append(p.getNumber());
            builder.append(p.getBegin());
            builder.append(p.getEnd());
            builder.append(p.getValue());
        }
        return builder.toString();
    }
}