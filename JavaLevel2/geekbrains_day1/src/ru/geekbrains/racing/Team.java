package ru.geekbrains.racing;

public class Team {
    private String teamName;
    private Competitor[] team;

    public Competitor[] getTeam() {
        return team;
    }

    Team(String teamName, Competitor... team){
        this.teamName = teamName;
        this.team = team;
    }

    void showMembers(){
        System.out.println(teamName + " members:");
        for (Competitor t: this.team) {
            System.out.println(t.getClass().getSimpleName() + " " + t.getName());
        }
    }

    void showResults(){
        for (Competitor c: this.team){
            System.out.println(c.getName() + (c.isOnDistance()? " Win" : " Lose"));
        }
    }
}
