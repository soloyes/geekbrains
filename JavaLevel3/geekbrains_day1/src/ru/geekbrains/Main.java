package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        String[] a1 = new String[]{"a", "b", "c"};
//        Integer[] a2 = new Integer[]{1,2,3};
//        Double[] a3 = new Double[]{1.0, 2.0, 3.0};
//
//        System.out.println(Arrays.toString(a1));
//        System.out.println(Arrays.toString(a2));
//        System.out.println(Arrays.toString(a3));
//        method(0,1, a1);
//        method(0,1, a2);
//        method(0,1, a3);
//        System.out.println(Arrays.toString(a1));
//        System.out.println(Arrays.toString(a2));
//        System.out.println(Arrays.toString(a3));
//
//        ArrayList<String> arrayList = method1(a1);
//        System.out.println(arrayList.get(0).getClass() + " " + arrayList);
//
//        ArrayList<Integer> arrayList1 = method1(a2);
//        System.out.println(arrayList1.get(0).getClass() + " " + arrayList1);
//
//
//        Box<Apple> boxApples = new Box<>();
//        Box<Orange> boxOranges = new Box<>();
//
//        Box<Apple> boxApples1 = new Box<>();
//        Box<Orange> boxOranges1 = new Box<>();
//
//        Orange[] oranges = new Orange[]{new Orange(10), new Orange(20)};
//        Apple[] apples = new Apple[]{new Apple(10), new Apple(30)};
//        Orange[] oranges1 = new Orange[]{new Orange(10), new Orange(20), new Orange(30)};
//        Apple[] apples1 = new Apple[]{new Apple(10), new Apple(20), new Apple(30)};
//
//        boxApples.put(apples);
//        boxOranges.put(oranges);
//        boxApples1.put(apples1);
//        boxOranges1.put(oranges1);
//
//        System.out.println(Box.getWeight(boxApples));
//        System.out.println(Box.getWeight(boxOranges));
//
//        System.out.println(boxApples.compare(boxOranges));
//
//        boxApples.giveMeMore(boxApples1);
//        boxOranges.giveMeMore(boxOranges1);
//
//        System.out.println(boxApples);
//        System.out.println(boxApples1);
//        System.out.println(boxOranges);
//        System.out.println(boxOranges1);

    }

    private static void method (int index1, int index2, Object... arr){
        if (index1 > arr.length - 1 || index2 > arr.length - 1) return;
        Object t1 = arr[index1];
        Object tmp = arr[index2];
        arr[index1] = tmp;
        arr[index2] = t1;
    }

    private static <T> ArrayList<T> method1 (T[] arr){
        ArrayList<T> a = new ArrayList<>();
        a.addAll(Arrays.asList(arr));
        return a;
    }
}
