package ru.geekbrains.racing;

public class Course {
    private Obstacle[] obstacles;

    Course(Obstacle... obstacles){
        this.obstacles = obstacles;
    }

    void doIt(Team team){
        for(Competitor c:team.getTeam()){
            for (Obstacle o: obstacles){
                o.doIt(c);
            }
        }
    }
}
