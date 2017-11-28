package ru.geekbrains;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneList {
    private Map<Long, String> map = new TreeMap<>();

    private Pattern p = Pattern.compile("\\d{11}");

    void add(String lastName, String phone){
        if (lastName == null || phone == null){
            System.out.println("WARN: must set lastName and phone");
            return;
        }

        if (lastName.isEmpty()) {
            System.out.println("WARN: must set lastName at least with one symbol");
            return;
        }

        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            System.out.println("WARN: " + phone + " is Not a phone");
            return;
        }

        map.put(Long.parseLong(phone), lastName);
    }

    void get(String lastName){
        System.out.println("Phones for " + lastName + " are below:");
        for (Map.Entry<Long, String> x: map.entrySet()) {
            if (x.getValue().equals(lastName)){
                System.out.println(x.getKey());
            }
        }
    }
}
