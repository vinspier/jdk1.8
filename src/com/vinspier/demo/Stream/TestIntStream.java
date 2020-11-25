package com.vinspier.demo.Stream;

import java.util.stream.IntStream;

public class TestIntStream {

    public static void main(String[] args) {
        TestIntStream testIntStream = new TestIntStream();
        testIntStream.test1();
        testIntStream.test2();
    }

    /**
     * 产生 1 - 100
     */
    public void test(){
        IntStream.rangeClosed(1,100).forEach(System.out::println);
    }

    /**
     * 计算 1 - 100
     */
    public void test1(){
        int sum =  IntStream.rangeClosed(1,100).sum();
        System.out.println(sum);
    }

    /**
     * 计算 1 - 100的偶数
     */
    public void test2(){
        int sum =  IntStream.rangeClosed(1,100).filter(i -> (i & 1) == 0).sum();
        System.out.println(sum);
    }

}
