# optional总结

## 1、使用optional可以优雅的处理null的情况

## 2、可以使用map来获取对象值 并且默认行为orElse方法提供默认返回

## 3、提供flatMap方法 链接多链路的optional 来代替 多层 if 判断
   + 注意点：
      + 当首个optional内部值为空时 且 必须为 Optional.EMPTY 或者 new Optional<>()时 调用链才可正常执行
      + 当首个optional内部值不为空时 ，那么在调用第一个flatMap时 会抛NPE；因为源码里面对结果做了非空校验
     
## 4、提供ifPresent方法 判断内部值是否为空

## 5、提供filter方法 可进行条件判断
   