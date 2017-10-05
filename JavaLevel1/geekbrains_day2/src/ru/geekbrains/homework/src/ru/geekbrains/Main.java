package ru.geekbrains.homework.src.ru.geekbrains;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Ordinary methods
        method1();
        method2();
        method3();
        method4(5);
        method5();
        //

        //method6:
        System.out.println(method6(new int[]{1, 1, 1, 2, 1})); //true
        System.out.println(method6(new int[]{2, 1, 1, 2, 1})); //false
        System.out.println(method6(new int[]{10, 10})); //true
        System.out.println(method6(new int[]{1, 2, 3, 4, 10})); //true
        System.out.println(method6(new int[]{1, 2})); //false
        System.out.println(method6(new int[]{1})); //true
        //

        //method7
        System.out.println(Arrays.toString(method7(new int[]{1, 2, 3, 4, 5}, 1)));
        System.out.println();
        System.out.println(Arrays.toString(method7(new int[]{1, 2, 3, 4, 5}, -1)));
        System.out.println();
        System.out.println(Arrays.toString(method7(new int[]{1}, -1)));
        System.out.println();
        System.out.println(Arrays.toString(method7(new int[]{1, 2, 3, 4, 5}, 6)));
        //

        //Extra methods
        System.out.println(Arrays.toString(method8(new int[]{0,0,0,0,1,1,1,1,1,1,1,0,0,1,1,1,1})));
        System.out.println(Arrays.toString(method8(new int[]{0,0,0,0,1,1,1,1,1,1,1,1,1})));
        System.out.println(Arrays.toString(method8(new int[]{0,0,0,1,1,1,1,1,0,1,1,1})));

        System.out.println(method9(new int[]{1,2,3,4,5}));

        System.out.println(method10(new int[]{1,2,3,4,5}, 4));

        System.out.println(method11(new int[][]{{1,1},{1,1}}));
        //
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
                 min = array[i];
             }
             if (array[i] > max){
                 max = array[i];
             }
         }
         out(array);
         System.out.println("min:" + min + " max: " + max);
     }

     public static boolean method6(int[] array) {
         if (array.length == 1) return true;
         for (int j = 0; j < array.length; j ++) {
             int tmp1 = 0;
             int tmp2 = 0;
             for (int i = 0; i < array.length; i++) {
                 if (i <= j) tmp1 += array[i];
                 if (i > j) tmp2 += array[i];
             }
             if (tmp1 == tmp2) return true;
         }
     return false;
     }

     public static int[] method7 (int[] array, int n){
         System.out.println(Arrays.toString(array));
         n = n%array.length;
         if (n == 0 || array.length == 1) return array;
         if (n > 0) {
             for (int i = 0; i < n; i++) {
                 int last = array[array.length - 1];
                 for (int j = array.length - 1; j > 0; j--) {
                     array[j] = array[j - 1];
                 }
                 array[0] = last;
             }
         }
         if (n < 0){
             n = -n;
             for (int i = 0; i < n; i++) {
                 int first = array[0];
                 for (int j = 0; j < array.length - 1; j++) {
                     array[j] = array[j + 1];
                 }
                 array[array.length - 1] = first;
             }
         }
         return array;

     }
     public static int[] method8(int[] array){
         //Without ArrayList
         System.out.println(Arrays.toString(array));
         int[] out = new int[array.length];
         int tmp = 1;
         int j = 0;
         for (int i = 0; i < array.length - 1; i++) {
             if (array[i] == array[i + 1]) {
                 tmp ++;
             }
             else {
                 out[j] = tmp;
                 tmp = 1;
                 j ++;
             }
         }
         out[j] = tmp;

         int tmp1 = 0;
         for (int i = 0; i < out.length - 1; i++) {
             if(out[i] == 0) break;
             tmp1 ++;
         }

         int[] out1 = new int[tmp1];

         for (int i = 0; i < tmp1; i++) {
             out1[i] = out[i];
         }
         return out1;
     }

     private static double method9(int[] array){
         double tmp = 0;
         int n = 0;
         for (int i = 1; i < array.length - 1; i+=2) {
             tmp += array[i];
             n ++;
         }
         return tmp/n;
     }

     private static long method10(int[] array, int n){
         //Only first income
         int j = -1;
         int res = 0;
         for (int i = 0; i < array.length - 1; i++) {
             if (array[i] == n) j = i;
         }
         if (j > 0){
             for (int i = 0; i < j + 1; i++) {
                 res += array[i];
             }
         }
         return res;
     }

     private static long method11(int[][] array){
         int res = 0;
         for (int i = 0; i < array.length; i++) {
             for (int j = 0; j < array[i].length; j++) {
                 res += array[i][j];
             }
         }
         return res;
     }
}
