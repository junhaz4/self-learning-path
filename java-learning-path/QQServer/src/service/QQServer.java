package service;

import common.Message;
import common.MessageType;
import common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QQServer {
    private ServerSocket ss = null;
    //创建集合存放多个用户登录信息
    private static HashMap<String, User> validUsers = new HashMap<>();

    static {//初始化
        validUsers.put("100",new User("100","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("300",new User("300","123456"));
    }

    //验证用户
    private boolean checkUser(String userId, String pwd){
        User user = validUsers.get(userId);
        if(user == null){
            return false;
        }
        if(!user.getPwd().equals(pwd)){
            return false;
        }
        return true;
    }

    public QQServer(){
        try {
            System.out.println("服务器端在9999监听");
            //启动推送新闻线程
            new Thread(new SendNewsToAll()).start();
            ss = new ServerSocket(9999);
            //监听是持续的
            while(true){
                Socket socket = ss.accept();
                //得到输入
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                //验证
                if(checkUser(user.getUserID(), user.getPwd())){
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //将message回复给客户端
                    oos.writeObject(message);
                    //创建一个线程和客户端保持通信,该线程持有socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, user.getUserID());
                    serverConnectClientThread.start();
                    ManageClientThreads.addClientThread(user.getUserID(),serverConnectClientThread);
                    //发送离线消息
                    ConcurrentHashMap<String, ArrayList> offlineMap = OffLineService.getOffLineMap();
                    OffLineService.sendOffLineMessage(user.getUserID(), offlineMap);
                    OffLineService.deleteOffLineMes(user.getUserID());
                }else{//登录失败
                    System.out.println("用户" + user.getUserID() + "登录失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAILED);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //如果服务端退出while循环，说明不再监听
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
