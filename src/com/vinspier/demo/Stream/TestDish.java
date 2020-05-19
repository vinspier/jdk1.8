package com.vinspier.demo.Stream;

import com.vinspier.demo.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: TestDish
 * @Description:
 * @Author:
 * @Date: 2020/5/19 9:16
 * @Version V1.0
 **/
public class TestDish {

    public static List<Dish> menus = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {
        test1();
    }

    /**
     * 对集合进行calories的筛选
     * 限制为3个
     * 打印出名称
     * */
    public static void test1(){
        List<String> names = menus.stream().filter(d -> d.getCalories() >400)
                                    .map(Dish::getName)
                                    .limit(5)
                                    .collect(Collectors.toList());
        names.forEach(System.out::println);
    }
}
