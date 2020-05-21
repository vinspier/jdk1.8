package com.vinspier.demo.model;

/**
 * @ClassName: Dish
 * @Description:
 * @Author:
 * @Date: 2020/5/19 9:13
 * @Version V1.0
 **/
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    /**
     * 将lambda表达式 抽取出来
     * 定义成一个方法 供方法应用传入lambda表达式中
     * */
    public CaloricLevel getCaloricLevel(){
        if (this.getCalories() <= 400)
            return CaloricLevel.DIET;
        else if (this.getCalories() <= 700)
            return CaloricLevel.NORMAL;
        else
            return CaloricLevel.FAT;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }
}
