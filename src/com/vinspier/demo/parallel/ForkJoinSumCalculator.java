package com.vinspier.demo.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static ForkJoinPool forkPool = new ForkJoinPool();

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 异步执行左边拆分计算
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        // 同步执行右边拆分计算
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        // 等待左边执行完成 否则阻塞
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    /**
     * 当任务不可在拆分时 执行计算
     * */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return forkPool.invoke(task);
    }

    public static void main(String[] args) {

    }
}
