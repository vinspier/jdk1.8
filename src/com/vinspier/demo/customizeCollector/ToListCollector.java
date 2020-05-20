package com.vinspier.demo.customizeCollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @ClassName: ToListCollector
 * @Description:
 * @Author:
 * @Date: 2020/5/20 13:56
 * @Version V1.0
 **/
public class ToListCollector<T> implements Collector<T,List<T>,List<T>> {

    /**
     * 创建集合操作的起始点
     * */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 累计遍历过的项目，原位修改累加器
     * */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 容器合并
     * 第一个累加器和第二个累加器整合到一起
     * */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 恒等函数
     * */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     *
     * */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
