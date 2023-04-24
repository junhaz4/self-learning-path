package Exercise;

import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings({"all"})
public class Generic {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("tom",20000, new MyDate(2000,10,12)));
        employees.add(new Employee("jack",10000, new MyDate(2003,12,10)));
        employees.add(new Employee("marry",50000, new MyDate(1990,11,11)));
        System.out.println(employees);
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(!(o1 instanceof Employee && o2 instanceof Employee)){
                    System.out.println("wrong type");
                    return 0;
                }
                int i = o1.getName().compareTo(o2.getName());
                if (i != 0){
                    return i;
                }
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        });
        System.out.println(employees);
    }
}
