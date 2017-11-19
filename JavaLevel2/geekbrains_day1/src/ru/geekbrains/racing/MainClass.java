package ru.geekbrains.racing;

public class MainClass {
    public static void main(String[] args) {

    Course c = new Course(new Cross(300), new Wall(25), new Cross(400));
    Team team = new Team("DreamTeam",
            new Human("Shutle"),
            new Dog("SuperDog"),
            new Cat("SuperCat"),
            new Human("FlameXander"));
    team.showMembers();
        System.out.println();
    c.doIt(team);
        System.out.println();
    team.showResults();
    }
}
