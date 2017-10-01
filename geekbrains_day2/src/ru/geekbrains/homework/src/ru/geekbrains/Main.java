package ru.geekbrains;
;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        method1();
        method2();
        method3();
        method4(5);
        method5();
        System.out.println(method6(new int[]{1, 1, 1, 2, 1}));
        System.out.println(method6(new int[]{2, 1, 1, 2, 1}));
        System.out.println(method6(new int[]{10, 10}));
        System.out.println(method6(new int[]{1, 2, 3, 4, 10}));
        System.out.println(method6(new int[]{1, 2}));
    }

    private static void out(int[] array){
        System.out.println(Arrays.toString(array));
        System.out.println();
    }
    private static void out(int[][] array){
        System.out.println(Arrays.deepToString(array));
        System.out.println();
    }

    private static void method1(){
        int[] array = {1,1,0,0,1,0,1,1,0,0};
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
                continue;
            }
            if (array[i] == 1) {
                array[i] = 0;
            }
        }
        out(array);
    }

    private static void method2(){
        int[] array = new int[8];
        for (int i = 0, j = 0; i < array.length; i ++ ,j += 3) {
            array[i] = j;
        }
        out(array);
    }

    private static void method3(){
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) array[i] *= 2;
        }
        out(array);
    }

    private static void method4(int n){
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length; i++) {
            array[i][n - i - 1] = 1;
            array[i][i] = 1;
        }
        out(array);
     }

     private static void method5(){
         int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
         int min = array[0];
         int max = array[0];
         for (int i = 1; i < array.length; i++) {
             if (array[i] < min){
                 int tmp = array[i];
                 array[i] = min;
                 min = tmp;
             }
             if (array[i] > max){
                 int tmp = array[i];
                 array[i] = max;
                 max = tmp;
             }
         }
         out(array);
         System.out.println("min:" + min + " max: " + max);
     }

     public static boolean method6(int[] array) {
     
     }
}
