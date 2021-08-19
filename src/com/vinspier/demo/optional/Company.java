package com.vinspier.demo.optional;

import java.util.List;
import java.util.Optional;

/**
 * 公司信息
 *
 * @author  vinspier
 * @date    2021/8/19 2:09 下午
 * @version 1.0
*/
public class Company {

    private String name;

    /**
     *
     * */
    private Optional<Department> department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Optional<Department> department) {
        this.department = department;
    }

}
