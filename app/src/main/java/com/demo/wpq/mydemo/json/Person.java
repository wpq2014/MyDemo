package com.demo.wpq.mydemo.json;

public class Person {

    public String name;
    private int   age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.err.println(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.err.println(age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
