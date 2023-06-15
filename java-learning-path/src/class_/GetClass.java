package class_;

public class GetClass {
    public static void main(String[] args) throws Exception{
        //1. 已知一个类的全名，且该类在类路径下，可通过Class.forName获得。多用于配置文件，读取类全路径
        String classFullPath = "class_.Car"; //通过配置文件读取
        Class cls1 = Class.forName(classFullPath);
        System.out.println(cls1);
        //2. 已知具体的类，通过类名.class，用于参数传递
        Class cls2 = Car.class;
        System.out.println(cls2);
        //3. 已知类的实例，通过getClass
        Car car = new Car();
        Class cls3 = car.getClass();
        System.out.println(cls3);
        //4.通过类加载器获取class对象
        //(1)先得到类加载器
        ClassLoader classLoader = car.getClass().getClassLoader();
        //(2)通过类加载器得到class对象
        Class cls4 = classLoader.loadClass(classFullPath);
        System.out.println(cls4);
        //5.基本数据类型，通过.class获得
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        System.out.println(integerClass);
        //6.基本数据对应的包装类，通过.TYPE
        Class<Integer> type = Integer.TYPE;
        System.out.println(type);
    }
}
