package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: AppleWeightFormater
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:58
 * @Version V1.0
 **/
public class AppleWeightFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        StringBuilder sb = new StringBuilder(apple.getWeight() > 150 ? "heavy" : "light");
        return sb.append("  ").append(apple.getColor()).append(":").toString();
    }
}
