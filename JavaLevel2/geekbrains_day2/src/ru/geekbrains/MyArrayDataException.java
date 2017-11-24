package ru.geekbrains;

public class MyArrayDataException extends Exception {
    private String message;
    private int i, j;
    MyArrayDataException(String message, int i, int j){
        super(message);
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
