package service;

import common.Message;
import common.MessageType;
import common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *用户登录验证和注册
 */
public class UserClientService {
    private User user = new User();//可能在其他地方使用到user信息，所以做成成员属性
    private Socket socket;
    public boolean checkUser(String userId, String pwd){
        boolean check = false;
        user.setUserID(userId);
        user.setPwd(pwd);
        try {
            //链接到服务器，发送对象
            socket = new Socket(InetAddress.getLocalHost(),9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user); //发送user对象
            //读取服务器回复的message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();
            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){//登陆成功
                //创建一个线程和服务器端通信
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动线程
                clientConnectServerThread.start();
                //扩展客户端，将线程放入集合中管理
                ManageClientServerThread.addClientConnectServerThread(userId,clientConnectServerThread);
                check = true;
            }else{ //登录失败
                socket.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void onlineUsers(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_USERS);
        message.setSender(user.getUserID());
        try {
            //从管理线程的集合中，通过userid得到线程对象
            ClientConnectServerThread clientConnectServerThread =
                    ManageClientServerThread.getClientConnectServerThread(user.getUserID());
            //通过线程对象得到关联的socket
            Socket socket = clientConnectServerThread.getSocket();
            //得到当前线程socket对应的Objectoutputstream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message); //向服务端发送消息，所有对象列表
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXT);
        message.setSendTime(user.getUserID());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //ObjectOutputStream oos = new ObjectOutputStream(ManageClientServerThread.getClientConnectServerThread(user.getUserID()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(user.getUserID()+"退出系统");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
