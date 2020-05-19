package com.vinspier.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @ClassName: TestConsumer
 * @Description:
 * @Author:
 * @Date: 2020/5/18 16:07
 * @Version V1.0
 **/
public class TestJdkConsumer {
    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5),(Integer i) -> System.out.println(i));
    }

    public static void forEach(List<Integer> integers, Consumer<Integer> c){
        for (Integer i : integers){
            c.accept(i);
        }
    }
}