package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: ApplePredicateWeight
 * @Description: 筛选条件的具体实现
 * @Author:
 * @Date: 2020/5/18 9:28
 * @Version V1.0
 **/
public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
