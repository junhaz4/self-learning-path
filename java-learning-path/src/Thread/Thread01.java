package Thread;
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