package com.vinspier.demo.Stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: TestMap
 * @Description:
 * @Author:
 * @Date: 2020/5/19 11:00
 * @Version V1.0
 **/
public class TestMap {
    public static void main(String[] args) {
        // flatMap();
        flatMap1();
    }

    /**
     * 流的扁平化
     * */
    public static void flatMap(){
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // 获取左右组合的坐标值
        List<int[]> position = numbers1.stream().flatMap(
               i -> numbers2.stream().map(j -> new int[]{i,j})
        ).collect(toList());
        position.forEach(a ->System.out.println(a[0] + "," +  a[1]));
    }

    /**
     * 流的扁平化
     * */
    public static void flatMap1(){
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // 获取左右组合的坐标值
        List<int[]> position = numbers1.stream().flatMap(
                i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i,j})
        ).collect(toList());
        position.forEach(a ->System.out.println(a[0] + "," +  a[1]));
    }
}
