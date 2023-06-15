package class_;

import java.lang.reflect.Field;
@SuppressWarnings({"all"})
//Class类的梳理
public class Class01 {
    public static void main(String[] args) throws Exception {
        String classAllPath = "class_.Car";
        //<?>表示不确定的java类型
        //获取到car类对应的class对象
        Class cls = Class.forName(classAllPath);
        System.out.println(cls); //显示cls对象，是哪个类的class对象 src.class_.Car
        System.out.println(cls.getClass());//输出cls运行类型，java.lang.Class
        System.out.println(cls.getPackage().getName()); //得到包名
        System.out.println(cls.getName()); //得到全类名
        //通过反射创建对象实例
        Car car = (Car) cls.getDeclaredConstructor().newInstance();
        System.out.println(car);
        //通过反射获取属性brand
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));
        //给属性赋值
        brand.set(car,"adas");
        //得到所有属性
        Field[] fields = cls.getFields();
    }
}
