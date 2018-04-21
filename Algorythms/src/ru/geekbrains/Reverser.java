package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reverser {

    public String setString(){
        String s = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Input string");
            s =  reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String reverse(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            stringBuilder.append(string.charAt(i));
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
