package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: AppleWeightAndColotPredicate
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:34
 * @Version V1.0
 **/
public class AppleWeightAndColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150 && "green".equals(apple.getColor());
    }
}
