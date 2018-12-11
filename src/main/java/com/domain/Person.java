package com.domain;

import java.io.Serializable;
import java.util.HashMap;

public class Person implements Serializable {

    private static final long serialVersionUID = 1258423535950190022L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private Integer age;
    private String sex;
    private String id;
    private HashMap<String, String> family;

    public HashMap<String, String> getFamily() {
        return family;
    }

    public void setFamily(HashMap<String, String> family) {
        this.family = family;
    }

    public Person() {
    }

    public Person(String name, Integer age, String sex, String id, HashMap<String, String> family) {
        setName(name);
        setAge(age);
        setSex(sex);
        setId(id);
        setFamily(family);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", Name=" + name + ", Age=" + age +
                ", Sex=" + sex + ", Family=" +family + "]";
    }

}
