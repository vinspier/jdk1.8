package com.vinspier.demo.Stream;

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
        Map<Boolean,List<Integer>> group = groupBy(2,20);
        group.forEach((k,v) -> System.out.println(k + ":" + v));
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

    public static Map<Boolean,List<Integer>> groupBy(int lowerLimit,int upperLimt){
        Map<Boolean,List<Integer>> primeGroup = IntStream.rangeClosed(lowerLimit,upperLimt)
                .boxed()
                .collect(Collectors.partitioningBy(candidate ->isPrime(candidate)));
        return primeGroup;
    }
}
