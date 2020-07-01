package com.vinspier.demo.lambda;

import com.vinspier.demo.model.Apple;
import com.vinspier.demo.service.AppleFilter;
import com.vinspier.demo.service.AppleWeightPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestPredicate
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:32
 * @Version V1.0
 **/
public class TestPredicate {
    public static void main(String[] args) {
        testPredicate();
        testLambda();
    }

    public static void testPredicate(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.add(new Apple("apple1",160,"red") );
        apples.add(new Apple("apple1",180,"yellow") );
        apples = AppleFilter.filter(apples,new AppleWeightPredicate());

        // 与上同效果
//        apples = apples.stream()
//                .filter(apple -> apple.getWeight() > 100)
//                .collect(Collectors.toList());
        System.out.println(apples.size());
    }

    public static void testLambda(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.add(new Apple("apple1",160,"red") );
        apples.add(new Apple("apple1",180,"yellow") );
        apples = AppleFilter.filter(apples,(Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(apples.size());
    }

}
