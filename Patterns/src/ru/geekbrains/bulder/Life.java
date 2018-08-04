package ru.geekbrains.bulder;

import java.util.Arrays;

public class Life {
    private String name;
    private int age;
    private double money;
    private House house;
    private Tree tree;
    private Child[] children;
    private Happiness happiness;

    private Life() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Life{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", house=" + house +
                ", tree=" + tree +
                ", children=" + Arrays.toString(children) +
                ", happiness=" + happiness +
                '}';
    }

    public static class Builder {
        private String name;
        private int age;
        private double money;
        private House house;
        private Tree tree;
        private Child[] children;
        private Happiness happiness;

        private Builder() {
            name = "John";
            age = 0;
            money = 0.0D;
            house = null;
            tree = null;
            children = null;
            happiness = null;
        }

        public Builder buildAge(int age) {
            this.age = age;
            return this;
        }

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildHouse(House house) {
            this.house = house;
            return this;
        }

        public Builder buildHappiness(Happiness happiness) {
            this.happiness = happiness;
            return this;
        }

        public Builder buildChildren(Child... children) {
            this.children = children;
            return this;
        }

        public Builder buildTree(Tree tree) {
            this.tree = tree;
            return this;
        }

        public Builder buildMoney(double money) {
            this.money = money;
            return this;
        }

        public Life build() {
            Life life = new Life();
            life.age = this.age;
            life.name = this.name;
            life.children = this.children;
            life.happiness = this.happiness;
            life.house = this.house;
            life.money = this.money;
            life.tree = this.tree;
            return life;
        }
    }
}
