package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: AppleFormater
 * @Description:
 * @Author:
 * @Date: 2020/5/18 9:57
 * @Version V1.0
 **/
@FunctionalInterface
public interface AppleFormatter {
    String accept(Apple apple);
}
