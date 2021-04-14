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
        // 找到不为空的数组位置 进行添加新数据
        data.put("12",12);
        // 找到不为空的数组位置 进行替换旧数据
        // 1、链表尾插 2、size > 8 转换成树
        data.put("12",12);
        System.out.println(data.size());
        // 数据大小超过负载因子 执行扩容机制
        // 1、创建新数组 2、重新排列数据 a：高低位拆分 b：重整树
        data.put("13",13);
    }

    public void testConcurrentHashMapResize(){
        ConcurrentHashMap<String,Integer> data = new ConcurrentHashMap<>(5);
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
