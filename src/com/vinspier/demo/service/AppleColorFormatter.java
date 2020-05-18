package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: AppleColorFormater
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:58
 * @Version V1.0
 **/
public class AppleColorFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        StringBuilder sb = new StringBuilder(apple.getWeight() > 150 ? "heavy" : "light");
        return sb.append("  ").append(apple.getColor()).append(":").append(apple.getWeight()).append("g").toString();
    }
}
