package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: ApplePredicateColor
 * @Description: 筛选条件具体实现
 * @Author:
 * @Date: 2020/5/18 9:28
 * @Version V1.0
 **/
public class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
