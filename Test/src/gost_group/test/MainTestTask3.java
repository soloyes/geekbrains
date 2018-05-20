package gost_group.test;

import gost_group.src.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MainTestTask3 {

    private String text1 =
            "Карл у Клары украл кораллы, а Клара у Карла украла кларнет. " +
                    "Королева Клара кавалера Карла строго карала за кражу кораллов. " +
                    "Карл у Клары украл кораллы, Клара у Карла украла кларнет. Если бы " +
                    "Карл не крал у Клары кораллы, то Клара не крала б у Карла кларнет.";

    private String text2 =
            "Корабли лавировали, лавировали, да не вылавировали";

    private Map<String, Long> map1 = new LinkedHashMap<String, Long>() {
        {
            put("у", 6L);
            put("Клара", 4L);
            put("Карла", 4L);
            put("кларнет", 3L);
            put("Клары", 3L);
            put("кораллы", 3L);
            put("Карл", 3L);
            put("не", 2L);
            put("украла", 2L);
            put("украл", 2L);
            put("Если", 1L);
            put("Королева", 1L);
            put("строго", 1L);
            put("карала", 1L);
            put("крал", 1L);
            put("а", 1L);
            put("б", 1L);
            put("крала", 1L);
            put("кораллов", 1L);
            put("кражу", 1L);
            put("за", 1L);
            put("бы", 1L);
            put("то", 1L);
            put("кавалера", 1L);
        }
    };

    private Map<String, Long> map2 = new LinkedHashMap<String, Long>(){
        {
            put("лавировали", 2L);
            put("Корабли", 1L);
            put("не", 1L);
            put("вылавировали", 1L);
            put("да", 1L);
        }
    };

    @Test
    public void method1() {
        Assert.assertEquals(Main.method3(text1).toString(), map1.toString());
        assertEquals(Main.method3(text2).toString(), map2.toString());
        assertNotEquals(Main.method3(text1).toString(), map2.toString());
    }
}