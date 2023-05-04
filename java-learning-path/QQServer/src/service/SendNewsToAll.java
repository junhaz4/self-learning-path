package service;

import common.Message;
import common.MessageType;
import utlis.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class SendNewsToAll implements Runnable{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        //为了多次推送
        while (true){
            System.out.println("输入exit推出推送程序或者请输入要推送的新闻、消");
            String news = Utility.readString(100);
            if("exit".equals(news)){
                break;
            }
            Message message = new Message();
            message.setSender("服务器");
            message.setContent(news);
            message.setMesType(MessageType.MESSAGE_TO_ALL);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人说：" + news);
            //遍历当前所有通讯线程
            HashMap<String, ServerConnectClientThread> map = ManageClientThreads.getMap();
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String onlineUserId = iterator.next();
                try {
                    ObjectOutputStream oos =
                            new ObjectOutputStream(map.get(onlineUserId).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
