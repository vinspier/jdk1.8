package com.vinspier.demo.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName: TestJdkFunction
 * @Description:
 * @Author:
 * @Date: 2020/5/18 16:17
 * @Version V1.0
 **/
public class TestJdkFunction {

    public static void main(String[] args) {
        map(Arrays.asList("12345","sdfghj","s"),(String s) -> s.length())
                .forEach((String key ,Integer i) -> System.out.println(key + ":" + i));
    }

    public static <T,R> Map<T,R> map(List<T> res, Function<T,R> f){
        Map<T, R> trHash = new HashMap<>();
        for (T t : res){
            trHash.put(t,f.apply(t));
        }
        return trHash;
    }
}
