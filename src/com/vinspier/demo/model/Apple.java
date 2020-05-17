package com.vinspier.demo.model;

public class Apple {

    private String name;
    private int weight;
    private String color;

    public Apple() {
    }

    public Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Apple(String name, int weight, String color) {
        this.name = name;
        this.weight = weight;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
