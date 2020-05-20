package com.vinspier.demo.customizeCollector;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @ClassName: PrimeCollector
 * @Description: 自实现质数检验器
 * 缺点：必须从2开始
 * @Author:
 * @Date: 2020/5/20 15:16
 * @Version V1.0
 **/
public class PrimeNumbersCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {

    /**
     * 创建集合操作的起始点
     * */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){
                {
                    put(true,new ArrayList<Integer>());
                    put(false,new ArrayList<Integer>());
                }};
    }


    /**
     * 累计遍历过的项目，原位修改累加器
     * 从原来的质数列表中拿取小于平方分的集合
     * 进行判断
     * */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc,Integer candidate) ->{
            acc.get(isPrime(acc.get(true),candidate)).add(candidate);
        };
    }


    /**
     * 实际上这个收集器是不能并行使用的，因为该算法本身是顺序的。
     * 这意味着永远都不会调用combiner方法，你可以把它的实现留空
     * */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 它既不是CONCURRENT也不是UNORDERED
     * */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateSqrt = (int) Math.sqrt((double) candidate);
        boolean prime = takeWhile(primes,i -> i <= candidateSqrt)
                .stream()
                .noneMatch(p -> candidate % p == 0);
        return prime;
    }

    /**
     * 截取小于被检验数的平方根的质数集
     * */
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
