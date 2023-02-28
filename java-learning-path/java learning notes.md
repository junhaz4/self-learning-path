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

#### 属性类型static, non-static, final

```java
// static: 存在于类中，所有创建的对象都可以访问，不需要创建对象也能访问
class Car {
    // static fields
    static int topSpeed = 100;
    static int maxCapacity = 4;  
}
class Demo {
    public static void main(String args[]){
    // Static fields are accessible in the main
    System.out.println(Car.topSpeed);
    System.out.println(Car.maxCapacity);   
  }
}

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

// final：属性的值不能改变
class Car {
  // Final variable capacity
  final int capacity = 4;
}
class Demo {
   public static void main(String args[]) {
      Car car = new Car();
      car.capacity = 5; // error can't assign a value to final variable capacity
   }
}

//
public class Hello{
    public static void main(String[] args){
      System.out.println(p()); //错误，不能在static方法里面call non-static方法，必须要创建一个new class然后调用，或者将call的方法改成static
      Hello hello = new Hello();
      System.out.println(hello.p());//正确
    }
    public String p(){
        return "gasdasd";
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

#### Abstract Class & Interfaces

Abstraction: 只把关键信息展示出来，隐藏其他内部细节降低复杂度，比如电视遥控器，Math module

Abstract data type: defines *‘what operations are to be performed?’* rather than *‘how to be performed?’*

Abstract method: 必须用abstract修饰符，而且只能存在于abstract class 或interface，只定义没有body部，并且不能被定义为private，因为其他class会用到，具体用的时候会根据需要写body部分

Abstract class: 不能被初始化，不用必须包含abstract method，使用abstract class必须通过继承，继承的子类必须完整定义所有存在于父类abstract class的abstract method， 并且必须使用@override

```java
abstract class Animal {
  public abstract void makeSound();
  public void move() {
    System.out.println(getClass().getSimpleName()+" is moving");
    //getClass().getSimpleName() is an inbuilt functionality of Java
    //to get the class name from which the method is being called
  }
}
class Dog extends Animal {
    @Override
    public void makeSound() {
    System.out.println("Woof Woof...");
  }
}
class Main {
  public static void main(String args[]) {
    // Creating the objects
    Animal dog = new Dog();  
    dog.makeSound();    // Calling methods from Dog
    dog.move();
  }
  
}
```

Interface接口: 一个抽象类型（Abstract Type), 是抽象方法的集合，接口通常以interface来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法。

接口并不是类，编写接口的方式和类很相似，但是它们属于不同的概念。类描述对象的属性和方法。接口则包含类要实现的方法。除非实现接口的类是抽象类，否则该类要定义接口中的所有方法。

接口不能创建对象，没有构造器，所有的方法必须是抽象方法，java8后接口中可以使用 default 关键字修饰的非抽象方法。接口不能包含成员变量，除了 static 和 final 变量。接口不是被类继承了，而是要被类实现，接口支持多继承

特性：接口中每一个方法也是隐式抽象的,接口中的方法会被隐式的指定为 **public abstract**只能是public abstract。接口中可以含有变量，但是接口中的变量会被隐式的指定为 **public static final** 变量，并且只能是 public。一个类只能继承一个抽象类，但是可以实现多个接口

接口的static method 不能被实现的子类override，同时子类也不能直接call

```java
//声明
[可见度] interface 接口名称 [extends 其他的接口名] {
        // 声明变量
        // 抽象方法
}
public interface NameOfInterface
{
   //任何类型 final, static 字段
   //抽象方法
}
interface Animal {
   public void eat();
   public void travel();
}

//接口的实现，类要实现接口中所有的方法。否则，类必须声明为抽象的类
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
 
//接口的继承，一个接口能继承另一个接口，和类之间的继承方式比较相似
public interface Sports
{
   public void setHomeTeam(String name);
   public void setVisitingTeam(String name);
}
 
// 文件名: Football.java
public interface Football extends Sports
{
   public void homeTeamScored(int points);
   public void visitingTeamScored(int points);
   public void endOfQuarter(int quarter);
}
//接口的多继承
public interface Hockey extends Sports, Event{}

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
}  // End of Elantra class
```

