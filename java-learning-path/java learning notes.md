### 自动类型转换

- **byte→short→int→long→float→double**
- **char→int→long→float→double**

细节:

1. 多种数据混合运算时，系统首先将所有数据转化成容量最大的类型，在进行计算。
2. 当把精度大的数据类型转化为精度小的时候，就会报错，反之就会进行自动类型转换
3. (byte,short）和char之间不发生自动转换
4. byte, short, char 可以计算，计算时首先转化为int类型
5. Boolean类型不参与转换，不可用0或者1替代
6. 自动提升原则：表达式结果的类型自动提升为操作数中的最大类型
7. a%b 当a是小数时，公式a-(int)a/b*b

```java
public class main{
  public static void main(String[] args){
			//细节1
      int n1 = 10; // ok
      float d1 = n1+1.1; // 错误，double->float
    	// two correct ways:
    	double d1 = n1+1.1;//correct
    	float d1 = n1+1.1F;//corrct
    	long l1 = 1L;
    	//细节2
    	int n2 = 1.1;// 错误 double -> int
			//细节3
    	//把具体数值赋给byte时，先判断该数是否在范围内，如果是就可以
    	byte b1 = 10; //对，在-128~127范围中
   		int n2 = 1; //n2是int
    	byte b2 = n2; //错误，此时n2是int类型，占有4个字节，byte只有1个字节。赋值变量的时候，要判断类型
    	char c1 = b1; //错误，byte -> char
    	byte b3 = 3;
    	b = b+2; //false
    	b += 3; // correct => b = (byte)(b+3)
    	//细节4
    	byte b2 = 1;
    	byte b3 = 1;
    	short s1 = 1;
    	short s2 = b2+s1; //错误，b2+s1->int
    	int s2 = b2+s1; //correct
    	byte b4 = b2 + b3; //错误，b2+b3->int，int->byte
    	//细节5
    	boolean pass = true;
    	int num = pass; //错误,boolean不参与类型转换
    	//细节6
    	byte b4 = 1;
    	short s3 = 100;
    	int num200 = 1;
    	double num300 = 1.1;
    	float num500 = b4+s3+num200+num300;//错误，最大类型是double，double->float
    	double num500 = b4+s3+num200+num300;
    	-10.5%3 = -10.5 - (-10)/3*3 = -10.5 - (-3)*3 = -1.5 //小数参与运算结果是近似值 
  }
}
```

### 强制类型转换

自动类型转换的逆过程

细节：

1. 将容量大的数据类型转化为容量小的，使用时要加上强制转化符(), 可能造成精度降低或者溢出，需小心使用
2. 强制符号只针对于最近的操作数有效，往往使用小括号提升优先级
3. char类型可以保存int的常量值，但不能保存int的变量值，需要强转
4. byte和short进行运算时，当做int处理 

```java
public class main{
  public static void main(String[] args){
    int n1 = (int)1.9;//精度损失
    int n2 = 2000;
    byte b1 = (byte)n2; //数据溢出
    int y = (int) 10*3.5+6*1.5; //错误，double->int,只有最近的10被转为了int，其他不变
    int y = (int)(10*3.5+6*1.5); //44.0->44
    char c1 = 100;
    int m = 100;
    char c2 = m;//错误
    char c3 = (char) m;//ok，100对应的字符d
  }
}
```

### String和基本数据类型转换

```java
public class main{
  public static void main(String[] args){
    //基本类型->String,使用+“”
    int n1 = 10;
    float f1 = 1.1f;
    double d1 = 4.5;
    boolean b1 = true;
    String s1 = n1+"";
    String s2 = f1+"";
    String s3 = d1+"";
    String s4 = b1+"";
    //String->基本类型,调用基本类型的parseXXX方法 
    String s5 = "123";
    int n2 = Integer.parseInt(s5);
    double d2 = Double.parseInt(s5);
    //string -> char
    char c1 = s5.charAt(0);
  }
}
```

### 进制

1. 二进制：0,1,满2进1，以0B或者0b开头
2. 十进制： -0-9，满10进1
3. 八进制：0-7，满八进一，以数字0开头
4. 十六进制：0-9及A-F，满16进1，以0X或者0x开头，此处A-F不区分大小写

```java
public class main{
  public static void main(String[] args){
    //二进制
    int n1 = 0b1010;
    //十进制
    int n2 = 1010;
    //八进制
    int n3 = 01010;
    //十六进制
    int n4 = 0X10101;
    //进制转换
    //八，十六转换十进制
    //0234 = 4*8^0+3*8^1+2*8^2=4+24+128=156
    //0X23A = 10*18^0+3*16^1+2*16^2=570
    //十进制转换八，十六进制
    //和转二进制一样
    //二进制转换八，十六进制
    //从低位开始，将二进制每三位一组，转成对应的八进制数即可0b11010101=>=>0b11(3)010(2)101(5)=>0325
    //从低位开始，将二进制每四位一组，转成对应的十六进制数即可0b11010101=>0x1101(D)0101(5)=>0xD5
    //八，十六进制转换二进制
    //将八进制的每一位数转化成一个3位的二进制数0237=>02(010)3(011)7(111)=>0b10011111
    //将十六进制的每一位数转化成一个4位的二进制数0x23B=>2(0010)3(0011)B(1011)=>001000111011
  }
}
```

源码&反码(inverse)&补码(complement)

1. 二进制位的最高位是符号位，0表示正数，1表示负数
2. 正数的源码&反码&补码都是一样的
3. 负数的反码=它的原码符号位不变，其他位取反
4. 负数的补码=负数的反码+1，负数的反码=负数的补码-1
5. 0的反码和补码都是0
6. java中的数都有符号
7. 计算机运算以补码方式进行，查看结果以原码查看

### 分支控制

```java
boolean b = true;
if (b=false) //注意此处和b==false不同，java中可以这么写，Python不行，并且此时b已经变成false，然后运行
  System.out.println("a")；
 else if (b)
   System.out.println("b")；
 else if (!b)
   System.out.println("c")；//输出C
 else
   System.out.println("d")；
```

#### switch分支

细节：

1. 表达式的数据类型，必须和case后的常量类型保持一致，或者是可以相互转换相互比较的类型
2. switch表达式的返回值必须是：（byte，short，char，int，enum，String）
3. case中的值必须是常量或者常量表达式，不能是变量
4. default是可选的，不写也可以
5. break用来在执行完一个case后跳出switch，如果没有break，程序会顺序执行到switch结尾，除非遇到break

```java
switch（表达式）{ //表达式对应一个值
   case 常量1； //如果常量1等于表达式，执行语句块1
   语句块1；
   break；      //退出当前switch，如果没有break，直接执行case2语句，不会进行判断，称为穿透
   case 常量2；  // 如果1不匹配，就尝试匹配2 
   语句块2；
   break；
   ....
   default；    //如果都没有匹配，执行default
   default语句块；
   break；
 }
//eg
Scanner myScanner = new Scanner(System.in);
char c1 = myScaneer.next.().charAt(0);
switch(c1){
  case "a":
    System.out.println("asd");
    break;
  case "b":
    System.out.println("asd");
    break;
  //...
  default:
    System.out.println("done");
}
System.out.println("exit");
//eg
switch(c1){
  case "a":
    System.out.println("asd1");
  case "b":
    System.out.println("asd2");
    break;
  default:
    System.out.println("done");
}
//输出asd1，asd2
```

### for循环

```java
//第一种写法
for (int i=1; i<=10; i++){
  System.out.println("done");
}
System.out.println(i); //报错，i不存在
//第二种写法
int i=1;
for (; i<=10; ){
  System.out.println("done");
  i++;
}
System.out.println(i); // ok
//两种区别是第二种在循环结束后依然可以继续使用i，第一种则不行
 
//无限循环
for(;;){
  System.out.println("ok");
}
```

### 数组

定义：数据类型 数组名[] (括号也可放在数据类型后面) = new 数据类型[大小] ，也可先声明数组后创建数组

```java
//直接创建
int a[] = new int[3];
//先声明后创建
int b[];
b = new int[3];
//静态初始化
int a[] = {1,2,3,4,5,6};
//常见错误
String[] strs = new String[3]{"a","b","c"};//错误，括号中不用3，由编译器判断数量
String[] strs = new String{"a","b","c"}//错误
```

Jvm的内存分为三部分：栈，堆，方法区

数组引用赋值的时候，栈内变量指向一个虚拟地址，地址存在堆中。当创建新的赋值时，拷贝的是虚拟地址，也指向同样的空间，所以更改新的数组会影响到原来的。

解决方法：创建一个新的数组，开辟一个新的空间，遍历把每个元素拷贝到新的数组

冒泡排序: 每轮排序比较相邻元素，把较大的元素放在后面，每轮结束后最大的数在后面

```java
int temp;
for (int i=0; i<arr.length-1; i++){
  boolean swap = true;
  for (int j =0; j<arr.length-1-i; j++){
    if (arr[j] > arr[j+1]){
      temp = arr[j];
      arr[j] = arr[j+1];
      arr[j+1] = temp;
      swap = false;
    }
  }
  if (swap){//如果有一轮没有任何交换，说明已经排序完成，直接结束
    break;
  }
}
```

二维数组

```java
//动态初始化
int a[][] = new int[2][3];
//先声明后创建
int b[][];
b = new int[2][3];
//静态初始化
int a[][] = {{1,2,3},{3,4,5}}
//每个column的个数不用相同
int arr[]][] = new int[3][];//创建二维数组，但是只确定一维数组的个数
for (int i=0; i<arr.length; i++){
  arr[i] = new int[i+1];//必须给每一个一维数组开辟空间
  for (int j=0; j<arr[i].length; j++){
    arr[i][j] = i+1;
  }
} 
```

### OOP
原则：高内聚，低耦合，一段代码建议只实现一个功能，提高可读性和扩展性
<img src="/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-20 at 4.17.33 PM.png" alt="Screenshot 2023-02-20 at 4.17.33 PM" style="zoom:80%;" />

![Screenshot 2023-02-20 at 5.15.15 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-20 at 5.15.15 PM.png)

#### 传参机制

```java
public static void main(String[] args){
  //基本数据,非引用类型,传递的是值，参数变量不会影响到实际变量
  int a = 5, b=10;
  Person p = new Person();
  p.swap(a,b); // a,b传送进swap函数会创建一个新的栈给swap，同时a和b也会拷贝到新的栈里面，此时栈里面a和b的数值发生了交换，a=10，b=5，但仅仅是在swap栈里面，并没有改变main里面的a和b
  System.out.println(a,b);// 5, 10，在main里面print a和b的时候，找的是main这个栈里面a和b的数值，所以没有发生变化
  
  //引用类型传递,传递的是引用
  Person b = new Person();
  int[] arr = {1,2,3};
  b.update(arr); //传进update的是arr的地址，update栈和main栈里面的arr都指向同一个地址，这个地址存在堆里面，所以update更新会改变整个arr
  for (int i=0; i<arr.length; i++){
      System.out.print(arr[i]); //200,2,3
   }
  Animal a = new Animal();
  a.name = "asdasd";
  a.age = 10;
  b.change(a);
  System.out.print(a.age) // 200
}
class Animal{
  String name;
  int age
}
class Person{
  public void swap(int a, int b){
  int temp = a;
  a = b;
  b = a;
  System.out.print(a,b); // 10, 5
	}
  public void update(int[] arr){
    arr[0] = 200;
    for (int i=0; i<arr.length; i++){
      System.out.print(arr[i]); //200,2,3
    }
  }
  public void change(Animal a){
    a.age = 200;
  }
}

```

#### 可变参数

允许将同一个类中多个同名同功能但参数个数不同的方法，封装成一个方法

```java
 访问修饰符 返回类型 方法名（数据类型...参数名）{
 }
class HspMethod{
  //int...比哦啊his可接受的是可变参数，类型是int，可以接收多个int(0-多)
  //使用可变参数时，参数可以为数组，也可以当做数组使用
  public int sum(int...nums){
    int res = 0;
    for (int n:nums){
      res += n;
    }
  }
  //当可变参数和不可变一起使用时，必须放在最后
  //可变参数最多只能有1个
  public void f(String s, int...nums){
  }
}
```

#### 构造器

```java
public static void main(String[] args){
  Person p1 = new Person("jack",11);
  Person p2 = new Person(11);
}
class Person{
  String name;
  int age;
  //构造器没有返回值，不能写void
  //构造器的名称必须和class名字一样，规则和成员方法一样
  public Person(String pname, int page){
    name = pname;
    age = page;
  }
  //构造器可以有多个不同的
  public Person(int page){
    age = page;
  }
}
//如果没有定义构造器，系统会自动给class生成一个默认无参构造器比如Person(){},使用javap反编译查看
//一点定义之后，默认的会被覆盖掉，除非额外定义一下
```

对象创建流程分析：以Person p = new Person("jack",10)为例

1. 方法区加载person类
2. 在堆中开辟空间
3. 进行变量初始化，先对age和name默认初始化为0和null，然后根据给的值改变
4. 构造器执行，根据传递的参数改变age和name的值，常量池会存放一个String Jack，name就指向这个地址
5. 在栈中创建一个p，指向堆中的对象的地址，p只是对象的名字或者引用，真正的对象存在堆中 

#### this

```java
//java虚拟机会给每个对象分配this，指向其所在的对象，Python中的self
class Dog{
  String name;
  int age;
  public Dog(String name, int age){
    this.name = name;
    this.age = age;
  }
}
//访问构造器语法
class T{
  public T(){
    this("asd",12);//在一个构造器中访问另一个构造器，this()必须放在第一条语句
  }
  public T(String name, int age){
    System.out.print("das");
  }
}
```

#### 访问修饰符

1. public: 对外公开，都可以访问
2. protected：同一个类，同一个包，子类可以访问，不同包不可以
3. default：同一个类和同一个包的可以访问，子类和不同包不可以
4. private：只有同一个类可以访问，同包，子类，不同包都不可访问
5. 只有public和default可以修饰class，function和attribute规则一致

```java
public class A{
  public int n1 = 100;
  protected int n2 = 200;
  int n3 = 300;
  private int n4 = 400;
  public void m1(){
    System.out.println(n1+n2+n3+n4); //四个属性都输出
  }
}
```

#### 属性类型 

##### 类/静态变量static

静态变量存放在堆中，在类对应的class实例的尾部，在类加载的时候初始化

```java
// static: 存在于类中，所有创建的对象都可以访问，不需要创建对象也能访问
class Car {
    static (public) int topSpeed = 100;  
    (public) static int maxCapacity = 4;  
  	public int n1;
  	public static void show(){ //静态方法只能访问静态属性，静态方法
      Car.topSpeed += 10;
      System.out.println(this.maxCapacity);//类方法不允许使用和对象有关的关键字，this，super
    }
}
class Demo {
    public static void main(String args[]){
    Car c = new Car();
    System.out.println(Car.topSpeed); //100
    System.out.println(c.topSpeed); //100
    System.out.println(Car.n1); //错误
  }
}
```

非静态变量non-static

```java
// non-static: 存在于类中，每个创建的对象都可以对这些属性有自己独特的值
class Car {
    // static fields
    int speed = 100;
    int capacity = 4;  
} //必须创建一个对象才能访问这些属性
class Demo {
    public static void main(String args[]){
    Car obj1 = new Car();
    System.out.println(obj1.speed);
    System.out.println(obj1.capacity);   
  }
}
```

##### main

1. main方法是虚拟机调用，虚拟机需要调用类的main方法，访问类型必须用public
2. 虚拟机在执行main的时候不需要创建对象，所以必须是static
3. 该方法接受String类型的参数，该数组保存java命令式传递的参数
4. 在main方法中，可以直接调用静态方法或者属性，但不能调用本类非静态成员，需要创建新的实例

```java
//静态方法只能访问静态成员
public class Hello{
    public static void main(String[] args){
      System.out.println(p()); //错误，不能在static方法里面运行non-static方法，必须要创建一个new class然后调用，或者将call的方法改成static
      Hello hello = new Hello();
      System.out.println(hello.p());//正确
    }
    public String p(){
        return "gasdasd";
    }
}
```

##### 代码块

static代码块，对类进行初始化，随着**类的加载**而执行，并且只会执行一次，只能调用静态属性

如果是普通代码块，每创建一个对象，就执行一次，可以使用任意成员

类什么时候会被加载：1. 创建对象实例时new，2. 创建子类对象实例，父类也会被加载，而且父类先被加载。3. 使用类的静态成员

```java
public static void main(String[] args){
  AA aa = new AA();
  AA aa1 = new AA(); //只会执行一次静态代码块
  BB bb = new BB();
  BB bb1 = new BB(); //执行两次
  System.out.println(CC.n1); // 静态代码块会被调用：0, cc
}
class AA{
  static {
    System.out.println("asdad");
  }
}
class BB{
  {
    System.out.println("asdad");
  }
}
class cc{
  public static int n1 = 0;
  static {
    System.out.println("cc");
  }
  {
    System.out.println("asdad");
  }
}
```

创建一个对象时，在一个类，调用顺序：

1. 调用静态代码块和静态属性初始化，优先级一样，如果有多个则按照定义顺序
2. 调用普通代码亏和普通属性初始化，同理
3. 调用构造器
4. 构造器的最前面隐含super和调用本类普通代码块和普通属性初始化

```java
public static void main(String[] args){
  A a = new A(); // 12345. asdads
}
class A{
  private static int n1 = getN1();
  static {
    System.out.println("asdasd");
  }
  public static int getN1(){
    System.out.println("12345");
    return 100;
  }
}
class BBB{
  {
     System.out.println();
  }
  public BBB(){
    //super()
    //本类普通代码块和普通属性初始化
    .....
  }
}
```

 创建子类时(继承关系)，静态代码块，静态属性初始化，普通代码块，普通属性初始化，构造器顺序如下：

1. 父类的静态代码块和静态属性
2. 子类的静态代码块和静态属性
3. 父类的普通代码块和普通属性
4. 父类的构造方法
5. 子类的普通代码块和普通属性
6. 子类的构造方法

##### 单例设计模式

单个实例，在软件系统中，对某个类只能存在一个对象实例。并且该类只提供一个取得其对象的方法

饿汉式：还没要求对象但是对象已经创建了，会造成对象创建没使用的问题

1. 构造器私有化=>防止直接new
2. 类的内部创建对象
3. 向外暴露一个静态的公共方法，getInstance

```java
public static void main(String[] args){
  GirlFriend gf1 = GirlFriend.getInstance();
  GirlFriend gf2 = GirlFriend.getInstance();
  System.out.println(gf1 == gf2); // 正确
}
class GirlFriend{
  private String name;
  // 2. 类的内部创建对象，为了能够在静态方法中返回对象，需要static
  private static GirlFriend gf = new GirlFriend("asd");
  // 1.构造器私有化
  private GirlFriend(String name){
    this.name = name;
  }
  //3. 提供一个公开的static方法，返回对象
  public static GirlFriend getInstance(){
    return gf;
  }
}
```

懒汉式：存在线程安全问题

```java
public static void main(String[] args){
  System.out.println(Cat.n1);//000
  Cat instance1 = Cat.getInstance();//对象此时为空，创建新的
  System.out.println(instance1); //dahuang
  Cat instance2 = Cat.getInstance();//对象不为空，直接返回
  System.out.println(instance2); //dahuang
  System.out.println(instance1 == instance2); //true
}
//在程序运行过程中只能创建一个对象
class Cat{
  private String name;
  public static int n1=000;
  // 2.定义一个static静态对象
  private static Cat cat;
  // 1.构造器私有化
  private Cat(String name){
    this.name = name;
  }
  //3.提供一个public static方法，返回一个cat对象，只有当用户使用时，返回cat对象，当之后再次调用时，返回之前的对象
  public static Cat getInstance(){
    if (cat == null){
      cat = new Cat("dahuang");
    }
    return cat;
  }
}
```

##### final

1. 当不希望类被继承时，可以用final

2. 当不希望父类的某个方法被子类重写时，用final
3. 当不希望某个属性值被改变时，用final修饰
4. 当不希望某个局部变量被修改的时候
5. final修饰的属性在初始化必须赋值，且之后不能修改
6. 如果final修饰的属性是静态的，那么初始化的位置只能是：1 定义时，2 静态代码块，不能在构造器赋值
7. final类不能继承，但是可以创建对象
8. 如果类不是final类，但是包含final方法，则该方法不能被重写，但是可以被继承
9. final不能修饰构造器
10. 如果一个类是final类，没有必要将方法改成final类
11. final和static往往搭配使用，不会导致类加载，底层效率高

```java
class Car {
  final int capacity = 4;
}
class Demo {
   public static void main(String args[]) {
      Car car = new Car();
      car.capacity = 5; // 错误
   }
}
```

#### 封装encapsulation

把抽象的数据(attributes)和对数据操作的方法(class methods)封装在一起，数据被保护在内部，程序的其他部分只有通过被授权的操作才能对数据进行操作，遥控器操作电视机就是个常见的例子 

步骤：

1. 将属性进行私有化private(不能直接修改属性)
2. 提供一个public set 方法，用于修改属性的值
3. 提供一个public get 方法，用于获取属性的值

```java
public class main(String[] args){
  Person p = new Person();
  //p.name = "jack"; //ok
  //p.age = 10; //错误
  p.setName("jack");
  p.setAge(10);
  p.setSalary(3000);
   
}
class Person{
    public String name;
    private int age;
    private double salary;
  	public Person(String name, int age, double salary){
      setName(name);
      setAge(age);
      setSalary(salary);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
```

#### 继承inheritance 

class 子类 extends 父类{}， Object是所有类的父类 

子类会自动拥有父类的所有非private得属性和方法，私有属性只能通过父类的公共方法访问

创建子类时，默认调用父类无参构造器，此时super可以省略不写。当父类没有默认无参构造器的时候，子类必须使用super指定父类的哪一个构造器来完成父类的初始化，父类构造器的调用不限于直接父类，将一直向上追溯到Object类

super：用于子类中，指向继承的父类, 必须放在第一行，super和this不能在同一个构造器中

当父类和子类出现同名的属性和方法是，必须使用super指代父类的属性和方法，若没有，super和this都可。如果多个上级类都有相同的名字，遵循就近原则

java是单继承机制，子类最多只能直接继承一个父类，并且子类和父类必须满足IS-A逻辑关系

![Screenshot 2023-02-25 at 4.09.45 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-25 at 4.09.45 PM.png)

```java
class Vehicle { 
  int fuel = 90;
  public void display() { 
    System.out.println("I am from the Vehicle Class");
  }
} 
class Car extends Vehicle{
  int fuel = 100;
  public void info() {
    System.out.println("Fuel Capacity from the Vehicle class: " + super.fuelCap); 
    System.out.println("Fuel Capacity from the Car class: " + fuelCap);
  }
  public void display() { //display method inside SubClass
    System.out.println("I am from the Car Class");
  } 
  public void printOut(){
    super.display();  //calling the display() of Vehicle(SuperClass)
    display();        //calling the display() of the Car(SubClass)
  }
}
```

![Screenshot 2023-02-25 at 3.16.09 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-25 at 3.16.09 PM.png)

```java
public static void main(String[] args){
  Son son = new Son();
  // 1、先看子类是否有该属性，如果有，直接返回
  // 2. 如果子类没有，查找父类有没有，如果有，直接返回
  // 3. 如果父类没有，继续找上级父类，直到Object
  System.out.print(son.name);
  System.out.print(son.aage); //报错，因为父类的aage是私有的
}
class Graddpa{
  String name = "adasd";
  String hobby = "adasd";
}
class Father extends Grandpa{
  String name = "asdasd";
  int age = 132;
  private int aage = 123;
}
class Son extends Father{
  String name = "SDFSDF";
}
```

继承的5中类型

```java
// single inheritance单继承，一个子类只能继承一个父类
class Vehicle {          //Base Vehicle class  
  private int topSpeed;
  public void setTopSpeed(int speed) {
    this.topSpeed=speed;
    System.out.println("The top speed is set to: "+ topSpeed);
  }
} 
class Car extends Vehicle { // sub class Car extending from Vehicle
  public void openTrunk() {  
    System.out.println("The Car trunk is Open Now"); 
  } 
} 

// multi-level 多重继承，一个子类有很多上级类
class Vehicle {          //Base Vehicle class  
  private int topSpeed;
  public void setTopSpeed(int speed) {
    this.topSpeed=speed;
    System.out.println("The top speed is set to: "+ topSpeed);
  }
}
class Car extends Vehicle { // Derived from Vehicle Base for Prius
  public void openTrunk() {
    System.out.println("The Car trunk is Open Now!"); 
  } 
} 
class Prius extends Car {// Derived from Prius & can be base to any further class
  public void turnOnHybrid() {
    System.out.println("The Hybrid mode is turned on!"); 
  } 
} 

//Hierarchical Inheritance 层次继承，多个子类继承一个父类
class Vehicle {          //Base Vehicle class  
  private int topSpeed;
  public void setTopSpeed(int speed) {
    this.topSpeed=speed;
    System.out.println("The top speed of "+getClass().getSimpleName()+" is set to: "+ topSpeed);
  }
} 
class Car extends Vehicle { // Derived from Vehicle Base for Prius
  //implementation of Car class
} 
class Truck extends Vehicle {// Derived from Prius can be base to any further class
  //implementation of Truck class
} 

//multiple inheritance 多个继承，一个子类继承多个父类，A4是个车，A4是个奥迪
//hybird inheritance 多重继承和多个继承合起来
//这两类继承都只能用于interface
```

#### 多态Polymorphism

同一个对象或者方法在不同情况下有不同的表现形式和行为

多态存在的三个必要条件：继承，重写，父类引用指向子类![ ](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-25 at 10.17.01 PM.png)

```java
//当使用多态方式调用方法时，首先检查父类中是否有该方法，如果没有，编译错误；如果有，再去调用子类的同名法。
class Shape {
  public double getArea() {
    return 0;
  }
}
// A Rectangle is a Shape with a specific width and height
class Rectangle extends Shape {   // extended form the Shape class
  private double width;
  private double height;
  
  public Rectangle(double width, double heigh) {
    this.width = width;
    this.height = heigh;
  }
  public double getArea() {
    return width * height; 
  }
  
}
// A Circle is a Shape with a specific radius
class Circle extends Shape {
  private double radius;

  public Circle(double radius) {
    this.radius = radius; 
  }
  public double getArea() {
    return 3.14 * radius * radius; 
  }
}

//静态：编译多态 -> 方法overloading
class Calculator {
  int add(int num1, int num2) {
    return num1 + num2;
  }
  int add(int num1, int num2, int num3) {
    return num1 + num2 + num3;
  }
  public static void main(String args[]) {
 		//两个add方法，哪个会被使用取决于编译时的参数
    Calculator obj = new Calculator();
    System.out.println("10 + 20 = " + obj.add(10, 20));
    System.out.println("10 + 20 + 30 = " + obj.add(10, 20, 30));
  }
}

//动态：运行多态 -> 方法overriding
//子类重载了父类的方法，哪个方法会被使用取决于对象的类型是什么，一个对象的编译类型和运行类型是可以不同的
//编译类型等号的左边，运行类型等号的右边。编译类型确定后不能改，运行类型可以变化
class Shape {
  public double getArea() {
    return 0;
  }
}
class Circle extends Shape {
  private double radius; 
  public Circle(double radius) {
    this.radius = radius;
  }
  public double getArea() {
    return this.radius * this.radius * 3.14;
  }
  public double size(){
    return this.radius*2;
  }
  public static void main(String args[]) {
    Shape c = new Circle(2);//向上转型
    c.getArea(); //可以调用父类中的所有成员
    c.size(); //错误，不能调用子类的特有成员，因为在编译阶段，能调用哪些由编译类型决定，最终效果看子类的具体实现，按照子类开始查找，直到找到位置
    
    //向下转型，只能强转父类的引用，不能强转父类的对象，要求 父类的引用必须指向当前目标类型的对象。当向下转型后，可以调用子类的所有成员
    Circle nc = (Circle) c; //若c不指向circle，则编译错误
    nc.size(); //ok
  }
```

##### 方法重载Overload

```java
//方法名字必须一样，参数必须不同（类型，顺序，个数，至少一个不一样），返回类型无所谓
class Calculator{//系统调用方法的时候，会根据实际传入的参数寻找匹配
  public int calculate(int n1, int n2){
    return n1+n2;
  }
  public double calculate(int n1, double n2){
    return n1+n2;
  }
  public double calculate(double n1, double n2){
    return n1+n2;
  }
  public void calculate(int n1, int n2){//没有构成方法重载，和第一个是重复的
    int res = n1+n2;
  }
}
```

##### 方法重写/覆盖Overwide

```java
//子类方法的返回类型必须是父类方法的返回类型，或者是其子类
//子类方法不能缩小父类方法的访问权限：public > protected > default > private
public class Aninmal{
  public void bark(){
    System.out.println("bark");
  }
  public Object m1(){
    return null;
  }
  public void eat(){}
}
public class Dog extends Aninmal{
  public void bark(){ // Dog的bark重写了Aniaml的
    System.out.println("gun");
  }
  public String m1(){ //也构成重写，String是Object的子类
    return null;
  }
  private void eat(){} //错误，访问权限改小了
}
```

![Screenshot 2023-02-25 at 4.28.56 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-02-25 at 4.28.56 PM.png)

##### 属性重写问题

```java
public static void main(String[] args){
  base b = new sub();
  System.out.print(b.count); // 10, 属性没有重写，属性的值看编译类型
}
class base{
  int count = 10;
}
class sub extends base{
  int count = 20;
}
//instance of 判断运行类型是否为xx的类型或者子类型
AA aa = new BB();
aa instance of AA; true
aa instance of BB; true
class AA{}
class BB extends AA{}
```

##### 动态绑定机制

```java
public static void main(String[] args){
  A a = new B();
  System.out.println(a.count); // 20
  System.out.println(a.sum()); //40
  System.out.println(a.sum1()); //30
  //现在去掉B中的sum（）
   System.out.println(a.sum()); //=> getI()是B中getI=>20+10=30
  //此时会调用A中的sum，但是sum中getI存在于A和B中，具体用哪个取决于动态绑定机制
  //1. 当调用对象方法的时候，该方法会和该对象的内存地址/运行类型绑定
  //2. 当调用对象的属性时，没有动态绑定，哪里有就直接用
  //去掉b中的sum1
  System.out.println(a.sum1());//同理，i是A中的i=>10+10 = 20
}
class A{
  public int i=10;
  public int sum(){
    return getI()+10;
  }
  public int sum1(){
    return i+10;
  }
  public int getI(){
    return i;
  }
}
class B extends A{
  public int i = 20;
  //public int sum(){
    //return i+20;
  //}
  public getI(){
    return i;
  }
  //public int sum1(){
    //return i+10;
  //}
}
```

##### Object类

==：是一个比较运算符，既可以判断基本类型，值是否相等；又可以判断引用类型，地址是否相等，是否指向同一个对象

equals: 只能判断引用类型，默认判断地址是否相等，子类通常会重写此方法，用于判断内容是否相等，比如integer和string

hashCode:  两个引用指向的是同一个对象，哈希值一定一样，如果不同对象，哈希值一般不同。hashCode主要是根据地址号来的，不能完全等价于地址

toString: 返回字符串形式，当直接输出一个对象时，toString会被默认的调用System.out.print等价于toString

finalize: 当对象被回收时，系统自动调用该对象的finalize方法，子类可以重写此方法，释放资源。

什么时候被回收：当某个对象没有任何引用时，jvm认为该对象是一个垃圾对象，就会启动垃圾回收机制销毁该对象, 代用该对象的finalize方法。垃圾回收机制的调用，是由系统来决定的(GC)，也可以通过System.gc()主动触发回收机制。

```java
String s = new String("asd");
String b = new String("ads");
System.out.print(s==b); //false
System.out.print(a.equals(b)); // true

car bmw = new Car("bmw");
bmw = null; //此时car对象就是一个垃圾，垃圾回收器就会回收对象，会调用该对象的finalize方法，程序员可以在此方法中写作业的业务逻辑，比如释放资源，数据库连接等等
//如果不在该对象重写finalize方法，就会调用object类的finalize方法，如果重写了就可以实现自己的逻辑
class car{
  private String name;
  public car(string name){
    this.name = name
  }
  protected void finalize() throws Throwable{
    System.out.print("asdad");
  }
}
```

#### 抽象类Abstraction
只把关键信息展示出来，隐藏其他内部细节降低复杂度，比如电视遥控器，Math module
当父类的某些方法需要声明但又不确定如何实现时，可以将其声明为抽象类，抽象类会被子类继承，由其来实现方法。
细节：
1. 抽象类不能实例化
2. 抽象类可以没有abstract方法，还可以有正常的方法
3. 一旦包含了抽象方法，必须定义为抽象类
4. abstract只能修饰类的和方法，不能修饰属性和其他
5. 抽象类还是类，可以有任意成员，比如非抽象方法，构造器，静态属性等等
6. 抽象类不能有主体，即不能实现
7. 如果一个类继承了抽象类，则它必须实现抽象类的所有抽象方法，除非它自己也声明是抽象类
8. 抽象方法不能使用private，final，static修饰，因为这些都和重写相违背
```java
public class Abstract{
    public static void main(String[] args) {
        new Animal(); //报错
    }
}
abstract class Animal {
  public abstract void makeSound();
  public void move() {
    System.out.println(getClass().getSimpleName()+" is moving");
  }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
    System.out.println("Woof Woof...");
  }
}
```
#### 接口Interface 
一个抽象类型（Abstract Type), 是抽象方法的集合，接口通常以interface来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法。
细节：
1. 接口不能创建对象，没有构造器，所有的方法必须是抽象方法，用public修饰，abstract关键字可以省略
2. 接口不是被类继承了，而是要被类实现，类必须实现接口的所有方法。接口支持多继承
3. 抽象类实现接口时可以不实现接口的方法
4. 一个类可以实现多个接口
5. 接口中的属性只能是final的，并且是public static final 
6. 接口不能继承其他的类，但可以继承多个接口
7. 接口的修饰符默认是public 
8. 接口的static method 不能被实现的子类override，同时子类也不能直接call

```java
//声明
[修饰] interface 接口名称 [extends 其他的接口名] {
        // 属性
        // 方法（抽象，默认实现，静态）
}
public interface Animal {
   public void eat(); //在接口中，抽象方法可以省略abstract关键字
   void travel(); //默认public修饰
   default public void ok(){ //jdk8以后，可以有默认实现方法，需要用default修饰
       System.out.println("ok");
   }
   public static void go(){ //jdk8以后，可以有静态方法
       System.out.println("asd");
   }
   int n1 = 10; // 等价public static final int n1 = 10;
}
//接口的实现，类要实现接口中所有的抽象方法。否则，类必须声明为抽象的类
public class MammalInt implements Animal{
   public void eat(){
      System.out.println("Mammal eats");
   }
   public void travel(){
      System.out.println("Mammal travels");
   } 
   public int noOfLegs(){
      return 0;
   }
}
//一个类可以实现多个接口
public class I implements Animals, Fruits{
}

//接口的单继承
public interface Sports
{
   public void setHomeTeam(String name);
   public void setVisitingTeam(String name);
}
public interface Football extends Sports
{
   public void homeTeamScored(int points);
   public void visitingTeamScored(int points);
   public void endOfQuarter(int quarter);
}
//接口的多继承
public interface Hockey extends Sports, Event{}
```
继承是is-a的关系，实现是like-a的关系，是对单继承机制的补充
子类继承父类，自动拥有父类的功能，如果子类需要扩展，可以通过实现接口扩展
接口可以实现代码解耦（接口规范+动态绑定）
```java
// 多个继承的实现需要interface
class Car {  // Base class
    private int model;  // Common features of all cars
    public Car(int model) {  // Constructor
        this.model = model;
    }
    public void printDetails() {
        System.out.println("The model of " + getClass().getSimpleName() + " is: " + model);
    }
}  // End of Car class

interface IsSedan {  // Interface for sedans
    int bootSpace = 420;  // Sedans have boot space
    void bootSpace();    // Every sedan must implement this
}  // End of IsSedan interface
class Elantra extends Car implements IsSedan {  // Elantra is a Car and is a Sedan also
    private String variant;    // Elantra's data member
    public Elantra(int model, String variant) {  // Constructor
        super(model);  // Calling the parent constructor with alredy known manufacturer
        this.variant = variant;
    }
    @Override
    public void bootSpace() { // Implementation of the interface method
        System.out.println("The bootspace of Elantra is: " + IsSedan.bootSpace +" litres");
    }
    @Override
    public void printDetails() {  // Overriding the parent class's inherited method
        super.printDetails();    // Calling the method from parent class
        System.out.println("The variant of Elantra is: " + variant);
    }
}  
```
##### 接口的多态性

```java
public class InterfaceP {
    public static void main(String[] args) {
        If i = new A();
        i = new B(); //接口类型的变量i 可以指向实现了If接口的对象实例

        IG ig = new teacher();
        IH ih = new teacher();//若果IG继承IH接口，而teacher实现了IG接口，那么相当于teacher也实现了IH接口,这就是多态传递
    }
}
interface If {
}
class A implements If {
}
class B implements If {
}

//多态传递
interface IH {
    void hello();
}
interface IG extends IH {
}
class teacher implements IG {
    @Override
    public void hello() {
    }
}
```
#### 内部类
一个类的内部有完整的嵌套了另一个类结构，被嵌套的称之为内部类，是类的五大成员（属性，方法，构造器，代码块。内部类），内部类可以直接访问私有属性，体现包含关系
```java
class Outer{
    private int n1 = 10;
    public Outer(int n1){
        this.n1 = n1;
    }
    public void m1(){}
    {
        System.out.println("hello");
    }
    class Inner{}
}
```
##### 内部类的分类
定义在外部类局部位置上
1. 局部内部类，有类名, 通常在方法
   细节：
   1. 可以直接访问外部类的所有成员，包括私有的
   2. 不能添加访问修饰符，但是可以用final修饰
   3. 作用域：仅在定义它的方法或者代码块中
   4. 局部内部类直接访问外部类的成员
   5. 外部类访问内部类成员，需要创建inner的对象，然后调用方法即可
   6. 外部其他类不能访问内部类
   7. 如果外部类和内部类的成员重名时，遵守就近原则，如果想访问外部类的成员，使用外部类名.this.成员去访问
```java
class Outer {
    private int n1 = 10;
    private void m2() {
    }
    public void m1() {
        class Inner { //局部内部类，仍然是一个类
            private int n1 = 800;
            public void f1() {
                System.out.println(n1); //800
                m2(); //局部内部类直接访问外部类的成员
                System.out.println(Outer.this.n1);//100, outer.this本质是外部类的对象，哪个对象调用了m1，就指向谁
            }
        }
        Inner inner = new Inner();
        inner.f1();
    }
}
```
2. 匿名内部类，无类名，本质还是类，同时还是一个对象
```java
class Outer {
   private int n1 = 10;

   public void method() {
      //基于接口的匿名内部类， 简化开发
      //a的编译类型是A，运行类型就是匿名内部类
      /*
              class XXX implements A{ //底层会分配类名，Outer$1
                @Override
               public void cry() {
                  System.out.println("asd");
               }
              }
       */
      //jdk在底层创建匿名内部类Outer$1，立即马上创建了Outer$1实例，并把地址给a
      //匿名内部类使用一次就不能在使用 
      A a = new A() {
         @Override
         public void cry() {
            System.out.println("asd");
         }
      };
      a.cry();

      //基于类的匿名内部类
      // father 编译类型 Father
      // father 运行类型 Outer$2
      // 底层创建匿名内部类Outer$2, 同时返回对象
      /*
              class Outer$2 extends Father{
                @Override 
                public void test(){
                    System.out.println("123");
                }
              }
       */
      //如果不加大括号就是普通的类
      Father father = new Father("jack") { //参数会传递给构造器
         @Override
         public void test() {
            System.out.println("123");
         }
      };

      //就要抽象类的匿名内部类
      Animal animal = new Animal(){
          @Override
          void eat(){
            System.out.println("asd");
         }
      };
   }
}

interface A {
   public void cry();
}

class Father {
   public Father(String name) {
      this.name = name;
   }

   public void test() {
      System.out.println("asdsad");
   }
}

abstract class Animal {
   abstract void eat() {
   }
}
// 匿名内部类作为参数传入方法中，只会运行一次
public class Main {
   public static void main(String[] args) {
      CellPhone cellPhone = new CellPhone();
      cellPhone.alarm(new Bell() { //传递的是实现了Bell接口的匿名内部类Main,重写了ring()方法
         @Override
         public void ring() {
            System.out.println("asda");
         }
      });
      cellPhone.alarm(new Bell() {
         @Override
         public void ring() {
            System.out.println("123");
         }
      });
   }
}
interface Bell{
   public void ring();
}
class CellPhone{
   public void alarm(Bell bell){
      bell.ring();//动态绑定和运行类型实际传递进去的一致
   }
}
```
定义在外部类的成员位置上
3. 成员内部类，无static修饰，可以添加任意访问修饰符

```java
class Outer {//外部类
   private int n1 = 90;
   public String name = "jack";
   private void hi() {
      System.out.println("12");
   }
   public class Inner {//成员内部类，定义在外部类的成员位置上
      public void say() {
         System.out.println(n1 + name); //可以直接访问外部类的所有成员，包括私有的
         hi();
      }
   }
   public void t1() {
      //使用成员内部类: 创建成员内部类对象，然后使用
      Inner inner = new Inner();
      inner.say();
   }
   public Innter getInner(){
       return new Inner();
   }

   //外部其他类访问成员内部类的方法
   public static void main(String[] args) {
      Outer outer = new Outer();
      // 1 直接通过外部类
      Outer.Inner inner = outer.new Inner();
      // 2 外部类中编写一个方法返回实例
      Outer.Inner inner2 = outer.getInner();
   }
}
```
4. 静态内部类，有static修饰

```java
class Outer {
   private int n1 = 90;
   public static String name = "jack";

   private static void cry() {
   }

   static class Inner { //静态内部类，放在外部类成员位置
      public static String name = "kevin";
      public void say() {
         System.out.println(n1); //错误
         System.out.println(name);//可以直接访问外部类的所有静态成员，包括私有的，不能直接访问非静态成员
         cry();
         System.out.println(Outer.name);
      }
   }
   public void t1() {//使用成员内部类: 创建成员内部类对象，然后使用
      Inner inner = new Inner();
      inner.say();
   }
   public Inner get() {
      return new Inner();
   }

   public static Inner get_() {
      return new Inner();
   }
}

public class Main {
   public static void main(String[] args) {
      Outer outer = new Outer();
      //外部其他类访问静态内部类
      // 1 静态内部类是可以通过类名直接访问，前提是满足访问权限
      Outer.Inner inner = new Outer.Inner();
      inner.say();
      // 2 创建一个非静态方法返回静态内部类的对象
      Outer.Inner inner1 = outer.get();
      // 3 创建一个静态方法返回静态内部类的对象
      Outer.Inner outer_ = outer.get_();
   }
}
```
### 异常

1. Error(错误): Java虚拟机无法解决的问题，比如stack over flow等，内存不足
2. Exception：其他因编程错误或者外在因素导致的一般性问题，比如空指针访问，读取不存在文件等等，主要分为两大类：运行时异常和编译时异常

```java
//try-catch-finally
//捕获异常，自行处理
try{//可能出现异常代码
  //如果异常发生，则异常后面代码不会执行，直接catch，否则继续执行
}catch(Exception e){//捕获异常
  //将异常封装成Exception对象e，传递给catch，程序员自己处理。如果没有异常，catch不执行
  //可以有多个catch分别捕获不同的异常，子类异常要写在前面，父类写在后面
}finally{
  //不管try是否有异常发生，始终要执行。通常将释放资源的代码放在这里
}

//throws
//如果一个方法可能出现异常，但是并不能确定如何处理这些异常，此时可以将异常抛出，交给调用者处理，最低级调用者是JVM，throws是默认异常处理
public void f2 throws Exception{//可以是多个异常
  //可能出现异常代码
}
//子类重写父类方法时，对抛出异常的规定：子类抛出的异常必须和父类一致，或者是父类抛出异常的子类
class Father{
  public void method() throws RuntimeException{}
}
class Son extends Father{
  @Override
  public void method() throws NullPointerException{
    throw new NullPointerException;
  }
}
```

### 常用类

```java
//integer创建机制
public void method(){
  Integer i = new Integer(1);
  Integer j = new Integer(1);
  System.out.println(i == j); //False
  //看范围，若-128~127就是直接返回，否则就是new Integer(xx)
  Integer m = 1;
  Integer n = 1;
  System.out.println(m == n);//True
  Integer x = 128;
  Integer y = 128;
  System.out.println(x == y);//False
  Integer i11 = 127;
  int i12 = 127;
  System.out.println(i11 == i12);//True，有基础类型就比较数值
}
```

```java
//String类，final类，不可被继承
//有属性private final char value[],用于存储字符串内容，value是final类型，地址不可修改，内容可
//两种创建方式
String s = "abc"; // 先从常量池常看是否有abc数据空间，如果有，直接指向，否则重新创建然后指向，s最终指向常量池的空间地址
String s2 = new String("abc");//先在堆中创建空间，里面维护了value属性，指向常量池的abc空间。如果没有abc，重新创建，如果有，直接通过value，最终指向堆中空间地址，堆中对象指向常量池空间地址

String s1 = "hello";
s1 = "haha"; //在常量池中创建新的haha对象，然后s1指向这个对象
String s = "hello" + "abc";//优化等价"helloabc",只创建一个对象

//1. 先创建一个StringBuilder sb = StringBuilder()在堆中
//2. 执行sb.append("hello")
//3. sb.append("abc")，append是在原字符串基础上加的
//4. String c = sb.toString(),c最后指向堆中的对象value[] ->池中"helloabc"
//总结："ab"+"ac"常量相加，看的是池，a+b看的是堆
String a = "hello";
String b = "abc";
String c = a + b;
String d = "helloabc";
System.out.println(c == d); //False

//String 保存字符串常量，里面的值不能更改，每次更新就是更改地址 private final char[] value
//StringBuffer，保存字符串变量，值可以更改，更新的是内容而不是抵制, char[] value
// String => StringBuffer
// 1 构造器
String str = "hello";
StringBuffer strb = new StringBuffer(str);
// 2 append
StringBuffer strb2 = new StringBuffer();
strb2 = strb2.append(str);
// StringBuffer => String
// 1 toString
StringBuffer strb = new StringBuffer("asdsad");
String s = strb.toString();
// 2 构造器
String s1 = new String(strb);

//StringBuilder 不是线程安全，优先用于单线程的操作，使用方法和StringBuffer一致
//StringBuilder 是final类，不能被继承，对象序列存在其父类的AbstractStringBuilder的char[]中，因此字符序列在堆中。

//String:不可变字符序列，效率低，复用率高
//StringBuffer:可变字符序列，效率较高(增删)，线程安全
//StringBuilder:可变字符序列，效率高，线程不安全
//注意：如果对String做大量修改，不用String
//效率：StringBuilder > StringBuffer > String
```

```java
import java.util.Arrays;
import java.util.Comparator;
//Arrays类
public class main {
  Integer arr[] = {1, 2, 3, 4, 5};
  Arrays.toString(arr);//返回数组的字符串形式
  Arrays.sort(arr); //排序
  Arrays.sort(arr, new Comparator<Integer>() { // 自定义排序方式
    @Override
    public int compare(Integer o1, Integer o2) {
      return o2-o1;
    }
  });
	int index = Arrays.binarySearch(arr,1); //二分查找,如果不存在，返回-(low+1)
  Integer newArr = Arrays.copyOf(arr,arr.length); //拷贝，如果拷贝长度大于arr.length,后面用null补上，如果拷贝长度小于0，返回错误
  Integer[] num = new Integer[]{1,2,3};
  Arrays.fill(num,99); //[99,99,99] 用后面的数字去填充num数组，可以理解成替换原来的元素
  Integer[] arr2 = {1,2,3};
  boolean equals = Arrays.equals(arr,arr2); //比较两个数组是否完全一致
  List aslist = Arrays.asList(2,3,4,5) //将数据转成一个list集合
}
```

```java
//BigInteger, 当数字很大的时候使用
BigInteger biginteger = new BigInteger("231233123214345435656876798709980");
BigInteger biginteger2 = new BigInteger("231233123214345435656876798709980");
//运算的时候需要用相应的方法，不能直接使用+-*/
BigInteger biginteger3 = biginteger.add(biginteger2);
// BigDecimal，当需要保留精度很高的时候使用
BigDecimal bigdecimal = new BigDecimal("213.12312431232454678977689");
//同理，运算的时候需要使用相应的方法
```

### 集合(List & Set) 

**ArrayList** 

1. 允许所有元素，空值也可以存放。底层是由数组实现存储
2. 基本等同于vector，线程不安全的，多线程的情况下，不建议使用
3. 底层源码分析:ArrayList中维护了一个OBJECT类型的数组elementData。当创建对象时，如果使用的是无参构造器，则elementData初始容量为0，第一次添加时，扩容到10，如果需要继续扩容，则为1.5倍。如果使用指定大小的构造器，则初始为指定大小，扩容为1.5倍

**Vector**
底层也是对象数组，线程安全的，无参构造，默认10，扩容按照2倍

**LinkedList **

1. 底层实现了双向链表和双端队列特点
2. 可以添加任意元素，线程不安全

**HashSet**

1. 底层是HashMap，可以存放null，只能有一个
2. 不保证元素是有序的，取决于hash后，再确定索引的结果
3. 底层源码分析：底层是HashMap，添加一个元素时，先得到hash值，然后得到索引。找到存储数据table，根据索引判断是否已经存放元素。如果没有，直接加入。如果有，调用equals比较，如果相同，放弃添加，否则以链表添加到后面。
4. 扩容树化机制：第一次添加时，table数组扩容到16，临界值是16*加载因子(0.75)=12，如果table使用到达了临界值12，就会扩容到16*2=32,新的临界值就是32*0.75=24，以此类推。在java8中，如果一条链表的元素个数到达TREEIFY_THRESHOLD(默认8),并且table大小>= MIN_TREEIFY_CAPACITY(默认64)，就会进行树化(红黑树)，否则默认采用数组扩容机制

**LinkedHashSet**
底层是LinkedHashMap，维护了一个数组+双向链表，使用hashcode决定存放位置，同时使用链表维护元素的次序。不允许添加重复元素

### Map

**HashMap**

1. 保存具有映射关系的key-value 元素，key和value可以是任何引用类型的数据，会封装到HashMap$Node对象中。为了方便遍历，会创建一个EntrySet，存放了元素Entry，每个Entry对象就有key,value, EntrySet<Entry<Key,value>>。Node实现了Entry的接口，但实际存放的还是Node。
2. key不允许重复，当有相同key则时替换value，value可重。key和value可以为空，key为null只能有一个。key和value存在单向一对一关系。
3. 扩容树化机制：底层维护了一个node类型的数组table，默认为null。当创建对象时，加载因子初始化为0.75。当添加key-value使，通过key的哈希值得到table索引，然后判断该处是否有元素。没有直接添加，如果有，判断要加入的key和当前元素的key是否相等，如果相等则直接替换value；如果不相等判断是树形结构还是链表，作出相应处理。第一次添加，table扩容为16，临界值12，之后再扩容，容量为原来的2倍一次类型。如果一条链表的元素个数到达TREEIFY_THRESHOLD(默认8),并且table大小>= MIN_TREEIFY_CAPACITY(默认64)，就会进行树化(红黑树)，否则默认采用数组扩容机制

```java
//遍历方式
// 1.先取出所有的key
Set keyset = map.keySet();
//1.1使用for循环
for (Object key: keyset){
  System.out.println(map.get(key));
}
//1.2使用迭代器
Iterator iterator = keyset.iterator();
while(iteator,hasNext()){
  Object key = iterator.next();
  System.out.println(map.get(key));
}

//2.取出所有的values,然后使用for或者迭代器取出所有的值
Collections values = map.values();
//2.1使用for
for (Object value: values){
  System.out.println(value);
}
//2.2使用迭代器
Iterator iterator = values.iterator();
while(iteator,hasNext()){
  Obejct value = iterator.next();
  System.out.println(values);
}

//3. 使用EntrySet取出k-v
Set entrySet = map.entrySet();
//3.1使用for循环
for (Object entry: entrySet){
  Map.Entry m = (Map.Entry) entry;
  System.out.println(m.getKey() + m.getValue());
}
//3.2使用迭代器
Iterator iterator = entrySet.iterator();
while(iteator,hasNext()){
  Map.Entry m = (Map.Entry) iterator.next();
  System.out.println(m.getKey() + m.getValue());
}
```

**HashTable**

1. 存放元素是k-v，键和值都不能为null，否则会抛出NullPointerException，使用方法和HashMap一致，线程安全的。
2. 底层有数组HashTable$Entry[]，初始化大小为11，临界值是11*0.75=8。扩容变为2倍再加1

**Properties** 

1. properties类继承自Hashtable类并且实现Map接口，使用特点和Hashtable类似。
2. 还可用于从xxx.properties文件中，加载数据到properties类对象，进行读取和修改

**如何选择集合实现类**

1. 先判断存储的类型(一组对象还是一组键值对)
2. 一组对象：
   1. 允许重复：List
      1. 增删多：LinkedList，底层双向链表
      2. 该柴东：ArrayList，底层维护object类型可变数组
   2. 不允许：Set
      1. 无序：HashSet，底层是哈希表(数组+链表+红黑树)
      2. 排序：TreeSet
      3. 插入和取出顺序一致：LinkedHashSet，数组+双向链表
3. 一对键对值：Map
   1. 键无序：Hashmap 底层哈希表
   2. 键排序：TreeMap
   3. 插入和取出顺序一致：LinkedHashMap
   4. 读取文件：Properties

**TreeSet**

底层TreeMap，使用无参构造器创建TreeSet，是无序的。需要用带有比较器的构造器并指定排序规则

去重机制：如果传入了comparator匿名对象，就使用实现的compare去重，如果返回0，就认为是相同的。如果没有传入，则按照添加对象实现的comparable接口的compareTo去重。

```java
TreeSet treeSet = new TreeSet(new Comparator(){
  @Override
  public int compare(Object o1, Object o2){ 
    return ((String)o1).compareTo((String)o2);//按照字母表顺序排序
    return ((String)o1).length() - ((String)o2).length()//按照长度从小到大
  }
});
treeSet.add("jack");
treeSet.add("a");
treeSet.add("tom");

TreeSet treeSet = new TreeSet();
//构造器并没有传入comparator接口的匿名内部类，所有底层会尝试把Person转成comparable类型
//因为Person并没有实现comparable接口，所有会报错
treeSet.add(new Person())
class Person{}
//解决方法
class Person implements Comparable{
  @Override
  public int compareTo(Object o){
    return ...//自己定义如何比较
  }
}
```

**TreeMap**

同理，使用默认的无参构造器创建TreeMap，是无序的，需要用带有比较器的构造器并指定排序规则

```java
TreeSet treeMap = new TreeMap(new Comparator(){
  @Override
  public int compare(Object o1, Object o2){ 
    return ((String)o1).compareTo((String)o2);//按照字母表顺序排序
    return ((String)o1).length() - ((String)o2).length()//按照长度从小到大
  }
});
treeMap.put("jack","adasd");
treeMap.put("a","123");
treeMap.put("tom","asaeqwe");
```

### 泛型

参数化类型，在类声明时通过一个标识表示类中某个属性的类型，或某个方法返回值的类型，或参数类型

```java
//泛型的声明
interface 接口<T>{} 和class类<K,V>{}, T,k,v不代表值，代表类型，也可用其他字母表示
  
Person<String> person = new Person<String>("asda");
Person<Integer> person = new Person<Integer>(100);
class Person<E>{
  E s; //E 表示s的数据类型，该数据类型在定义Person对象的时候指定，在编译期间，就确定E是什么类型
  public Person(E s){//也可以是参数类型
    this.s = s;
  }
  public E f(){//返回值类型是E
    return s;
  }
}

//给泛型指定数据类型，要求是引用类型，不能是基本类型
List<Integer> list = new List<Integer>();//ok
List<int> list = new List<int>();//错误

//在给泛型指定具体类型后，可以传入该类型或者其子类
Pig<A> pig = new Pig<A>(new A());
Pig<A> pig = new Pig<A>(new B());//B是A的子类，可以传入
class A{}
class B extends A{}
class Pig<E>{
  E e;
  public Pig(E e){
    this.e = e;
  }
}

//泛型的使用形式
List<Integer> list1 = new List<Integer>(); //常规形式
ArrayList<Integer> list2 = new ArrayList<>(); //省略形式，在实际开发中常用
//默认的泛型类型是Object
ArrayList arr = new ArrayList(); //等价于 ArrayList<Object> arr = new ArrayList<>();

//自定义泛型类
class 类名<T,R...>{//...表示可以有多个泛型
  成员
}
//普通成员可以使用泛型
//使用泛型的数组，不能初始化
//静态方法中不能使用类的泛型
class Tiger<T,R,M>{
  String name;
  T t;
  R r;
  M m;
  T[] ts; //不能初始化，因为类型没确定，无法开辟内存空间
}

//自定义泛型接口
//静态成员不能使用泛型
//泛型接口的类型，在继承接口或实现接口时确定
interface Ia extends a<String,Double>{}
//当实现Ia接口时，因为Ia继承了a接口，指定了U->String,R->Double, 在实现a接口的方法是，替换U,R
class II implements Ia{
  @Override
  public Double get(String s){
    return null;
  }
  @Override
  public void hi(Double d){
  }
}
class Ib implements a<Integer,Float>{
  ...//实现a的方法是，也会替换U->Integer和R->Float
}
interface a<U,R>{
  R get(U u);
  U name; //错误
  void hi(R r){}
}

//自定义泛型方法: 修饰符<T,R...>返回类型 方法名(参数列表){}
//可以再普通类中，也可以在泛型类中
Car car = new Car();
car.fly("bmw",1000);//当调用时，传入参数，编译器就会确认类型
Fish<String,ArrayList> = new Fish<>();
fish.hello(new ArrayList(),11.3f);

class Car{
  public <T,R> void run(T t, R r){} 
}
class Fish<T,R>{
  public<U,M> void run(U u, M m){}
  public void hi(T t){//不是泛型方法，而是方法使用了类声明的泛型
  }
  //泛型方法，可以使用类声明的泛型，也可以使用自己声明的
  public<K> void hello(R r, K k){
  }
}

//泛型的继承和通配符
//泛型不具备继承
List<Object> list = new List<String>(); //错误
//List<?>表示任意的泛型都可以接收
public static void print1(List<?> c){}
//? extends AA 表示上限，可以接收AA和其子类
public static void print2(List<? extends AA> c){}
//？super AA 表示下限，可以接收AA和其父类，不限于直接父类
public static void print3(List<? super AA> c){}
class AA{}
class BB extends AA{}
class CC extends BB{}

List<Object> list1 = new ArrayList<>();
List<String> list2 = new ArrayList<>();
List<AA> list3 = new ArrayList<>();
List<BB> list4 = new ArrayList<>();
List<CC> list5 = new ArrayList<>();
//全部ok
print1(list1);
print1(list2);
print1(list3);
print1(list4);
print1(list5);
//AA和AA子类
print2(list1);//错误
print2(list2);//错误
print2(list3);//ok
print2(list4);//ok
print2(list5);//ok
//AA和AA父类
print3(list1);//ok
print3(list2);//错误
print3(list3);//ok
print3(list4);//错误
print3(list5);//错误
```

### 多线程

创建线程的两种方式

1. 继承Thread类，重写run方法
2. 实现Runnable接口，重写run方法

```java
// 继承Thread类
public class Thread01 {
    public static void main(String[] args) {
        Cat cat = new Cat(); //创建cat对象，当做县城使用
        //cat.run(); 此时不是线程，就是普通run方法，就是串行化了
        cat.start();
    }
}
class Cat extends Thread{ //当一个类继承Thread，就可以当成线程使用
    int times = 0;
    @Override
    public void run() {//重写run方法
        while (true) {
            System.out.println("cat");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

//实现Runnable接口
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start() 没有此方法
        Thread thread = new Thread(dog);//创建thread对象。把dog传入
        thread.start();
    }
}
class Dog implements Runnable{//通过实现Runnable接口实现线程
    int count = 0;
    @Override
    public void run() {
        while (true){
            System.out.println("hi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

用户线程：工作线程，当线程的任务执行完或者通知方式结束

守护线程：为工作线程服务，当所有用户线程结束后，其结束，常见的是垃圾回收机制. setDaemon(true)

**线程同步机制**： 保证数据在任何同一时刻，最多有一个线程访问

```java
synchronized(对象){
} //同步代码块
public synchronized void f(){
} //同步方法
//互斥锁，保证数据完整性，需要保证锁的对象是同一个
//如果锁在非静态方法时，这是锁在this对象
//如果在静态方法中实现，这是锁在类本身，即为类.class

//线程死锁：多个线程都占用了对方的资源，互不相让，导致死锁
//释放锁
// 1. 当前线程的同步方法，同步代码块执行结束
// 2. 当前线程在同步方法，代码块中遇到break，return
// 3. 当前线程在同步方法，代码块中遇到未处理的error和exception，导致异常结束
// 4. 当前线程在同步方法，代码块中执行了执行对象的wait方法，线程暂停，释放锁
//下面方法不会释放锁
//1. 调用sleep，yield， 不会释放锁
//2. 当前线程在同步方法，代码块中执行时，其他线程执行suspend方法将线程挂起，不会释放锁
```

### 文件&IO流

```java
//创建文件相关方法
new File(String pathname).createNewFile(); //根据路径创建文件
new File(File parent, String child);//根据父目录文件+子路径
new File(String parent,String child);//根据父目录+子路径
//mkdir()创建一级目录, mkdirs()创建多级目录, delete()删除空目录或者文件
```

输入input：读取外部数据到程序中
输出output：将程序中数据输出到磁盘，光盘等

流的分类：

1. 按照操作数据的单位不同：字节流(8 bit)，字符流(按字符)
2. 按照数据的流向不同：输入流，输出流
3. 按照流的角色不同：节点流，处理流

![Screenshot 2023-04-17 at 3.11.05 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-04-17 at 3.11.05 PM.png)

节点流：从一个特点的数据源进行读写操作，fileReader, fileWriter

处理流：是连接已存在的流之上，为程序提供更强大的读写功能, BufferReader, BufferWriter

序列化：保存数据的值和类型

反序列化：恢复数据的值和类型

为了实现可序列化，必须实现两个接口之一：serializable或者externalizable

### 反射机制

反射机制运行程序借用Reflect API取得任何累的内部信息，并能操作对象的属性及方法。

加载完类之后，在堆中产生另一个class类型的对象，一个类只有一个clas对象，这个对象包含了类的完整结构信息。

总结：反射可以动态获取类的信息

```java
//入门
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
```

![Screenshot 2023-05-03 at 5.52.24 PM](/Users/lawrencezhang/Desktop/self-learning-path/java-learning-path/Screenshot 2023-05-03 at 5.52.24 PM.png)

优缺点：

1. 可以动态地创建和使用对象，使用灵活，没有反射机制，框架技术失去底层支撑
2. 使用反射是基本解释执行，影响执行速度，可以使用.setAccessible(True)提高速度

sad

