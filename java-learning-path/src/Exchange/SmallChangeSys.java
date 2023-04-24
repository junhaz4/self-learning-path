package Exchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        String note = "";
        double money = 0;
        double balance = 0;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String details = "-------------零钱通明细---------------";
        do {
            System.out.println("=======零钱通菜单======");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入职");
            System.out.println("\t\t\t3 消   费");
            System.out.println("\t\t\t4 推   出");
            System.out.println("请选择1-4");
            key = scanner.next();
            switch(key){
                case "1":
                    System.out.println("零钱通明细");
                    break;
                case "2":
                    System.out.println("收益入账金额");
                    money = scanner.nextDouble();
                    if (money <= 0){
                        System.out.println("需要大于0");
                        break;
                    }
                    balance += money;
                    date = new Date();
                    details += "\t收益入职" + money + "\t" + sdf.format(date) + "\t" + money;
                    break;
                case "3":
                    System.out.println(" 消   费");
                    break;
                case "4":
                    System.out.println("推   出");
                    loop = false;
                    break;
                default:
                    System.out.println("选择有错");
            }
        }while(loop);
        System.out.println("退出了");
    }
}
