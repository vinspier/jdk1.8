package com.vinspier.demo.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName: TestJdkPredicate
 * @Description:
 * @Author:
 * @Date: 2020/5/18 16:02
 * @Version V1.0
 **/
public class TestJdkPredicate {
    public static void main(String[] args) {
        test(Arrays.asList("","1","@","3"),(String s) -> s.length() > 0);
    }

    public static void test(List<String> list, Predicate<String> p){
        List<String> result = new ArrayList<>();
        for (String s : list){
            if (p.test(s)){
                result.add(s);
                System.out.println(s);
            }
        }
    }
}
