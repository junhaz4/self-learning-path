package view;

import service.FileClientService;
import service.MessageClientService;
import service.UserClientService;
import utlis.Utility;

public class QQView {
    private boolean loop = true;
    private String key = "";
    private UserClientService userClientService = new UserClientService(); //用于登录，注册
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientService fileClientService = new FileClientService();
    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("客户端退出");
    }
    private void mainMenu(){
        while(loop){
            System.out.println("========欢迎登录========");
            System.out.println("\t\t 1 欢迎登录");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择：");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("请输入用户号：");
                    String userId = Utility.readString(50);
                    System.out.println("请输入密 码：");
                    String pwd = Utility.readString(50);
                    if(userClientService.checkUser(userId,pwd)){
                        System.out.println("========欢迎 (用户 "+userId+" ）登录========");
                        while(loop){
                            System.out.println("\n========网络通信系统二级菜单(用户 "+userId+" ）========");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    //System.out.println("显示在线人数");
                                    userClientService.onlineUsers();
                                    break;
                                case "2":
                                    System.out.println("请输入群发消息内容");
                                    String s = Utility.readString(100);
                                    messageClientService.sendMessageAll(s,userId);
                                    break;
                                case "3":
                                    System.out.println("请输入在线用户名:");
                                    String receiver = Utility.readString(50);
                                    System.out.println("请输入内容：");
                                    String content = Utility.readString(100);
                                    messageClientService.sendMessage(content,userId,receiver);
                                    //System.out.println("私聊消息");
                                    break;
                                case "4":
                                    System.out.print("请输入文件发送的在线用户");
                                    receiver = Utility.readString(50);
                                    System.out.print("请输入文件发送的路径");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入文件发送到对方的路径");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFile(src,dest,userId,receiver);
                                    break;
                                case "9":
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    }else{
                        System.out.println("登录失败");
                        break;
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
