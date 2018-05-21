import csi.src.Price;
import csi.src.PriceJoiner;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;


/*
Квадратичная сложность.
10000 входных и исходных данных <5s при 4ГГц CPU.
100000 - ~30min в один поток.
*/

public class PriceJoinerTest7 {

    private PriceJoiner priceJoiner;

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

    @Before
    public void init() {
        priceJoiner = new PriceJoiner();

    }

    @Test(timeout = 5)
    public void test1() throws CloneNotSupportedException {
        Random rnd = new Random();
        LinkedList<Price> oldPriceList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            oldPriceList.add(Price.builder()
                    .id(new Random().nextInt())
                    .product_code(String.valueOf(rnd.nextInt(15000)))
                    .number(rnd.nextInt(10))
                    .depart(rnd.nextInt(15))
                    .begin(new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong())))
                    .end(new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong())))
                    .value(rnd.nextInt(35000))
                    .build());
        }

        LinkedList<Price> newPriceList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            newPriceList.add(Price.builder()
                    .id(new Random().nextInt())
                    .product_code(String.valueOf(rnd.nextInt(15000)))
                    .number(rnd.nextInt(10))
                    .depart(rnd.nextInt(15))
                    .begin(new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong())))
                    .end(new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong())))
                    .value(rnd.nextInt(35000))
                    .build());
        }

        print(priceJoiner.join(oldPriceList, newPriceList));
    }

    private void print(List<Price> linkedList) {
        for (Price p : linkedList) {
            System.out.println(p);
        }
    }
}