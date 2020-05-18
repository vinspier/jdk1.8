package com.vinspier.demo.service;

import com.vinspier.demo.model.Apple;

/**
 * @ClassName: ApplePredicate
 * @Description: 筛选条件的抽象类
 * @Author:
 * @Date: 2020/5/18 9:26
 * @Version V1.0
 **/
@FunctionalInterface
public interface ApplePredicate {
    boolean test(Apple apple);
}
