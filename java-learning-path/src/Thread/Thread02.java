package Thread;

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