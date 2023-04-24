import java.util.LinkedHashSet;
import java.util.Objects;
@SuppressWarnings({"all"})
public class Hello {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Car("ad",100));
        linkedHashSet.add(new Car("asd",100));
        linkedHashSet.add(new Car("ad",100));
        linkedHashSet.add(new Car("ead",100));
        System.out.println(linkedHashSet);
        for (Object c: linkedHashSet){
            System.out.println(c);
        }

    }
}

class Car{
    private String name;
    private int price;

    public Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return price == car.price && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}

