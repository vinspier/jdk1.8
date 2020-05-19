package com.vinspier.demo.Stream;

import com.vinspier.demo.model.Trader;
import com.vinspier.demo.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @ClassName: TestTransaction
 * @Description:
 * @Author:
 * @Date: 2020/5/19 14:21
 * @Version V1.0
 **/
public class TestTransaction {

    public static Trader raoul = new Trader("Raoul", "Cambridge");
    public static Trader mario = new Trader("Mario","Milan");
    public static Trader alan = new Trader("Alan","Cambridge");
    public static Trader brian = new Trader("Brian","Cambridge");

    public static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        test();
        System.out.println("---------------------------------------------------------");
        testCity();
        System.out.println("---------------------------------------------------------");
        testTrader();
        System.out.println("---------------------------------------------------------");
        testTraders1();
    }

    // 找出2011年的所有交易并按交易额排序（从低到高）
    public static void test(){
        List<Transaction> result = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        result.forEach(System.out::println);
    }

    // 交易员都在哪些不同的城市工作过
    public static void testCity(){
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        cities.forEach(System.out::println);
    }

    // 交易员都在哪些不同的城市工作过
    public static void testCity1(){
        Set<String> cities = transactions.stream().map(t -> t.getTrader().getCity())
                .collect(toSet());
        cities.forEach(System.out::println);
    }

    // 查找所有来自于剑桥的交易员，并按姓名排序
    public static void testTrader(){
        List<Trader> traders = transactions.stream().map(t -> t.getTrader())
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        traders.forEach(System.out::println);
    }

    // 返回所有交易员的姓名字符串，按字母顺序排序
    public static void testTraders(){
        List<String> traders = transactions.stream().map(t -> t.getTrader().getName())
                .sorted()
                .collect(toList());
        traders.forEach(System.out::println);
    }

    // 返回所有交易员的姓名字符串，按字母顺序排序
    public static void testTraders1(){
//        String traders = transactions.stream().map(t -> t.getTrader().getName())
//                .sorted()
//                .reduce("",(x,y) -> x + "," + y);
        // 内部会使用StringBuilder做字符串的拼接
        String traders = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        System.out.println(traders);
    }

    // 有没有交易员是在米兰工作的
    public static void anyMatch(){
        boolean existed = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
    }

    // 打印生活在剑桥的交易员的所有交易额
    // 存在 装箱机制
    public static void total(){
        Integer sum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0,Integer::sum);
    }

    // 打印生活在剑桥的交易员的所有交易额
    public static void total1(){
        // mapToInt 转换成IntStream
        int sum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
    }

    // 存在 装箱机制
    public static void max(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    // 不存在 装箱机制
    public static void max1(){
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
    }

    // 存在 装箱机制
    public static void min(){
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
    }

    // 不存在 装箱机制
    public static void min1(){
        OptionalInt min = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
    }
}
