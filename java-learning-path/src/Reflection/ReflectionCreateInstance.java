package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SuppressWarnings({"all"})
public class ReflectionCreateInstance {
    public static void main(String[] args) throws Exception{
        //1.获取User类的class对象
        Class<?> userCls = Class.forName("Reflection.User");
        //2.通过public的无参构造器构建实例
        Object o = userCls.newInstance();
        System.out.println(o);
        //3.通过public的有参构造器构建实例
        //3.1先得到对应构造器
        Constructor<?> constructor = userCls.getConstructor(String.class);
        //constructor对象就是public有参构造器
        //3.2 创建实例，传入实参
        Object hsp = constructor.newInstance("hsp");
        System.out.println(hsp);
        //4. 通过非public的有参构造器构建实例
        //4.1 得到private构造器对象
        Constructor<?> declaredConstructor = userCls.getDeclaredConstructor(int.class, String.class);
        //4.2 创建实例
        declaredConstructor.setAccessible(true); //爆破，使用反射可以访问私有
        Object adasd = declaredConstructor.newInstance(100, "adasd");
        System.out.println(adasd);

        //使用反射得到属性对象
        Field age = userCls.getField("age");
        age.set(o,88);
        System.out.println(age.get(o));
        Field name = userCls.getDeclaredField("name");
        name.setAccessible(true);
        name.set(null,"adad");//name.set(o,"adad"); 两种都可
        System.out.println(o);
    }
}

class User{
    private int age = 10;
    private String name = "asd";

    public User(){
    }

    public User(String name){
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
