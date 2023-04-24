package Exchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSysOOP {
    boolean loop = true;
    Scanner scanner = new Scanner(System.in);
    String key = "";
    String note = "";
    double money = 0;
    double balance = 0;
    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String details = "-------------零钱通明细---------------";

    public void mainMenu(){
        System.out.println("零钱通菜单");
    }
    public void detail(){
        System.out.println(details);
    }
    public void income(){
        System.out.println("收益入账金额");
        money = scanner.nextDouble();
        if (money <= 0){
            System.out.println("需要大于0");
            return;
        }
        balance += money;
        date = new Date();
        details += "\t收益入职" + money + "\t" + sdf.format(date) + "\t" + balance;
    }
    public void pay(){
        System.out.println("消费金额：");
        money = scanner.nextDouble();
        if (money <= 0 || money > balance){
            System.out.println("需要在0-"+balance);
            return;
        }
        System.out.println("消费说明：");
        note = scanner.next();
        balance -= money;
        date = new Date();
        details += '\t' + note + '\t' + money + "\t" + sdf.format(date) + "\t"+ balance;
    }
    public void exit(){
        String choice = "";
        while (true){
            System.out.println("确定退出吗?y/n");
            choice = scanner.next();
            if ("y".equals(choice) || "n".equals(choice)){
                break;
            }
        }
        if (choice.equals("y")) {
            loop = false;
        }
    }
}
