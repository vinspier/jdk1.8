package com.vinspier.demo.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * jdk普通类测试
 *
 * @author fxb
 * @date 2021/3/31 8:26 下午
 **/
public class TestMap {

    private final int size = 10000;
    private ExecutorService service = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        TestMap testMap = new TestMap();
//         testMap.testHashMapResize();
        testMap.testConcurrentHashMapResize();
    }

    public void testHashMapResize(){
        Map<String,Integer> data = new HashMap<>();
        for (int i = 0; i < 11; i++){
            data.put("" + i,i);
        }
        data.put("12",12);
        data.put("12",12);
        System.out.println(data.size());
        data.put("13",13);
    }

    public void testConcurrentHashMapResize(){
        Map<String,Integer> data = new ConcurrentHashMap<>(5);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < size; i++){
            final int key = i;
            service.submit(() -> {
                countDownLatch.countDown();
                data.put("" + key,key);
            });
        }
        try {
            countDownLatch.await();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("多线程执行任务失败 msg=" + e.getMessage());
        }finally {
            service.shutdown();
        }

        System.out.println("map data size = " + data.size());
    }


}
