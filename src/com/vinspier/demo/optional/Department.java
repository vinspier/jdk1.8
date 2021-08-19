package com.vinspier.demo.optional;

import java.util.List;
import java.util.Optional;

/**
 * 部门的信息
 *
 * @author  vinspier
 * @date    2021/8/19 2:09 下午
 * @version 1.0
*/
public class Department {

    private String name;

    private Optional<Person> leader;

    private List<Person> personList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Person> getLeader() {
        return leader;
    }

    public void setLeader(Optional<Person> leader) {
        this.leader = leader;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
