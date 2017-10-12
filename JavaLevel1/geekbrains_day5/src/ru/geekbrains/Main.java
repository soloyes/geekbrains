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

        new Car().createCar(10, 100, "White1", 1.1f, 10, "Corolla1");
        new Car().createCar(20, 110, "White2", 1.2f, 20, "Corolla2");
        new Car().createCar(30, 120, "White3", 1.3f, 30, "Corolla3");
        new Car().createCar(40, 130, "White4", 1.4f, 40, "Corolla4");
        new Car().createCar(50, 140, "White5", 1.5f, 50, "Corolla5");
        new Car().createCar(60, 150, "White6", 1.6f, 60, "Corolla6");
        new Car().createCar(70, 160, "White7", 1.7f, 70, "Corolla7");
        new Car().createCar(80, 170, "White8", 1.8f, 80, "Corolla8");
        new Car().createCar(90, 180, "White9", 1.9f, 90, "Corolla9");
        new Car().createCar(100, 190, "White10", 2.0f, 100, "Corolla10");
        //Not come to array:
        new Car().createCar(110, 200, "White11", 2.1f, 110, "Corolla11");
    //Car.printCar();
    Car.move(Car.cars[0], 100);
    Car.compareCarsVelociity(Car.cars[0], Car.cars[1]);
    }
}
