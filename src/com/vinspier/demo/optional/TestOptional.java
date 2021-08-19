package com.vinspier.demo.optional;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Optional 优雅处理null情况
 *
 * @author  vinspier
 * @date    2021/8/19 2:08 下午
 * @version 1.0
*/
public class TestOptional {

    public static void main(String[] args) {
//        testSerialCall();
        testFilter();
    }

    public static Company buildCompany(){
        Company company = new Company();
        company.setName("test company");
        return company;
    }

    public static void testSerialCall(){
        // 第一个optional内部值非空 后续链路（非尾节点）中出现空值 则会抛NPE
        Optional<Company> companyOpt = Optional.ofNullable(buildCompany());

        // flatMap链接optional引用串联
        String leaderName = companyOpt.flatMap(Company::getDepartment)
                .flatMap(Department::getLeader)
                .map(Person::getName)
                .orElse("no leader name");

        // -------------- 上部分会抛NPE

        // 另一种写法
        Optional<Company> companyOpt1 = Optional.ofNullable(null);
        leaderName = companyOpt.flatMap(Company::getDepartment)
                .orElse(new Department())
                .getLeader()
                .map(Person::getName)
                .orElse("no leader name");


        System.out.println(leaderName);
    }

    public static void testFilter(){
        Optional<Company> company = Optional.of(buildCompany());
        // 寻找匹配的值
        String name = company.map(Company::getName)
                .filter(c -> Objects.equals("test",c))
                .orElse("default");

        System.out.println(name);
    }

}
