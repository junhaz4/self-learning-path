public class Main {
    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();
        cellPhone.alarm(new Bell() {
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
        bell.ring();
    }
}

