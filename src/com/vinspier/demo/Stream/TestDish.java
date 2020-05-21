package com.vinspier.demo.Stream;

import com.vinspier.demo.model.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

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
        System.out.println("=================================================================");
        testMultiGroupBy();
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
                                    .collect(toList());
        names.forEach(System.out::println);
    }

    public static void testGroupy(){
        Map<Dish.Type,List<Dish>> dishMap = menus.stream()
                .collect(groupingBy(Dish::getType));
    }

    public static void testGroupyDefinition(){
        Map<Dish.CaloricLevel,List<Dish>> dishMap = menus.stream().collect(groupingBy(d -> {
                    if(d.getCalories() <= 400){
                        return Dish.CaloricLevel.DIET;
                    }else if(d.getCalories() >= 700){
                        return Dish.CaloricLevel.FAT;
                    }else{
                        return Dish.CaloricLevel.NORMAL;
                    }
                }));
    }

    /**
     * 使用嵌套groupingBy
     * 实现多级分组
     * 第二个参数可以接受任意类型的收集器
     * */
    public static void testMultiGroupBy(){
        Map<Dish.Type,Map<Dish.CaloricLevel,List<Dish>>> dishMap = menus.stream()
                .collect(groupingBy(Dish::getType,groupingBy(d -> {
                            if (d.getCalories() <= 400) {
                                return Dish.CaloricLevel.DIET;
                            } else if (d.getCalories() >= 700) {
                                return Dish.CaloricLevel.FAT;
                            } else {
                                return Dish.CaloricLevel.NORMAL;
                            }
                        }))
                    );
        dishMap.forEach((key,value) -> System.out.println(key + ":" + value.toString()));
    }

    /**
     * 使用嵌套groupingBy
     * 实现多级分组
     * 第二个参数可以接受任意类型的收集器
     * */
    public static void testMultiGroupBy1(){
        Map<Dish.Type,Map<Dish.CaloricLevel,List<Dish>>> dishMap = menus.stream()
                .collect(groupingBy(Dish::getType,groupingBy(Dish::getCaloricLevel))
                );
        dishMap.forEach((key,value) -> System.out.println(key + ":" + value.toString()));
    }

    /**
     * 计算每一个分类类型下的数量
     * */
    public static void testCountGroupingBy(){
        Map<Dish.Type,Long> dishMap = menus.stream()
                .collect(groupingBy(Dish::getType,counting()));
    }

    /**
     * 分区
     * 是否为蔬菜分类
     * */
    public static void testPartitioningBy(){
        Map<Boolean,List<Dish>> partitions = menus.stream()
                .collect(partitioningBy(Dish::isVegetarian));
    }

    /**
     * 分区
     * 是否为蔬菜分类
     * 第二个参数 接受任意类型的收集器
     * */
    public static void testPartitioningBy1(){
        Map<Boolean,Map<Dish.Type,List<Dish>>> partitions = menus.stream()
                .collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));

        Map<Boolean,Dish> partitions1 = menus.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }
}
