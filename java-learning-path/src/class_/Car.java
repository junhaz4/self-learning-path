package class_;

public class Car {
    public String name = "";
    public int price = 1000;
    public String color = "ad";

    @Override
    public String toString() {
        return "class_.Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }
}
