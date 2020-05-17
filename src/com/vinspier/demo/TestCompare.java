package com.vinspier.demo;

import com.vinspier.demo.model.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TestCompare {
    public static void main(String[] args) {
      // test1();
      // test2();
       test3();
    }

    public static void test1(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.forEach(apple -> System.out.println(apple.toString()));
    }

    public static void test2(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples = filterApples(filterApples(apples,Apple::isGreenApple),Apple::isHeavyApple);
        apples.forEach(apple -> System.out.println(apple.toString()));
    }

    public static void test3(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples = filterApples(apples,(Apple a) ->
            a.getWeight() > 150 && a.getColor().equals("green")
        );
        apples.forEach(apple -> System.out.println(apple.toString()));
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
