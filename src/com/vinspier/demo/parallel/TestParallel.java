package com.vinspier.demo.parallel;

import java.util.function.Function;

public class TestParallel {
    public static void main(String[] args) {
//        System.out.println("Iterative sum done in:" +
//                measureSumPerf(CustomizeParallelStreams::iterativeSum, 10_000_000) + " msecs");
//        System.out.println("Sequential sum done in:" +
//                measureSumPerf(CustomizeParallelStreams::sequentialSum, 10_000_000) + " msecs");
//        System.out.println("Parallel sum done in: " +
//                measureSumPerf(CustomizeParallelStreams::parallelSum, 10_000_000) + " msecs" );
//        System.out.println("Ranged sum done in: " +
//                measureSumPerf(CustomizeParallelStreams::rangedSum, 10_000_000) + " msecs" );
//        System.out.println("ParallelRangedSum sum done in: " +
//                measureSumPerf(CustomizeParallelStreams::parallelRangedSum, 10_000_000) + " msecs" );

        System.out.println("ForkJoin sum done in: " +
                measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs" );
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

}
