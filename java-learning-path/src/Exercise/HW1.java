package Exercise;

public class HW1 {
    public static void main(String[] args) {
        Person[] persons = new Person[3];
        persons[0] = new Person("jack", 20, "sde");
        persons[1] = new Person("ma", 30, "accountant");
        persons[2] = new Person("fei", 39, "ceo");
        Person temp = null;
        for (int i=0; i<persons.length-1; i++){
            boolean swap = true;
            for (int j=0; j< persons.length-i-1; j++){
                if (persons[j].getAge() < persons[j+1].getAge()){
                    temp = persons[j];
                    persons[j] = persons[j+1];
                    persons[j+1] = temp;
                    swap = false;
                }
            }
            if (swap) break;
        }
        for (int i=0; i<persons.length; i++){
            System.out.println(persons[i]);
        }
    }
}

class Person{
    String name;
    int age;
    String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}