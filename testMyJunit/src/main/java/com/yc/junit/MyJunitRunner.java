package com.yc.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: maven_1
 * @description:
 * @author: 作者
 * @create: 2021-03-31 19:51
 */
public class MyJunitRunner {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //没有idea的插件 ，只能先做  class  加载
        Class cls = Class.forName("com.yc.MyCalculatorTest");

        //1.获取这个类中的所有方法
        Method [] ms = cls.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<Method>();
        Method beforeMethod = null;
        Method afterMethod = null;
        Method beforeClassMethod = null;
        Method afterClassMethod = null;

        //2.循环这些方法，判断上面加了那个注解
        for(Method m:ms){
            //3.将这些方法分别存好  @test对应的方法有多少  存到一个集合中
            if(m.isAnnotationPresent(MyTest.class)){
                testMethods.add(m);  //添加到集合中
            }
            if(m.isAnnotationPresent(MyBefore.class)){
                beforeMethod = m;
            }
            if(m.isAnnotationPresent(MyAfter.class)){
                afterMethod = m;
            }
            if(m.isAnnotationPresent(MyBeforeClass.class)){
                beforeClassMethod = m;
            }
            if(m.isAnnotationPresent(MyAfterClass.class)){
                afterClassMethod = m;
            }
        }

        //4.按junit的运行的生命周期来调用
        if(testMethods == null || testMethods.size()<=0 ){
            throw new RuntimeException("没有要测试的方法");
        }
        Object o = cls.newInstance();  //实例化 测试类
        beforeClassMethod.invoke(o,null);
        for(Method m : testMethods){
            if(beforeMethod !=null ){
                beforeMethod.invoke(o,null);
            }
            m.invoke(o,null);  //测试方法
            if(afterMethod !=null ){
                afterMethod.invoke(o,null);
            }
        }
        afterClassMethod.invoke(o,null);
    }

}
