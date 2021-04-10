package com.yc;

import com.yc.biz.Calculator;
import com.yc.junit.*;

/**
 * @program: maven_1
 * @description:
 * @author: 作者
 * @create: 2021-03-31 19:50
 */
public class MyCalculatorTest {
    private Calculator cal;  //待测试的单元

    @MyBeforeClass
    public static void bc(){ System.out.println("beforeclass");}

    @MyBefore  //执行测试方法前需要调用的
    public void setUp(){
        System.out.println("before");
        cal = new Calculator();
    }

    @MyAfter  //执行测试方法后需要调用的
    public void setDown(){
        System.out.println("after");
    }

    @MyAfterClass
    public static void ac(){ System.out.println("afterclass");}

    @MyBefore
    public void add(){
        System.out.println("add测试");
    }

    @MyAfter
    public void sub(){
        System.out.println("sub测试");
    }

}
