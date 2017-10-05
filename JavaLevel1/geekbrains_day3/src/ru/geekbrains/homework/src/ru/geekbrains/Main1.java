package ru.geekbrains.homework.src.ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

    private static int number = randomNumber(9);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int line;
        for (;;) {
            System.out.println("input 0 <= n <= 9");
            for (int i = 0; i < 3; i++) {
                for (;;) {
                    line = scanner.nextInt();
                    if ( line < 0 || line > 9) System.out.println("0 <= n <= 9");
                    else break;
                }
                if (number < line) System.out.println("n < " + line);
                else if (number > line) System.out.println("n > " + line);
                else if (number == line) {
                    System.out.println("Выигрыш");
                    break;
                }
                if (i == 2){
                    System.out.println("Проигрыш");
                    break;
                }
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            int choise;
            if ((choise = scanner.nextInt()) == 0) {
                scanner.close();
                return;
            }
            number = randomNumber(9);
        }
    }

    private static int randomNumber(int n){
        return new Random().nextInt(n + 1);
    }
}
