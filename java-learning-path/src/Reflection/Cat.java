package Reflection;

public class Cat {
    private String name = "kevin";
    public int age = 10;
    public Cat(String name){
        this.name = name;
    }
    public Cat(){}
    public void hi(){
        System.out.println("hi "+ name);
    }
    public void cry(){
        System.out.println("damn" + name);
    }
}
