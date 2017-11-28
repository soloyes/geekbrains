package ru.geekbrains;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static String[] strings = {"A", "B", "C", "C", "E", "F", "G", "H", "I", "I"};

    private static Map<String, Integer> map = new TreeMap<>();

    public static void main(String[] args) {
        for (String string : strings) {
            if (map.get(string) != null) map.replace(string, map.get(string) + 1);
            map.putIfAbsent(string, 1);
        }

        for (Map.Entry x:map.entrySet()) {
            System.out.println(x.getKey() + " " + x.getValue());
        }

    PhoneList phoneList = new PhoneList();

        //Test bad values
        phoneList.add("S", "2");
        phoneList.add("S", "");
        phoneList.add("", "2");
        phoneList.add("S", null);
        phoneList.add(null, "2");
        phoneList.add("A", "123456789");
        phoneList.get("A");phoneList.get("B");phoneList.get("C");phoneList.get("D");

        //Well values
        phoneList.add("A", "12345678901");
        phoneList.add("A", "12345678902");
        phoneList.add("A", "12345678903");
        phoneList.add("B", "12345678904");
        phoneList.add("C", "12345678905");
        phoneList.add("D", "12345678906");
        phoneList.get("A");phoneList.get("B");phoneList.get("C");phoneList.get("D");

        //Change values
        phoneList.add("B", "12345678901");
        phoneList.add("B", "12345678902");
        phoneList.get("A");phoneList.get("B");phoneList.get("C");phoneList.get("D");
    }
}
