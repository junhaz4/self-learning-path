public class OOP {
    public static void main(String[] args) {
        class Person {
            //A private member cannot be accessed directly from outside the class.
            // The aim is to keep it hidden from the users and other classes.
            private String address;

            // public member can be directly accessed by anything which is in the same scope as the class object
            public String name;

            //protected category is unique. The access level lies somewhere between private and public.
            // The primary use of the protected tag can be found when using inheritance,
            // The protected data members can be accessed inside a Java package.
            // However, outside the package, they can only be referred to through an inherited class.
            protected void get_age() {System.out.println("age is ");}

            //If we do not mention any access modifier, then it is considered to be default access.
            // The default access is similar to the protected. It also has package-level access,
            // but it also applies to inherited classes as well, unlike protected
            int age;
        }
        //Fields

    }
}
class Person{
    public String name;
    private int age;
    private double salary;
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
