package ru.geekbrains.homework.src.ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Main2 {

    private static String[] words = {
            "apple", "orange", "lemon", "banana",
            "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear",
            "pepper", "pineapple", "pumpkin", "potato"
    };
    private static char[] field = new char[]{
            '#','#','#','#','#',
            '#','#','#','#','#',
            '#','#','#','#','#'};
    private static String answer = randomWord();

    public static void main(String[] args) {
        System.out.println(answer);
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            System.out.println("your word is: ");
            String word = scanner.nextLine();
            int j = answer.length() < word.length() ? answer.length() : word.length();

            for (int i = 0; i < j; i++) {
                if (answer.charAt(i) == word.charAt(i)) field[i] = answer.charAt(i);
            }
            if (answer.equals(checkAnswer())) break;
            System.out.println(field);
        }

        scanner.close();
    }

    private static String randomWord(){ return words[new Random().nextInt(words.length)]; }

    private static String checkAnswer(){ return String.valueOf(field, 0, answer.length()); }
}
