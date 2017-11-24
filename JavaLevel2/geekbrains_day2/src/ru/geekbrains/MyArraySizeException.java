package ru.geekbrains;

public class MyArraySizeException extends Exception{
    private String message;
    MyArraySizeException(String message){
        super(message);
    }
}
