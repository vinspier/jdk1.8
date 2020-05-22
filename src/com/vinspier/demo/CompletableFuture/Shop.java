package com.vinspier.demo.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @ClassName: Shop
 * @Description:
 * @Author:
 * @Date: 2020/5/22 9:54
 * @Version V1.0
 **/
public class Shop {

    private static final Random random = new Random();
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceReturnString(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    // 异步 创建一个CompletableFuture对象返回调用者
    // 如果计算价格出错 会导致 外部调用者使用futurePrice.get()一直阻塞
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }


    // 异步 创建一个CompletableFuture对象返回调用者
    // 如果计算价格出错 会导致 外部调用者使用futurePrice.get()一直阻塞
    // 因此 主动抛出异常
    public Future<Double> getPriceAsyncThrowsE(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    // 使用工厂方法supplyAsync 交由ForkJoinPool池中的某个执行线程（ Executor）
    // 错误管理机制同getPriceAsyncThrowsE
    public Future<Double> supplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // 模拟1秒钟延迟的方法
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }
}
