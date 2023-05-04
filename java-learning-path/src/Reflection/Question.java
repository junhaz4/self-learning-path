package Reflection;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Question {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //根据配置文件，创建cat对象并调用方法

        //传统方式
//        Cat cat = new Cat();
//        cat.hi();

        //尝试文件读取
        //1. 使用properties类读写配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/Reflection/re.properties"));
        String classFullPath = properties.get("classFullPath").toString();
        String methodName = properties.get("method").toString();
        //System.out.println(classFullPath + "\n" + method);
        //2. 创建对象
        // new classFullPath() 不行，因为是string不是类名

        //OCP原则，开闭原则：不修改源码，扩展功能,控制程序
        //3. 反射机制解决
        //(1) 加载类，返回class类型的对象
        Class cls = Class.forName(classFullPath);
        //(2) 通过cls得到加载的类Cat的对象实例
        Object o = cls.getDeclaredConstructor().newInstance();
        System.out.println(o.getClass()); //运行类型
        //(3) 通过cls得到加载的类Cat的methodName的方法对象
        //在反射中，可以把方法当做对象，即万物皆对象
        Method method1 = cls.getMethod(methodName);
        //(4) 通过method调用方法：通过方法对象来实现调用方法
        method1.invoke(o);
        //只需修改配置文件中的内容，无需改动源码，即可修改程序，不需要重新编译
    }
}
