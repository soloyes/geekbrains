package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        System.out.println(power(5,15));
        System.out.println(fast(5,15));
    }

    //power: O(n) - выполняем действие n-раз
    private static long power(int a, int n) {
        long res = a;
        for (int i = 0; i < n - 1; i++) {
            res *= a;
        }
        return res;
    }

    //Оптимизация: алгоритм быстрого возведения в степень
    //Так как каждые две итерации степень сокращается в двое, сложность такого алгоритма логарифмическая
    //O(logn)
    private static long fast(int a, int n){
            long res = 1;
            while (n != 0) {
                if ((n & 1) != 0)
                    res *= a;
                a *= a;
                n >>= 1;
            }
            return res;
    }

    //min O(n) - выполняем действие n-раз
    private static int min(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (i < array[i]) min = i;
        }
        return min;
    }
    //Оптимизация: бинарный поиск

    //median O(n) - выполняем действие n-раз
    private static float median(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += i;
        }
        return sum / array.length;
    }
    //Оптимизация: при большом массиве, наверно, Map-reduce
}
