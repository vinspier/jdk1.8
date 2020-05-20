package com.vinspier.demo.Stream;

import com.vinspier.demo.customizeCollector.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName: TestPrimeNumber
 * @Description:
 * @Author:
 * @Date: 2020/5/20 11:18
 * @Version V1.0
 **/
public class TestPrimeNumber {
    public static void main(String[] args) {
        Map<Boolean,List<Integer>> group = groupBy(4,20);
        group.forEach((k,v) -> System.out.println(k + ":" + v));
        System.out.println("==================================");
        Map<Boolean,List<Integer>> group1 = customizeGroupingBy(4,20);
        group1.forEach((k,v) -> System.out.println(k + ":" + v));
        System.out.println("==================================");
        testJdkTime();
        System.out.println("==================================");
        testCustomizeTime();
    }

    /**
     * 测试自定义的收集器与jdk提供的收集器的效率
     * */
    public static void testJdkTime(){
        int upperLimit = 1000000;
        long fast = Long.MAX_VALUE;
        for (int i = 0; i < 20; i++){
            long start = System.currentTimeMillis();
            groupBy(2,upperLimit);
            long duration = (System.nanoTime() - start);
            if (duration < fast){
                fast = duration;
            }
        }
        System.out.println("jdk prime method time takes: " + fast + " ms");
    }

    /**
     * 测试自定义的收集器与jdk提供的收集器的效率
     * */
    public static void testCustomizeTime(){
        int upperLimit = 100000;
        long fast = Long.MAX_VALUE;
        for (int i = 0; i < 20; i++){
            long start = System.currentTimeMillis();
            customizeGroupingBy(2,upperLimit);
            long duration = (System.nanoTime() - start);
            if (duration < fast){
                fast = duration;
            }
        }
        System.out.println("customize prime method time takes: " + fast + " ms");
    }

    /**
     * 判断为质数
     * */
    public static boolean isPrime(int candidate){
        int candidateSqrt = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateSqrt)
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 判断为非质数
     * */
    public static boolean negatePrime(int candidate){
        int candidateSqrt = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateSqrt)
                .anyMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean,List<Integer>> groupBy(int lowerLimit,int upperLimit){
        Map<Boolean,List<Integer>> primeGroup = IntStream.rangeClosed(lowerLimit,upperLimit)
                .boxed()
                .collect(Collectors.partitioningBy(candidate ->isPrime(candidate)));
        return primeGroup;
    }

    /**
     * 使用自定义的质数收集器
     * */
    public static Map<Boolean,List<Integer>> customizeGroupingBy(int lowerLimit,int upperLimit){
        Map<Boolean,List<Integer>> primeGroup = IntStream.rangeClosed(lowerLimit,upperLimit)
                .boxed()
                .collect(new PrimeNumbersCollector());
        return primeGroup;
    }
}
