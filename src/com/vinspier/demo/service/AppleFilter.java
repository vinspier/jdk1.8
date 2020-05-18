package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AppleFilter
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:37
 * @Version V1.0
 **/
public class AppleFilter {

    private AppleFilter() {
    }

    public static List<Apple> filter(List<Apple> apples, ApplePredicate ap){
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple : apples){
            if (ap.test(apple)){
                appleList.add(apple);
            }
        }
        return appleList;
    }

    public static List<String> filter(List<Apple> apples, AppleFormatter af){
        List<String> appleList = new ArrayList<>();
        for (Apple apple : apples){
            appleList.add(af.accept(apple));
        }
        return appleList;
    }
}
