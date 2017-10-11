package ru.geekbrains;

public class Employer {
    private String name;
    private String position;
    private String email;
    private long phone;
    private long salary;
    private int age;

    public Employer(String name, String position, String email, long phone, long salary, int age){
        this.age = age;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    void getEployerInfo(){
        System.out.println(this.name + " " + this.position + " " +
                this.email + " " + this.phone + " " +
                this.salary + " " + this.age);
    }

    int getAge() {
        return age;
    }
}
