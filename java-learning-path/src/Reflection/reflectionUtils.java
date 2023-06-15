package Reflection;

import org.testng.annotations.Test;

//通过反射获取类的结构信息
public class reflectionUtils {
    public static void main(String[] args) {

    }
    @Test
    public void api_01() throws Exception{
        Class<?> personCls = Class.forName("Reflection.Person");
        //获取全类名
        personCls.getName();
        //获取简单类名
        personCls.getSimpleName();
        //获取所有属性，包含本类及父类
        personCls.getFields();
        //获取本类所有属性
        personCls.getDeclaredFields();
    }
}

class Person{
    public String name;
    protected int age;
    String job;
    private double salary;

    public void m1(){}

    protected void m2(){}

    void m3(){}

    private void m4(){}
}
