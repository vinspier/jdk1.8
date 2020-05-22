package com.vinspier.demo.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: Test
 * @Description:
 * @Author:
 * @Date: 2020/5/22 10:09
 * @Version V1.0
 **/
public class Test {

    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    // 创建一个线程池，线程池中线程的数
    // 目为 100和商店数目二者中较小的一个值
    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    new ThreadFactory() {
                        // 如果将线程标记为守护进程，意味着程序退出时它也会被回收
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
       // 执行更多任务，比如查询其他商店
        doSomethingElse();
       // 在计算商品价格的同时
        try {
            // 要么获得Future中封装的值（如果异步任务已经完成），
            // 要么发生阻塞，直到该异步任务完成
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void doSomethingElse(){
        System.out.println("处理其他业务");
    }

    // 采用顺序查询所有商店的方式
    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }
    // 采用顺序查询所有商店的方式
    public static List<String> findPrices1(String product) {
        return shops.stream()
                .map(shop -> shop.getPriceReturnString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    // 采用并行流查询所有商店的方式
    public static List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    // 使用CompletableFuture实现
    public List<String> findPricesAsync(String product) {
        // 使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product)))
                        .collect(Collectors.toList());
        // 等待所有异步操作结束
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    // 使用CompletableFuture实现 使用自定义的线程执行器
    public List<String> findPricesAsyncCustomize(String product) {
        // 使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),executor))
                        .collect(Collectors.toList());
        // 等待所有异步操作结束
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public List<String> findPrices2(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceReturnString(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                        .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * 重构findPrices方法返回一个由Future构成的流
     * CompletableFuture 通 过 thenAccept 完成执行后使用它的返 回 值
     * findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println));
     * */
    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceReturnString(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)));
    }


}
