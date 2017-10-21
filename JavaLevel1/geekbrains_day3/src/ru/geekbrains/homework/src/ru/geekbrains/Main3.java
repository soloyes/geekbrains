package ru.geekbrains.homework.src.ru.geekbrains;

public class Main3{
    private int N = 4;
    private int[][] field = new int[N][N];

    public static void main(String[] args) {
        Main3 main3 = new Main3();
        main3.find();
    }

    private void find() {
        for (int i = 0; i < N; i ++) {
            int[] line0 = new int[N];
            line0[i] = 1;
            field[0] = line0;
            for (int j = 0; j < N; j ++) {
                if (j != i) {
                    int[] line1 = new int[N];
                    line1[j] = 2;
                    field[1] = line1;
                    for (int k = 0; k < N; k ++) {
                        if (k != i && k != j) {
                            int[] line2 = new int[N];
                            line2[k] = 3;
                            field[2] = line2;
                            for (int l = 0; l < N; l ++) {
                                if (l != i && l != j && l != k) {
                                    int[] line3 = new int[N];
                                    line3[l] = 4;
                                    field[3] = line3;
                                }
                            }
                            for (int m = 0; m < N; m ++) {
                                for (int o = 0; o < N; o ++) {
                                    System.out.print(field[m][o] + " ");
                                }
                                System.out.println();
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}


