package com.vinspier.demo.service;

/**
 * @ClassName: B
 * @Description:
 * @Author:
 * @Date: 2020/5/21 15:48
 * @Version V1.0
 **/
public interface B extends A{
   default void test(){
       System.out.println("default method from B");
   }
}
