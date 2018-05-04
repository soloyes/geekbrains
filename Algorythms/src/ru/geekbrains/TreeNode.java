package ru.geekbrains;

public class TreeNode {
    int value;

    public TreeNode left;
    public TreeNode right;

    protected TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}
