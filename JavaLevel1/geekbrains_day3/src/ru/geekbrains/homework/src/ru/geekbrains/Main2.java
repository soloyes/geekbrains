package ru.geekbrains.homework.src.ru.geekbrains;

import java.util.Random;

public class Main2 {

    private static String[] words = {
            "apple", "orange", "lemon", "banana",
            "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear",
            "pepper", "pineapple", "pumpkin", "potato"
    };

    public static void main(String[] args) {

    }

    private static String randomNumber(){
        return words[new Random().nextInt(words.length)];
    }
}
