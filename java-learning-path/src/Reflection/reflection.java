package Reflection;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
@SuppressWarnings({"all"})
public class reflection {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/Reflection/re.properties"));
        String classFullPath = properties.get("classFullPath").toString();
        String methodName = properties.get("method").toString();
        //(1) 加载类，返回class类型的对象
        Class cls = Class.forName(classFullPath);
        //(2) 通过cls得到加载的类Cat的对象实例
        Object o = cls.getDeclaredConstructor().newInstance();
        System.out.println(o.getClass()); //运行类型
        //(3) 通过cls得到加载的类Cat的methodName的方法对象
        //在反射中，可以把方法当做对象，即万物皆对象
        Method method1 = cls.getMethod(methodName);
        //(4) 通过method调用方法：通过方法对象来实现调用方法
        method1.invoke(o); //反射：方法.invoke(对象)

        //Filed代表类的成员变量
        //Field name = cls.getField("name");//报错，该方法不能得到私有属性
        Field age = cls.getField("age");
        System.out.println(age.get(o)); //反射：成员变量对象.get(对象)

        //Constructor代表类的构造方法
        Constructor constructor = cls.getConstructor(); //括号中可以指定参数,返回无参构造器
        System.out.println(constructor);

        Constructor constructor2 = cls.getConstructor(String.class); //传入的是String类的class对象
        System.out.println(constructor2);
    }
}
