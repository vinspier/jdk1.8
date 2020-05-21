package com.vinspier.demo.lambda;

import com.vinspier.demo.model.CustomizeTask;

/**
 * @ClassName: Test
 * @Description:
 * @Author:
 * @Date: 2020/5/21 9:55
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        // 当lambda表达式满足多个时 可现实进行类型转换
        doSomething((Runnable)() -> {
            System.out.println("=======");
        });
    }

    public static void doSomething(Runnable r){
        r.run();
    }

    public static void doSomething(CustomizeTask t){
        t.execute();
    }
}
