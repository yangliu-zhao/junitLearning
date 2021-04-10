package com.yc.reflection;

import com.yc.Showable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: 在程序运行中，有人给了一个类，请动态的了解这个类，且创建这个类的对象
 * @description:
 * @author: 作者
 * @create: 2021-03-29 18:40
 */
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入类路径");
            String path = sc.nextLine();
            System.out.println("待加载的类："+path);

            Class c = Class.forName(path);  //生成一个“反射”的字节码  反射实例
            String name = c.getName();
            System.out.println(name);

            //取属性
            Field [] fs = c.getDeclaredFields();
            if(fs!=null && fs.length>0){   //迭代
                for( Field f:fs){
                    String modifier = "";
                    switch (f.getModifiers()) {  //getModifiers()获取的是恒定的字段值，此处取出的int值代表private修饰符
                        case 1:
                            modifier = "public";
                            break;
                        case 2:
                            modifier = "private";
                            break;
                    }
                    System.out.println(modifier +"\t"+f.getName());
                    //算法：位图算法
                }
            }

            //取方法
            //Method[] ms = c.getMethods();
            Method[] ms = c.getDeclaredMethods(); //Declared  自己声明的
            if(ms!=null && ms.length>0){
                for( Method m:ms){
                    System.out.println(m.getModifiers() +"\t"+m.getReturnType().toString());
                    //算法：位图算法
                }
            }

            //取构造方法
            Constructor [] cs = c.getConstructors();
            if(cs!=null && cs.length>0){
                for( Constructor m:cs){
                    System.out.println(m.getModifiers() +"\t"+m.getName());
                    //算法：位图算法
                }
            }


            //利用反射完成实例化操作  之前的是字节码
            Object o = c.newInstance();   //无参构造方法
            //这个是  已知接口名  instanceof
            if(o instanceof Showable){     //若要解耦合，实现面向接口
                Showable p = (Showable) o;
                p.show();
            }


            //利用反射调用某个方法  适合j2EE中的规范方法调用场景
            System.out.println("=======================");
            if (ms !=null && ms.length >0){
                for(Method m:ms){
                    if(m.getName().startsWith("sh")){
                        //
                        m.invoke(o);
                    }
                }
            }

            Map<String,String > pMap = new HashMap<String,String>();
            pMap.put("name","张三");
            pMap.put("age",30 +"");
            Object oo = setValues(pMap,c);
            System.out.println(oo.toString());
        }

    }


    /**
     * 反射功能模块： 将map中保存的 属性值存到  cls对应的对象中  ，注意已知 cls 满足j2ee的javabean规范（  setXXX  getXXX）
     */
    public static Object setValues(Map<String,String> map,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o =null;
        o = cls.newInstance();  //
        Method[] ms = cls.getDeclaredMethods();
        if(ms!=null && ms.length >0){
            for(Method m:ms){
                if(m.getName().startsWith("set")){  //只有set开头才激活
                    String mName = m.getName();
                    String fName = mName.substring(3).toLowerCase(); //从3后面开始截取，并转换为大小写
                    String value = map.get(fName);
                    if("Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName()) || "Int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())){
                        m.invoke(o,Integer.parseInt(value));
                    }else{
                        m.invoke(o,value);   //调用set方法，设置值
                    }
                }
            }
        }
        return o;
    }


}
