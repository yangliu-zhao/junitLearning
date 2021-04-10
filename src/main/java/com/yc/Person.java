package com.yc;

/**
 * @program: javabean 规范
 * @description:
 * @author: 作者
 * @create: 2021-03-29 18:41
 */
public class Person implements Showable{
    private String name;
    private int age;
    public Person(){
        System.out.println("无参构造方法");
    }

    public Person(String name){
        this.name = name;
        System.out.println("有参构造方法");
    }

    @Override
    public void show() {
        System.out.println("show()");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
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
