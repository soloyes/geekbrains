package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        Employer[] employer = new Employer[5];
        employer[0] = new Employer("Name1", "Position1",
                "email1", 1, 10, 10);
        employer[1] = new Employer("Name2", "Position2",
                "email2", 2, 20, 20);
        employer[2] = new Employer("Name3", "Position3",
                "email3", 3, 30, 30);
        employer[3] = new Employer("Name4", "Position4",
                "email4", 4, 40, 40);
        employer[4] = new Employer("Name5", "Position5",
                "email5", 5, 50, 50);

        for (Employer e: employer) {
            if (e.getAge() > 40) e.getEployerInfo();
        }
    }
}
