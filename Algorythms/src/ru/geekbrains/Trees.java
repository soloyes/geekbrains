package ru.geekbrains;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class Trees {
    /**
     * **** Создать программу для построения двоичного дерева.
     * В цикле построить двадцать деревьев из 32-63 элементов.
     * Данные, которыми необходимо заполнить узлы деревьев представляются в
     * виде чисел типа int. Число, которое попадает в узел, должно
     * генерироваться случайным образом в диапазоне от -100 до 100.
     * Запустить программу.
     * <p>
     * **** Проанализировать, какой процент созданных деревьев является
     * несбалансированными.
     */
    public static void main(String[] args) {
        List<Tree> treeList = new LinkedList<>();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 20; i++) {
            Tree tree = new Tree();
            for (int j = 0; j < 63 - random.nextInt(32); j++) {
                tree.insert(100 - random.nextInt(201));
            }
            treeList.add(tree);
        }

        int count = 0;
        for (int i = 0; i < treeList.size(); i++) {
            count += treeList.get(i).balance() ? 1 : 0;
        }

        System.out.println(String.format("%s %d", "Total trees count:", treeList.size()));
        System.out.println(String.format("%s %d%s", "Balanced:", count * 100 / treeList.size(), "%"));
    }
}