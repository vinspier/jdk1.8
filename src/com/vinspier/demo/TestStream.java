package com.vinspier.demo;

import com.vinspier.demo.model.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class TestStream {
    public static void main(String[] args) {
       // testGroup();
       // testSerialize():
        testParallel();
    }

    public static void testGroup(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.add(new Apple("apple1",160,"red") );
        apples.add(new Apple("apple1",180,"yellow") );
        Map<String,List<Apple>> appleMap = apples.stream()
                .filter((Apple a) -> a.getWeight() > 150)
                .collect(groupingBy(Apple::getColor));
        appleMap.forEach((key,value) -> System.out.println(key + "----" + value.size()));
    }

    // 串行化处理 只使用cpu单核
    public static void testSerialize(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.add(new Apple("apple1",160,"red") );
        apples.add(new Apple("apple1",180,"yellow") );
        apples = apples.stream().filter((Apple a) -> a.getWeight() > 150)
                        .collect(Collectors.toList());
        apples.forEach(a -> System.out.println(a.toString()));
    }

    // 并行化处理 尽可能使用多个cpu核
    public static void testParallel(){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("apple2",200,"green") );
        apples.add(new Apple("apple1",100,"green") );
        apples.add(new Apple("apple1",160,"red") );
        apples.add(new Apple("apple1",180,"yellow") );
        apples = apples.parallelStream().filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList());
        apples.forEach(a -> System.out.println(a.toString()));
    }
}
