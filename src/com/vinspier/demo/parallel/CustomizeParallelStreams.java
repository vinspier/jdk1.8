package com.vinspier.demo.parallel;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CustomizeParallelStreams {

    /**
     * 原始迭代累计
     * */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 顺序流处理
     * */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 并行流处理
     * 存在装箱机制
     * */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 并行流处理
     * 不存在装箱机制
     *
     * 1、并行化并不是没有代价的。并行化过程本身需要对流做递归划分
     * 2、要保证在内核中并行执行工作的时间比在内核之间传输数据的时间长
     * */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     *
     * 不存在装箱机制
     * */
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }



}
